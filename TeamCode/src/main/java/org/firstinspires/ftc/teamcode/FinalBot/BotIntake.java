package org.firstinspires.ftc.teamcode.FinalBot;

import android.hardware.Sensor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/*
    This class simply controls the intake system with motors/sensor
 */


public class BotIntake {

    DistanceSensor touch;//Distance sensor to detect a successful intake

    DcMotor[] motors = new DcMotor[2];//motors for the intake system
    //0:Left
    //1:Right

    public BotIntake(DcMotor left, DcMotor right, DistanceSensor dist){
        motors[0] = left;
        motors[1] = right;
        this.touch = dist;
    }//basic constructor to create objects

    public void intakeStart(){
        /*(!)*/motors[0].setPower(1);//replace 1 with -1 or 1 depending on motor setup
        /*(!)*/motors[1].setPower(1);//replace 1 with -1 or 1 depending on motor setup
    }//Starts the intake system

    public void intakeStop(){
        motors[0].setPower(0);
        motors[1].setPower(0);
    }//stops the intake system

    public boolean intakeFill(){
        /*(!)*/return touch.getDistance(DistanceUnit.INCH) <= 2;//returns if distance between block and sensor is less that 2in, adjust values later
    }//returns true if intake contains a block

}
