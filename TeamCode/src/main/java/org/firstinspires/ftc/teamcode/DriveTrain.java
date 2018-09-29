package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
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
