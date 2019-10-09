package org.firstinspires.ftc.teamcode.FinalBot;

/*
    This class contains the robots' wheels and the methods used to manipulate them
 */

import com.qualcomm.robotcore.hardware.DcMotor;

public class BotWheels {

    private DcMotor[] wheels = new DcMotor[4];//an array storing the wheels of the bot
    /*
    0: Front left
    1: Front right
    2: Back left
    3: Back right
    */

    public BotWheels(DcMotor frontLeft,DcMotor frontRight, DcMotor backLeft, DcMotor backRight){
        wheels[0] = frontLeft;
        wheels[1] = frontRight;
        wheels[2] = backLeft;
        wheels[3] = backRight;
    }//main constructor, sets vals of all DcMotors

    public BotWheels(DcMotor.RunMode mode,DcMotor frontLeft,DcMotor frontRight, DcMotor backLeft, DcMotor backRight){
        this(frontLeft,frontRight,backLeft,backRight);
        setMode(mode);
    }//secondary constructor used to set all motors and their runMode

    //start set/get methods

    public void setMode(DcMotor.RunMode mode){
        for(DcMotor m:wheels){
            m.setMode(mode);
        }//sets runmode of all motors
    }//sets mode of all the motors to mode

    public void setMode(int i, DcMotor.RunMode mode){
        wheels[i].setMode(mode);
    }//sets mode of an individual motor

    public void setPower(double power){
        for(DcMotor m:wheels){
            m.setPower(power);
        }//sets power of all motors
    }//sets the power of all motors

    public void setPower(int i, double power){
        wheels[i].setPower(power);
    }//sets the power of an individual motor

    //start work methods

}
