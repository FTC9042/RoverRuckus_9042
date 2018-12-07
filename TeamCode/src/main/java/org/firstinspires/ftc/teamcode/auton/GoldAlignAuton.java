package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.DriveTrain;
import org.firstinspires.ftc.teamcode.util.GoldAlignUtil;
import org.firstinspires.ftc.teamcode.util.Logging;

@Autonomous(name = "Gold Align Auton")
public class GoldAlignAuton extends LinearOpMode {

    public int offset = 250;
    public DriveTrain driveTrain;
    private GoldAlignUtil util;

    @Override
    public void runOpMode() {
        driveTrain = new DriveTrain(hardwareMap);
        util = new GoldAlignUtil(hardwareMap);
        util.init();

        waitForStart();

        while (opModeIsActive() && util.isAligned() != true){
            double xpos = util.getXPosition();
            alignToGold(xpos);
            Logging.log("X Position", xpos,telemetry);
            Logging.log("offset", offset,telemetry);
            telemetry.update();
        }
        driveTrain.stop();
        util.stop();
    }

    public void alignToGold(double dir){
        if(dir - offset > 0){
            driveTrain.setPower(-0.05, 0.05);
            telemetry.addData("Rotation","turning clockwise");
        }
        if(dir - offset < 0){
            driveTrain.setPower(0.05, -0.05);
            telemetry.addData("Rotation","turning counterclockwise");
        }
    }
}
