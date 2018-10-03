package ftc.vision;


import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MineralProcessor implements ImageProcessor<MineralColorResult> {
    private static final String TAG = "MineralProcessor";
    private static final double MIN_MASS = 6;

    @Override
    public ImageProcessorResult<MineralColorResult> process(long startTime, Mat rgbaFrame, boolean saveImages) {
        //save the image in the Pictures directory
        if (saveImages) {
            ImageUtil.saveImage(TAG, rgbaFrame, Imgproc.COLOR_RGBA2BGR, "0_camera", startTime);
        }
        //convert image to hsv
        Mat hsv = new Mat();
        Imgproc.cvtColor(rgbaFrame, hsv, Imgproc.COLOR_RGB2HSV);
        // rgbaFrame is untouched; hsv now contains the same image but using HSV colors

        List<Scalar> hsvMin = new ArrayList<>();
        List<Scalar> hsvMax = new ArrayList<>();

        //hsvMin.add(new Scalar(  H,   S,   V  ));
        hsvMin.add(new Scalar(20,  100, 100)); //gold min
        hsvMax.add(new Scalar(30, 255, 255)); //gold max

        hsvMin.add(new Scalar(0,  0, 0)); //silver min
        hsvMax.add(new Scalar(0, 0, 255)); //silver max

        // make a list of channels that are blank (used for combining binary images)
        List<Mat> rgbaChannels = new ArrayList<>();

        // For each side of the image, a "color mass" will be computed. This mass is just how
        // much of a color is present on that side (in units of scaled pixels that pass the
        // color filter). This variable keeps track of the mass
        // of the color that ended up having the most "color mass" on each side.
        double [] maxMass = { Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE}; //max mass for left and right
        // This next variable keeps track of the color on each side that had the max "color mass"
        // with  0=Gold  1=Silver 2=UNKNOWN
        // So both sides start as unknown:
        int[] maxMassIndex = {2, 2, 2}; // index of the max mass

        // We are about to loop over the filters and compute the "color mass" for each color
        // on each side of the image.

        // These variables are used inside the loop:
        Mat maskedImage;
        Mat colSum = new Mat();
        double mass;
        int[] data = new int[3]; //used to read the colSum

        //loop through the filters
        for(int i=0; i<2; i++) {
            //apply HSV thresholds
            maskedImage = new Mat();
            ImageUtil.hsvInRange(hsv, hsvMin.get(i), hsvMax.get(i), maskedImage);

            //copy the binary image to a channel of rgbaChannels
            rgbaChannels.add(maskedImage);

            //apply a column sum to the (unscaled) binary image
            Core.reduce(maskedImage, colSum, 0, Core.REDUCE_SUM, 4);

            //loop through left and right to calculate mass
            int start = 0;
            int end = hsv.width()/3;
            for(int j=0; j<3; j++){
                //calculate the mass
                mass = 0;
                for(int x=start; x<end; x++){
                    colSum.get(0, x, data);
                    mass += data[0];
                }
                mass /= hsv.size().area(); //scale the mass by the image size

                //if the mass found is greater than the max for this side
                if(mass >= MIN_MASS && mass > maxMass[j]){
                    //this mass is the new max for this side
                    maxMass[j] = mass;
                    //and this index is the new maxIndex for this side
                    maxMassIndex[j] = i;
                }
                if(j == 0){
                    start = end;
                    end = 2*hsv.width()/3;
                }else if(j ==1){
                    start = end;
                    end = hsv.width();
                }
            }

        }
        //add empty alpha channel
        rgbaChannels.add(Mat.zeros(hsv.size(), CvType.CV_8UC1));
        //merge the 3 binary images and 1 alpha channel into one image
        Core.merge(rgbaChannels, rgbaFrame);

        //use the maxIndex array to get the left and right colors
        MineralColorResult.MineralColor[] mineralColors = MineralColorResult.MineralColor.values();
        MineralColorResult.MineralColor left = mineralColors[maxMassIndex[0]];
        MineralColorResult.MineralColor center = mineralColors[maxMassIndex[1]];
        MineralColorResult.MineralColor right = mineralColors[maxMassIndex[2]];

        if (saveImages) {
            ImageUtil.saveImage(TAG, rgbaFrame, Imgproc.COLOR_RGBA2BGR, "1_binary", startTime);
        }

        //construct and return the result
        return new ImageProcessorResult<>(startTime, rgbaFrame,
                new MineralColorResult(left,center, right)
        );
    }
}
