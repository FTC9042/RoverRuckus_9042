package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {
    private DcMotor motor1, motor2; //Left Motors
    private DcMotor motor3, motor4; //Right Motors

    //TODO consider using the DcMotorEx class
    public DriveTrain(HardwareMap map){
        motor1 = map.dcMotor.get(Constants.DRIVE_MOTOR_TL);
        motor2 = map.dcMotor.get(Constants.DRIVE_MOTOR_BL);
        motor3 = map.dcMotor.get(Constants.DRIVE_MOTOR_TR);
        motor4 = map.dcMotor.get(Constants.DRIVE_MOTOR_BR);

        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor4.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setPower(double l1, double l2, double r1, double r2){
        motor1.setPower(l1);
        motor2.setPower(l2);
        motor3.setPower(r1);
        motor4.setPower(r2);
    }

    public void setPower(double left, double right){
        motor1.setPower(left);
        motor2.setPower(left);
        motor3.setPower(right);
        motor4.setPower(right);
    }

    public void setPower(double power){
        setPower(power,power);
    }

    public DcMotor getMotor1() {
        return motor1;
    }

    public DcMotor getMotor2() {
        return motor2;
    }

    public DcMotor getMotor3() {
        return motor3;
    }

    public DcMotor getMotor4() {
        return motor4;
    }
}
