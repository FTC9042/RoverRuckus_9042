package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.DriveTrain;

@TeleOp(name = "Teleop")
public class Teleop extends OpMode {
    DriveTrain driveTrain;

    @Override
    public void init() {
        driveTrain = new DriveTrain(this.hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.right_trigger >0.5){
            driveTrain.setPower(this.gamepad1.left_stick_y*0.3, this.gamepad1.right_stick_y*0.3);
        }else {
            driveTrain.setPower(this.gamepad1.left_stick_y, this.gamepad1.right_stick_y);
        }
    }
}
