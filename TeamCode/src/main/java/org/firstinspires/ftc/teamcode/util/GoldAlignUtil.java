package org.firstinspires.ftc.teamcode.util;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class GoldAlignUtil {

    private GoldAlignDetector detector;
    private HardwareMap hardwareMap;

    public GoldAlignUtil( HardwareMap hardwaremap) {
        this.hardwareMap = hardwaremap;
    }

    public void init(){
        detector = new GoldAlignDetector();

        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance(), 0, false);
        detector.useDefaults();

        // Optional Tuning
        detector.alignSize = 50; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = -50; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005;

        detector.ratioScorer.weight = 5;
        detector.ratioScorer.perfectRatio = 1.0;

        detector.enable();
    }


    public double getXPosition() {
        return detector.getXPosition();
    }

    public boolean isAligned() {
        return detector.getAligned();
    }

    public void stop(){
        detector.disable();
    }

}
