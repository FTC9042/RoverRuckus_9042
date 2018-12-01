package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.util.DriveTrain;

@Autonomous(name = "TickCounter", group = "Driving Forward")
public class TickTest extends OpMode {
    DriveTrain driveTrain;

    @Override
    public void init() {
        driveTrain = new DriveTrain(this.hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Top Left: ",driveTrain.getMotor1().getCurrentPosition());
        telemetry.addData("Back Left: ",driveTrain.getMotor2().getCurrentPosition());
        telemetry.addData("Top Right: ",driveTrain.getMotor3().getCurrentPosition());
        telemetry.addData("Back Right: ",driveTrain.getMotor4().getCurrentPosition());
    }
}
