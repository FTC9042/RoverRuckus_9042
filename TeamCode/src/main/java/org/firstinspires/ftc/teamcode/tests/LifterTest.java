package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Lifter Test")
public class LifterTest  extends OpMode {
    DcMotor extend;
    DcMotor left_lift, right_lift;

    @Override
    public void init() {
        extend = hardwareMap.dcMotor.get("0");
        left_lift = hardwareMap.dcMotor.get("1");
        right_lift = hardwareMap.dcMotor.get("2");
    }

    @Override
    public void loop() {
        extend.setPower(gamepad1.left_stick_y);
        left_lift.setPower(gamepad1.right_stick_y);
        right_lift.setPower(gamepad1.right_stick_y);
    }
}
