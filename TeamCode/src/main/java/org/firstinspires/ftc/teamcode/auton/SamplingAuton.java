package org.firstinspires.ftc.teamcode.auton;

import com.disnodeteam.dogecv.detectors.roverrukus.SamplingOrderDetector;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.MineralDetection;

public class SamplingAuton extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        SamplingOrderDetector.GoldLocation location = SamplingOrderDetector.GoldLocation.UNKNOWN;
        MineralDetection detection = new MineralDetection(hardwareMap);
        detection.init();

        while(!opModeIsActive()){
            location = detection.getMineralPos();
            telemetry.addData("Position", location.toString());
            telemetry.update();
        }

        waitForStart();

        if(opModeIsActive()){
            while(location.compareTo(SamplingOrderDetector.GoldLocation.UNKNOWN) == 0){
                telemetry.addData("Position", location.toString());
                telemetry.update();
                if(!opModeIsActive()){
                    break;
                }
            }
        }
    }
}
