package org.firstinspires.ftc.teamcode;

import static java.lang.Math.PI;

import com.lumen.lightstep.following.Localizer;
import com.lumen.lightstep.math.Vector3d;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;

import java.util.Objects;

public class PinpointLocalizer implements Localizer {
    public static final class PinpointConstants {
        private final double xOffsetMM;
        private final double yOffsetMM;
        private final Odometry.Direction xDir;
        private final Odometry.Direction yDir;
        private final double encoderResolution;

        public PinpointConstants(double xOffsetMM, double yOffsetMM, Odometry.Direction xDir,Odometry.Direction yDir,  double ticksPerMM) {
            this.xOffsetMM = xOffsetMM;
            this.yOffsetMM = yOffsetMM;
            this.xDir = xDir;
            this.yDir = yDir;
            this.encoderResolution = ticksPerMM;
        }    public PinpointConstants(double xOffsetMM, double yOffsetMM, Odometry.Direction xDir,Odometry.Direction yDir,Odometry podType) {
            this.xOffsetMM = xOffsetMM;
            this.yOffsetMM = yOffsetMM;
            this.xDir = xDir;
            this.yDir = yDir;
            this.encoderResolution= podType.ticksPerMM;
        }

        public double xOffsetMM() {
            return xOffsetMM;
        }

        public double yOffsetMM() {
            return yOffsetMM;
        }

        public double encoderResolution() {
            return encoderResolution;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            PinpointConstants that = (PinpointConstants) obj;
            return Double.doubleToLongBits(this.xOffsetMM) == Double.doubleToLongBits(that.xOffsetMM) &&
                    Double.doubleToLongBits(this.yOffsetMM) == Double.doubleToLongBits(that.yOffsetMM) &&
                    Double.doubleToLongBits(this.encoderResolution) == Double.doubleToLongBits(that.encoderResolution);
        }

        @Override
        public int hashCode() {
            return Objects.hash(xOffsetMM, yOffsetMM, encoderResolution);
        }

        @Override
        public String toString() {
            return "PinpointConstants[" +
                    "xOffsetMM=" + xOffsetMM + ", " +
                    "yOffsetMM=" + yOffsetMM + ", " +
                    "encoderResolution=" + encoderResolution + ']';
        }
    }
    private final GoBildaPinpointDriver pinpoint;

    public PinpointLocalizer(GoBildaPinpointDriver pinpoint,PinpointConstants constants) {
        this.pinpoint = pinpoint;
        config(constants);
    }    public PinpointLocalizer(GoBildaPinpointDriver pinpoint, Vector3d start,PinpointConstants constants) {

        this.pinpoint = pinpoint;
        config(constants);
        pinpoint.setPosition(new Pose2D(DistanceUnit.INCH,(start.x()/144)*141.5,(start.y()/144)*141.5,AngleUnit.RADIANS, start.z()));
    }

    private void config(PinpointConstants constants) {
        pinpoint.setOffsets(constants.xOffsetMM,constants.yOffsetMM,DistanceUnit.MM);
        pinpoint.setEncoderResolution(constants.encoderResolution,DistanceUnit.MM);
pinpoint.setEncoderDirections(constants.xDir== Odometry.Direction.Forward? GoBildaPinpointDriver.EncoderDirection.FORWARD: GoBildaPinpointDriver.EncoderDirection.REVERSED,constants.yDir== Odometry.Direction.Forward? GoBildaPinpointDriver.EncoderDirection.FORWARD: GoBildaPinpointDriver.EncoderDirection.REVERSED);
    }
    public void resetPosAndIMU(){
        pinpoint.resetPosAndIMU();
    }

    @Override
    public Vector3d getPos() {
        return new Vector3d((pinpoint.getPosX(DistanceUnit.INCH)/141.5)*144,
                (pinpoint.getPosY(DistanceUnit.INCH)/141.5)*144,pinpoint.getHeading(AngleUnit.RADIANS)+ PI);
    }
@Override
    public void update() {
        pinpoint.update();
    }

    @Override
    public Vector3d getVel() {
        return new Vector3d((pinpoint.getVelX(DistanceUnit.INCH)/141.5)*144,
                (pinpoint.getVelY(DistanceUnit.INCH)/141.5)*144,pinpoint.getHeadingVelocity(UnnormalizedAngleUnit.RADIANS));
    }

}
