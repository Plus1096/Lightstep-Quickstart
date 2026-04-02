package org.firstinspires.ftc.teamcode;

import static java.lang.Math.PI;

public enum Odometry {
    goBilda_4_Bar(2000/(32*PI)),
    goBilda_Swingarm(2000/(48*PI)),
    Optii_V1_V2(4096/(35*PI)),
    Elc_V2(4000/(35*PI));
    public final double ticksPerMM;

    Odometry(double ticksPerMM){
        this.ticksPerMM = ticksPerMM;
    }
public enum Direction{
        Reversed
    ,Forward
}
}
