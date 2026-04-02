package org.firstinspires.ftc.teamcode;

import com.lumen.lightstep.following.Drivetrain;
import com.lumen.lightstep.following.Localizer;
import com.lumen.lightstep.math.Vector3d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mecanum.MecanumConstants;

public final class Constants {
 public static  Class<MecanumConstants> constants = MecanumConstants.class;
    public static Localizer getLocalizer(HardwareMap hardwareMap, Vector3d start){
        try {
            return (Localizer) constants.getMethod("getLocalizer", HardwareMap.class, Vector3d.class).invoke(null,hardwareMap,start);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    public static Drivetrain getDrivetrain(HardwareMap hardwareMap, double maxVel){
        try {
            return (Drivetrain) constants.getMethod("getDrivetrain", HardwareMap.class, double.class).invoke(null,hardwareMap,maxVel);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static double maxForwardVel=1;
}
