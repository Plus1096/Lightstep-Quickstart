package org.firstinspires.ftc.teamcode.mecanum;

import com.lumen.lightstep.following.Drivetrain;
import com.lumen.lightstep.kinematics.MecanumDriveKinematics;
import com.lumen.lightstep.kinematics.MecanumWheelSpeeds;
import com.lumen.lightstep.math.Vector3d;

import org.firstinspires.ftc.teamcode.Util;

public class MecanumDt implements Drivetrain {
    private final MecanumDriveKinematics kinematics;
    private final MecanumChassis chassis;
    private final double wheelCircumference;

  public  MecanumDt(MecanumDriveKinematics kinematics, MecanumChassis chassis,double wheelDiameterMM){
        this.kinematics = kinematics;
        this.chassis = chassis;
        this.wheelCircumference = wheelDiameterMM*Math.PI;
    }
    @Override
    public void applyRobotRelativeVelocities(double xCoordsPerSecond, double yCoordsPerSecond, double headingRadiansPerSecond) {
        MecanumWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(new Vector3d(xCoordsPerSecond, yCoordsPerSecond, headingRadiansPerSecond));
        chassis.setWheelVels(convert(wheelSpeeds.frontRightSpeed()),convert(wheelSpeeds.frontLeftSpeed()),convert(wheelSpeeds.backRightSpeed()),convert(wheelSpeeds.backLeftSpeed()));
    }

    private double convert(double speed) {
        return Util.ftcToMeters(speed) / wheelCircumference;
    }

    @Override
    public void applyFieldRelativeVelocities(double xCoordsPerSecond, double yCoordsPerSecond, double headingRadiansPerSecond, double currentHeadingRad) {
        MecanumWheelSpeeds wheelSpeeds = kinematics.toWheelSpeeds(new Vector3d(xCoordsPerSecond, yCoordsPerSecond, headingRadiansPerSecond),currentHeadingRad);
        chassis.setWheelVels(convert(wheelSpeeds.frontRightSpeed()),convert(wheelSpeeds.frontLeftSpeed()),convert(wheelSpeeds.backRightSpeed()),convert(wheelSpeeds.backLeftSpeed()));
    }
}
