package org.firstinspires.ftc.teamcode.mecanum;

import com.bylazar.configurables.annotations.Configurable;
import com.lumen.lightstep.following.Drivetrain;
import com.lumen.lightstep.following.Localizer;
import com.lumen.lightstep.kinematics.MecanumDriveKinematics;
import com.lumen.lightstep.math.Vector2d;
import com.lumen.lightstep.math.Vector3d;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Motor;
import org.firstinspires.ftc.teamcode.Odometry;
import org.firstinspires.ftc.teamcode.PinpointLocalizer;

public final class MecanumConstants {

   public static double wheelDiameter=0;
   public static Localizer getLocalizer(HardwareMap hardwareMap,Vector3d start){
      GoBildaPinpointDriver pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
      return new PinpointLocalizer(pinpoint, start,new PinpointLocalizer.PinpointConstants(0,0, Odometry.Direction.Forward, Odometry.Direction.Forward,0));
   }
   public static Drivetrain getDrivetrain(HardwareMap hardwareMap, double maxVel){
      Motor fr = hardwareMap.get(DcMotor.class, "frontRight")::setPower;
      Motor fl = hardwareMap.get(DcMotor.class, "frontLeft")::setPower;
      Motor br = hardwareMap.get(DcMotor.class, "backRight")::setPower;
      Motor bl = hardwareMap.get(DcMotor.class, "backLeft")::setPower;
   return  new MecanumDt(MecanumConstants.kinematics, new MecanumChassis(fr, fl, br, bl, Config.frMaxPow, Config.flMaxPow, Config.brMaxPow, Config.blMaxPow, maxVel), MecanumConstants.wheelDiameter);
   }
   static MecanumDriveKinematics kinematics =  new MecanumDriveKinematics(new Vector2d(0,0),new Vector2d(-0,0),new Vector2d(0,-0),new Vector2d(-0,-0));
   @Configurable
   static  public class Config{
   static double frMaxPow =1;
static double flMaxPow =1;
static double brMaxPow =1;
static double blMaxPow =1;}
}
