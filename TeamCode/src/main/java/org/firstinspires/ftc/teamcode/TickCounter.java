package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "TickCounter", group = "test")
public class TickCounter extends OpMode {
    private DriveTrain driveTrain;

    @Override
    public void init() {
        driveTrain = new DriveTrain(this.hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Top Left Position: ", driveTrain.getMotor1().getCurrentPosition());
        telemetry.addData("Back Left Position: ", driveTrain.getMotor2().getCurrentPosition());
        telemetry.addData("Top Right Position: ", driveTrain.getMotor3().getCurrentPosition());
        telemetry.addData("Back Right Position: ", driveTrain.getMotor4().getCurrentPosition());
    }
}
