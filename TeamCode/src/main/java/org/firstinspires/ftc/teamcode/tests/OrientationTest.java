package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.util.Gyro;

@Autonomous(name = "Orientation Test")
public class OrientationTest extends OpMode {

    Gyro gyro;

    @Override
    public void init() {
        gyro = new Gyro(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Roll", gyro.getRoll());
        telemetry.addData("Pitch", gyro.getPitch());
        telemetry.addData("Yaw", gyro.getYaw());
        telemetry.update();
    }
}
