package org.firstinspires.ftc.teamcode.tuning;

import com.lumen.lightstep.control.PIDFController;
import com.lumen.lightstep.following.Driveable;
import com.lumen.lightstep.following.Drivetrain;
import com.lumen.lightstep.following.Follower;
import com.lumen.lightstep.following.Input;
import com.lumen.lightstep.following.Localizer;
import com.lumen.lightstep.math.Vector3d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Constants;

@TeleOp(name = "maxVelFinder",group = "Tuning")
public class MaxVelFinder extends LinearOpMode {
    private double maxRadPerSec;
    private double maxYVel;
    private double maxXVel;

    @Override
    public void runOpMode() throws InterruptedException {
        Drivetrain dt = Constants.getDrivetrain(hardwareMap,1);
        Localizer localizer = Constants.getLocalizer(hardwareMap,new Vector3d(0, 0, 0));
        Follower follower = new Follower(dt, getEmpty(), getEmpty(), getEmpty(), getEmpty(), getEmpty(), getEmpty(), localizer);
   Driveable driveable= follower.getArcadeDrive(false,99999999,9999999);

   while (opModeIsActive()){
    if(gamepad1.a){
        driveable.update(new Input(0, -1,0));
        if(localizer.getVel().y()>maxYVel){
            maxYVel=localizer.getVel().y();
        }
    }  else if(gamepad1.b){
        driveable.update(new Input(1, 0,0));
        if(localizer.getVel().x()>maxXVel){
           maxXVel=localizer.getVel().x();
        }
    }else if(gamepad1.x){
        driveable.update(new Input(0, 0,1));
        if(localizer.getVel().z()>maxRadPerSec){
            maxRadPerSec=localizer.getVel().z();
        }
    }
    telemetry.addData("maxRadPerSec",maxRadPerSec);
    telemetry.addData("maxXVel",maxXVel);
    telemetry.addData("maxYVel",maxYVel);
    telemetry.update();
   }
    }
    public PIDFController getEmpty() {
        return new PIDFController(0, 0, 0, 0);
    }
}
