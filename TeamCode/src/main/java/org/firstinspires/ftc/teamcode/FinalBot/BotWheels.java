package org.firstinspires.ftc.teamcode.FinalBot;

/*
    This class contains the robots' wheels and the methods used to manipulate them
 */

import com.qualcomm.robotcore.hardware.DcMotor;

public class BotWheels {

    //constants (taken from demo programs)
    private final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    private final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    private final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    private final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    /*!*/private final double distanceModX = 1;//how much to modify distance based off of calibration software
    /*!*/private final double distanceModY = 1;
    //

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

    public void moveRelativeY(double distance, double power){

        DcMotor.RunMode temp = wheels[0].getMode();//saves runmode of motors for later reset
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);//sets motor runmode

        int[] yTargets = new int[wheels.length];//creates an array of x target count rotations

        for(int i = 0; i < yTargets.length; i++){
            yTargets[i] = wheels[i].getCurrentPosition() + (int)( (distance*distanceModY) * COUNTS_PER_INCH);//sets target count LOC for each wheel
        }

        setMode(DcMotor.RunMode.RUN_TO_POSITION);
        setPower(power);//sets power and begins run

        while(wheels[0].isBusy() || wheels[1].isBusy()|| wheels[2].isBusy()|| wheels[3].isBusy());//waits until encoders are done running

        setPower(0);//stops wheels command is done

        setMode(temp);//resets runmode back to original

    }//moves relative to the bots 'y' axis or up/down, bias up

    public void moveRelativeX(double distance, double power){

        DcMotor.RunMode temp = wheels[0].getMode();//saves runmode of motors for later reset
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);//sets motor runmode

        int[] xTargets = new int[wheels.length];//creates an array of x target count rotations

        xTargets[0] = wheels[0].getCurrentPosition() + (int)( (distance*distanceModX) * COUNTS_PER_INCH );
        xTargets[1] = wheels[1].getCurrentPosition() - (int)( (distance*distanceModX) * COUNTS_PER_INCH );//reversed target motor LOC
        xTargets[2] = wheels[2].getCurrentPosition() - (int)( (distance*distanceModX) * COUNTS_PER_INCH );//reversed target motor LOC
        xTargets[3] = wheels[3].getCurrentPosition() + (int)( (distance*distanceModX) * COUNTS_PER_INCH );

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setPower(0, power);
        setPower(1, -power);
        setPower(2, -power);
        setPower(3, power);//sets power of the bot

        while(wheels[0].isBusy() || wheels[1].isBusy()|| wheels[2].isBusy()|| wheels[3].isBusy());//waits until encoders are done running

        setPower(0);//stops wheels command is done

        setMode(temp);//resets runmode back to original

    }//moves bot relative to X axis, or left/right bias right



}
