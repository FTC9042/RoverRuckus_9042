package org.firstinspires.ftc.teamcode.tests;

import org.firstinspires.ftc.teamcode.util.Constants;

public class Proportional {
    public static double P_Constant, target;

    public Proportional(double P, double target){
        this.P_Constant = P;
        this.target = target;
    }

    public double getValue(double pos){
        double displacement = target - (pos/Constants.TICKS_INCH);
        double power = displacement*P_Constant;
        return limit(power);
    }

    private double limit(double power) {
        if(power > 1){
            return 1;
        }
        else if(power < -1){
            return -1;
        }
        return power;
    }
}
