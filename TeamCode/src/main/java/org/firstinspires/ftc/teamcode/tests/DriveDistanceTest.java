package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.DriveTrain;

@Autonomous(name = "Drive Distance Test")
public class DriveDistanceTest extends LinearOpMode{
    private DriveTrain driveTrain;

    @Override
    public void runOpMode() throws InterruptedException {
        driveTrain = new DriveTrain(this.hardwareMap);

        double target = 25;

        Proportional proportional = new Proportional(Constants.P_CONSTANT_DRIVING, target);

        driveTrain.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();
        driveTrain.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while(opModeIsActive()){

            if(Math.abs(target * Constants.TICKS_INCH - Math.abs(driveTrain.getAveragePositionLeft())) < Constants.TOLERANCE){
                break;
            }

            if(Math.abs(target * Constants.TICKS_INCH - Math.abs(driveTrain.getAveragePositionRight())) < Constants.TOLERANCE){
                break;
            }

            double leftPower = proportional.getValue(driveTrain.getAveragePositionLeft());
            double rightPower = proportional.getValue(driveTrain.getAveragePositionRight());

            driveTrain.setPower(leftPower,rightPower);

            telemetry.addData("Left Position", driveTrain.getAveragePositionLeft());
            telemetry.addData("Right Position", driveTrain.getAveragePositionRight());
            telemetry.update();
        }

        driveTrain.setPower(0);

        while(opModeIsActive()){
            telemetry.addData("Left Position", driveTrain.getAveragePositionLeft());
            telemetry.addData("Right Position", driveTrain.getAveragePositionRight());
            telemetry.update();
        }
    }
}
