package org.firstinspires.ftc.teamcode.vision;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.DriveTrain;

@Autonomous(name = "Align Gold")
public class GoldAlign extends LinearOpMode {
    // Detector object
    private GoldAlignDetector detector;
    DriveTrain driveTrain;

    @Override
    public void runOpMode() throws InterruptedException {
        // Set up detector
        detector = new GoldAlignDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();

        detector.alignSize = 100;
        detector.alignPosOffset = 0;
        detector.downscale = 0.4;

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA;
        detector.maxAreaScorer.weight = 0.005;

        detector.ratioScorer.weight = 5;
        detector.ratioScorer.perfectRatio = 1.0;

        detector.enable();

        driveTrain = new DriveTrain(hardwareMap);

        driveTrain.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();
        driveTrain.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        ElapsedTime t = new ElapsedTime();
        t.reset();
        driveTrain.setPower(0.2);
        while(t.seconds()<3 && opModeIsActive()){
            telemetry.addData("Driving", "Forward");
            telemetry.update();
        }

        t.reset();
        driveTrain.setPower(-0.3,0.3);
        while(t.seconds()<3 && opModeIsActive()){
            telemetry.addData("Turning", "Left");
            telemetry.update();
        }
        driveTrain.setPower(0);

        t.reset();
        driveTrain.setPower(0.1,-0.1);
        while(opModeIsActive() && t.seconds()<10){
            if(detector.getAligned()){
                break;
            }
        }
        driveTrain.setPower(0);

        t.reset();
        driveTrain.setPower(0.7);
        while (opModeIsActive() && t.seconds()<3){
            telemetry.addData("Hitting", "Mineral");
        }
        driveTrain.setPower(0);
    }
}
