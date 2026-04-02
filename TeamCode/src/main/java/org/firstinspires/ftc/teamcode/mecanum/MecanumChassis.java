package org.firstinspires.ftc.teamcode.mecanum;

import org.firstinspires.ftc.teamcode.Motor;
import org.firstinspires.ftc.teamcode.Wheel;

import java.util.Arrays;
import java.util.function.DoubleConsumer;

public class MecanumChassis{

    private DoubleConsumer frontRight;
    private DoubleConsumer frontLeft;
    private DoubleConsumer backRight;
    private DoubleConsumer backLeft;
    private double flMaxPow;

    public void setFlMaxPow(double flMaxPow) {
        this.flMaxPow = flMaxPow;
    }

    public void setFrMaxPow(double frMaxPow) {
        this.frMaxPow = frMaxPow;
    }

    public void setBlMaxPow(double blMaxPow) {
        this.blMaxPow = blMaxPow;
    }

    public void setBrMaxPow(double brMaxPow) {
        this.brMaxPow = brMaxPow;
    }

    private double frMaxPow;
    private double blMaxPow;
    private double brMaxPow;
    private double maxVel;

    MecanumChassis(Motor frontRight, Motor frontLeft, Motor backRight, Motor backLeft, double frMaxPow, double flMaxPow, double brMaxPow, double blMaxPow, double maxVel) {
        this.flMaxPow = flMaxPow;
        this.frMaxPow = frMaxPow;
        this.blMaxPow = blMaxPow;
        this.brMaxPow = brMaxPow;
        this.maxVel = maxVel;
        this.frontRight =(v)-> frontRight.setPower(Math.min(v,this.frMaxPow)/maxVel);
        this.frontLeft = (v)-> frontLeft.setPower(Math.min(v,this.flMaxPow)/maxVel);
        this.backRight =(v)-> backRight.setPower(Math.min(v,this.brMaxPow)/maxVel);
        this.backLeft = (v)-> backLeft.setPower(Math.min(v,this.blMaxPow)/maxVel);

    }
    MecanumChassis (Wheel frontRight, Wheel frontLeft, Wheel backRight, Wheel backLeft){
       this. frontRight = frontRight::setVel;
      this.  frontLeft = frontLeft::setVel;
     this.   backRight = backRight::setVel;
     this.   backLeft = backLeft::setVel;
    }
public void setWheelVels(double fr,double fl,double br, double bl){

   double max= Arrays.stream(new double[]{fr,fl,br,bl}).max().getAsDouble();
        if(max>maxVel){
            double scale = maxVel / max;
            frontRight.accept(fr* scale);
            frontLeft.accept(fl*scale);
            backRight.accept(br*scale);
            backLeft.accept(bl*scale);
        }else {


        frontRight.accept(fr);
        frontLeft.accept(fl);
        backRight.accept(br);
        backLeft.accept(bl);
}}
}
