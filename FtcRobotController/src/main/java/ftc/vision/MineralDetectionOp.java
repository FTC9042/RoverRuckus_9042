package ftc.vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

@Autonomous(name = "Mineral Detection")
public class MineralDetectionOp extends LinearOpMode {

  @Override
  public void runOpMode() throws InterruptedException {
      waitForStart();
      while(opModeIsActive()) {
          FrameGrabber frameGrabber = FtcRobotControllerActivity.frameGrabber; //Get the frameGrabber
          frameGrabber.setSaveImages(false);

          frameGrabber.grabSingleFrame(); //Tell it to grab a frame
          while (!frameGrabber.isResultReady()) { //Wait for the result
              Thread.sleep(5); //sleep for 5 milliseconds
          }

          //Get the result
          ImageProcessorResult imageProcessorResult = frameGrabber.getResult();
          MineralColorResult result = (MineralColorResult) imageProcessorResult.getResult();

          MineralColorResult.MineralColor color = result.getCenter();

          telemetry.addData("Result", result); //Display it on telemetry
          telemetry.update();
          //wait before quitting (quitting clears telemetry)
          Thread.sleep(1000);
      }
  }
}
