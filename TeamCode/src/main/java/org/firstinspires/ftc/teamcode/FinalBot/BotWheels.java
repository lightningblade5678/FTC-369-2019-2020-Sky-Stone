package org.firstinspires.ftc.teamcode.FinalBot;

/*
    This class contains the robots' wheels and the methods used to manipulate them
 */

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BotWheels {

    //constants (taken from demo programs)
    private static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder is 1440, neverest classic 40 are 1120
    private static final double     DRIVE_GEAR_REDUCTION    =  1;     // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    private static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);

    /*
    NOTE:
        front and back motors for this robot are different
        front are tetrix at 152RPM
        back are AndyMark NeveRest Classic 40s at 160RPM

        torque differences as of yet have not been taken into account
    
     */

    /*!*/private static final double distanceModX = 1;//how much to modify distance based off of calibration software
    /*!*/private static final double distanceModY = 1;
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
        for(int i = 0; i < wheels.length;i++){
            setPower(i,power);
        }//sets power of all motors
    }//sets the power of all motors

    public void setPower(int i, double power){
        wheels[i].setPower(power);

        if(i == 1 || i == 3){
            wheels[i].setPower(wheels[i].getPower()*-1);
        }//compensates for back wheels rotating backwards

    }//sets the power of an individual motor

    public DcMotor getWheel(int i){
        return wheels[i];
    }//returns wheel for use

    public double getCountsPerInch(){
        return COUNTS_PER_INCH;
    }//get CPI

    public double getDistanceModX(){
        return distanceModX;
    }//gets modX

    public double getDistanceModY(){
        return distanceModY;
    }//gets modY

    //start work methods

    public void moveRelativeY(double distance, double power){

        DcMotor.RunMode temp = wheels[0].getMode();//saves runmode of motors for lat

        setMode(DcMotor.RunMode.RUN_TO_POSITION);//sets wheels to begin to run to position

        wheels[0].setTargetPosition(wheels[0].getCurrentPosition() + (int)( (distance*distanceModY) * COUNTS_PER_INCH));//sets target count LOC for each wheel
        wheels[1].setTargetPosition(wheels[1].getCurrentPosition() - (int)( (distance*distanceModY) * COUNTS_PER_INCH));

        wheels[2].setTargetPosition(wheels[2].getCurrentPosition() + (int)( (distance*distanceModY) * COUNTS_PER_INCH));//sets target count LOC for each wheel
        wheels[3].setTargetPosition(wheels[3].getCurrentPosition() - (int)( (distance*distanceModY) * COUNTS_PER_INCH));

        //NOTE: only the back 2 motors have encoders

        setPower(power * (Math.abs(distance)/distance));//sets power and begins run

        setPower(2, power*0.95* (Math.abs(distance)/distance) );
        setPower(3, power*0.95* (Math.abs(distance)/distance) );

        while( wheels[0].isBusy() || wheels[1].isBusy() || wheels[2].isBusy() || wheels[3].isBusy());//waits until encoders are done running
        //NOTE: commented out wheels w/o encoders

        setPower(0);//stops wheels command is done

        setMode(temp);//resets runmode back to original

    }//moves relative to the bots 'y' axis or up/down, bias up

    public void moveRelativeX(double distance, double power){

        DcMotor.RunMode temp = wheels[0].getMode();//saves runmode of motors for later reset

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        /*NOTE: only the back 2 wheels have encoders*/
        wheels[0].setTargetPosition(wheels[0].getCurrentPosition() + (int)( (distance*distanceModX) * COUNTS_PER_INCH ));
        wheels[1].setTargetPosition(wheels[1].getCurrentPosition() + (int)( (distance*distanceModX) * COUNTS_PER_INCH ));//reversed target motor LOC
        wheels[2].setTargetPosition(wheels[2].getCurrentPosition() - (int)( (distance*distanceModX) * COUNTS_PER_INCH ));//reversed target motor LOC
        wheels[3].setTargetPosition(wheels[3].getCurrentPosition() - (int)( (distance*distanceModX) * COUNTS_PER_INCH ));

        setPower(0, power* (Math.abs(distance)/distance));
        setPower(1, -power* (Math.abs(distance)/distance));
        setPower(2, -power*0.95* (Math.abs(distance)/distance));
        setPower(3, power*0.95* (Math.abs(distance)/distance));//sets power of the bot

        while( wheels[0].isBusy() || wheels[1].isBusy() || wheels[2].isBusy() || wheels[3].isBusy()) {

        }//waits until encoders are done running
        //NOTE: commented out wheels w/o encoders

        setPower(0);//stops wheels command is done

        setMode(temp);//resets runmode back to original

    }//moves bot relative to X axis, or left/right bias right



}
