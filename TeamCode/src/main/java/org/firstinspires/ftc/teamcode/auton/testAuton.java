package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.DriveTrain;

@Autonomous(name = "Test Auton")
public class testAuton extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //4 seconds forward and 2 seconds backward
        DriveTrain dt = new DriveTrain(hardwareMap);
        ElapsedTime et = new ElapsedTime();
        et.reset();
        while(et.seconds()<4){
            dt.setPower(-0.4,-0.4);
        }

        et.reset();
        while(et.seconds()<2){
            dt.setPower(0.4,0.4);
        }

        Servo marker = hardwareMap.servo.get("marker");
        marker.setPosition(0);
        et.reset();
        while(et.seconds()<3){

        }
        marker.setPosition(1);
    }
}
