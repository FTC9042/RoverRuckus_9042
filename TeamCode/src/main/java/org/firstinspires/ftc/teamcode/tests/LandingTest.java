package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Landing Test", group = "landing")
public class LandingTest extends LinearOpMode {
    DcMotor extend;
    DcMotor left_lift, right_lift;


    @Override
    public void runOpMode() throws InterruptedException {
        extend = hardwareMap.dcMotor.get("extend");
        left_lift = hardwareMap.dcMotor.get("left");
        right_lift = hardwareMap.dcMotor.get("right");

        extend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_lift.setDirection(DcMotorSimple.Direction.REVERSE);

        extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        left_lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        left_lift.setTargetPosition(0);
        left_lift.setPower(1);
        right_lift.setTargetPosition(0);
        right_lift.setPower(1);

        waitForStart();

        left_lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right_lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        ElapsedTime t = new ElapsedTime();
        t.startTime();
        while(opModeIsActive() & t.seconds()<1){
            left_lift.setPower(0.4);
            right_lift.setPower(0.4);
        }
        left_lift.setPower(0);
        right_lift.setPower(0);
    }
}
