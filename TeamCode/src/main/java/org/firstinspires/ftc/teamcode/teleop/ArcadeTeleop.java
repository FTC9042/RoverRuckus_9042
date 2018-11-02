package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.DriveTrain;

@TeleOp(name = "ArcadeTeleop")
public class ArcadeTeleop extends OpMode {
    DriveTrain driveTrain;

    @Override
    public void init() {
        driveTrain = new DriveTrain(this.hardwareMap);
    }

    @Override
    public void loop() {
        float yval = gamepad1.left_stick_y;
        float xval = gamepad1.right_stick_x;


        float lpwr = (float) Math.pow(((yval - xval)), 3);
        float rpwr = (float) Math.pow((yval + xval), 3);

        driveTrain.setPower(lpwr, rpwr);

    }
}
