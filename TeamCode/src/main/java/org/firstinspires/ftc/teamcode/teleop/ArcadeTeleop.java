package org.firstinspires.ftc.teamcode.teleop;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.DriveTrain;

@TeleOp(name = "ArcadeTeleop")
public class ArcadeTeleop extends OpMode {

    DriveTrain dt;

    @Override
    public void init() {
        dt = new DriveTrain(hardwareMap);
    }

    @Override
    public void loop() {
        float yval = gamepad1.left_stick_y;
        float xval = gamepad1.right_stick_x;


        float lpwr = (float) Math.pow(((yval - xval)), 3);
        float rpwr = (float) Math.pow((yval + xval), 3);

        dt.setPower(lpwr,rpwr);

        Servo dump = hardwareMap.servo.get("dump");

        if (gamepad2.right_bumper){
            dump.setPosition(0);
        }
        if(gamepad2.left_bumper) {
            dump.setPosition(1);
        }


    }
}