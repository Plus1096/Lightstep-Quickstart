package org.firstinspires.ftc.teamcode.tuning;

import com.bylazar.configurables.annotations.Configurable;
import com.lumen.lightstep.control.PIDCoefficients;
import com.lumen.lightstep.control.PIDFController;
import com.lumen.lightstep.following.Drivetrain;
import com.lumen.lightstep.following.Follower;
import com.lumen.lightstep.following.FollowerPIDCoefficients;
import com.lumen.lightstep.following.Localizer;
import com.lumen.lightstep.following.PathBuilder;
import com.lumen.lightstep.math.Vector2d;
import com.lumen.lightstep.math.Vector3d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Constants;

@TeleOp(name = "TranslationTuner",group = "Tuning")
@Configurable
public class HeadingTuner extends LinearOpMode {


    public static double p;
    public static double i;
    public static double d;
    public static double targetHeadingRad = Math.PI;

    @Override
    public void runOpMode() throws InterruptedException {
        Drivetrain dt = Constants.getDrivetrain(hardwareMap,1);
        Localizer localizer = Constants.getLocalizer(hardwareMap,new Vector3d(0, 0, 0));
        Follower follower = new Follower(dt, getEmpty(), getEmpty(), getEmpty(), getEmpty(), getEmpty(), getEmpty(), localizer);
        Vector2d target = new Vector2d(72, 0);

        while (opModeIsActive()) {
            if (gamepad1.aWasPressed()) {
                follower.queue(new PathBuilder().setShouldPrecalculate(false).lineThrough(targetHeadingRad, target).build());
            }
            follower.updateCoefficients(null, new FollowerPIDCoefficients(PIDCoefficients.empty(),PIDCoefficients.empty(), new PIDCoefficients(p, i, d)));
            follower.update();
            telemetry.addLine("Click A to Start!");
            telemetry.addData("Pos", localizer.getPos());
            telemetry.addData("Target", target);
            telemetry.update();
        }
    }

    public PIDFController getEmpty() {
        return new PIDFController(0, 0, 0, 0);
    }


}
