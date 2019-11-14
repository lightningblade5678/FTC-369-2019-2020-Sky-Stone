package org.firstinspires.ftc.teamcode.FinalBot;

import android.graphics.Color;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;


public class FinalBot {

    //motors
    private BotWheels wheels;//movement is handled internally

    public BotWheels getWheels(){
        return wheels;
    }//gets the wheels for external use if required

    public BotIntake intake;
    public BotArm arm;

    //sensors

    private ColorSensor colors;
    private GyroSensor gyro;

    //constructors

    public FinalBot(HardwareMap map){

        wheels = new BotWheels(map.get(DcMotor.class, "frontLeft"),map.get(DcMotor.class, "frontRight"),map.get(DcMotor.class, "backLeft"),map.get(DcMotor.class, "backRight"));
        //initializes botWheels

        intake = new BotIntake(map.get(DcMotor.class, "intakeLeft"),map.get(DcMotor.class, "intakeRight"),map.get(ModernRoboticsI2cRangeSensor.class,"intakeDistance"),map.get(Servo.class, "finger"));
        //initializes intake motors and touch sensor(can replace with distance sensor in the future)

        /*[!]*/arm = new BotArm(map.get(DcMotor.class, "baseMotor"),map.get(Servo.class, "wristServo"), map.get(Servo.class, "handServo")); //change motor names

        colors = map.get(ColorSensor.class, "colorSensor");//initializes color sensor

        gyro = map.get(GyroSensor.class, "gyroscope");

    }//basic constructor for initializing from a HardwareMap, use this in implementations of this class

    public boolean detectColor() {
        if(colors.red() > 10 && colors.green() > 10 && colors.blue() > 10){
            return true;
        }
        else{
            return false;
        }
    }//Detects if something is in front of robot

    public FinalBot(BotWheels wheels, BotIntake intake, BotArm arm){

        this.wheels = wheels;
        this.intake = intake;
        this.arm = arm;

        //insert sensor code here

    }//basic constructor used to set all parts of the bot

    //start work methods

    public void move(double x, double y) {

        double threshold = 5;//5 degree error threshold

        calibrateGyro();

        double heading = gyro.getHeading();

        if(x != 0 && y != 0) {

            /*!*/
            double steps = 1;//move in steps of 1 inch (diagonally)
            double target = Math.sqrt((x * x) + (y * y));//sets diagonal target
            double angle = Math.atan(y / x);//use later

            for (double i = 0; i < target; i += steps) {
                wheels.moveRelativeY(Math.sin(angle) * steps, Math.abs(y) / y);//moves y
                wheels.moveRelativeX(Math.cos(angle) * steps, Math.abs(x) / x);//moves x

                if(Math.abs(gyro.getHeading()-heading) > threshold){
                    rotate(heading-gyro.getHeading());
                    calibrateGyro();
                    heading = gyro.getHeading();
                }//corrects course if bot gets thrown off heading

            }//moves the bots in a "staircase" with a overall linear traverse of steps inches

        }else if(x == 0){//if is simple linear grid motion just call the methods

            wheels.moveRelativeY(y, Math.abs(y)/y);

            if(Math.abs(gyro.getHeading()-heading) > threshold){
                rotate(heading-gyro.getHeading());
            }//corrects course if bot is thrown off heading

        }else if(y == 0){

            wheels.moveRelativeX(x, Math.abs(x)/x);

            if(Math.abs(gyro.getHeading()-heading) > threshold){
                rotate(heading-gyro.getHeading());
            }//corrects course if bot is thrown off heading

        }

    }//moves bot by x/y values
    /*
        Assume x/y are in inches
        x and y move relative to the front of the bot:
            -x = side to side relative to forward
            -y = forward and back


            +x = right
            -x = left

            +y = forward
            -y = back
     */

    public void rotate(double degree){
        rotate(degree,1);
    }//helper method for simplicity


    public void rotate(double degree, double speed /*ALWAYS set this to 1*/ ) {

        calibrateGyro();

        double target = gyro.getHeading()+degree;

        wheels.setPower(0,Math.abs(degree)/degree*speed);
        wheels.setPower(1,-Math.abs(degree)/degree*speed);
        wheels.setPower(2,(Math.abs(degree)/degree*0.95)*speed);
        wheels.setPower(3,(-Math.abs(degree)/degree*0.95)*speed);//sets wheels to rotate clockwise if degree is positive

        if(degree >= 0){//clockwise

            while(gyro.getHeading() < target);//waits until degree is greater than or equal to target loc

        }else{//counterclockwise

            while(gyro.getHeading() > target);//waits until degree is less than or equal to target loc

        }

        wheels.setPower(0);//done

        double threshold = 5;//5 degree error threshold

        if(Math.abs(gyro.getHeading() - target) > threshold){
            rotate(target-gyro.getHeading(), speed/2);//try again but slower (less room for error as any overshoot is likely caused by too much speed on the motor)
        }//corrects any errors above threshold

    }//rotates bot by degree rotates clockwise IE: compass

    public void placeBlock(int height){

        // [!] [!] CHANGE CODE TO ACCOUNT FOR ARM DEGREE BEFORE MOVEMENT [!] [!]

         /*
        Level 1 = set degree to 205.4 || distance is sqrt.160 + 3
        Level 2 = set degree to 188.21 || distance is sqrt.192 + 3
        Level 3 = set degree to 171.79 || distance is sqrt.192 + 3
        Level 4 = set degree to 154.6 || distance is sqrt.160 + 3
        Level 5 = set degree to 134.42 || distance is sqrt.96 + 3
     */

        double[] heightDistance = new double[2];
        heightDistance[0] = Math.sqrt(160) + 3;
        heightDistance[1] = Math.sqrt(192) + 3;
        //heightDistance[2] = Math.sqrt(192) + 3;
        //heightDistance[3] = Math.sqrt(160) + 3;
        //heightDistance[4] = Math.sqrt(96) + 3;

        double[] heightDegree = new double[2];
        heightDegree[0] = 205.4;
        heightDegree[1] = 188.21;
        //heightDegree[2] = 171.79;
        //heightDegree[3] = 154.6;
        //heightDegree[4] = 134.42;

        //gets robot into position to place
    move(0, heightDistance[height]);
    arm.setGrabPos();
    arm.handGrab(true);
    arm.baseRotateDegree(arm.baseMotor, 45, 1); // [!] Check parameters
    arm.rotateWrist(180);

        //places block
    arm.baseRotateDegree(arm.baseMotor, heightDegree[height], 1 );
    arm.handGrab(false);
    arm.baseRotateDegree(arm.baseMotor,60, 1 ); //lifts arm for next movements [!] Check parameters


    }//places block from internal storage onto tower

    public double intake(double timeout){

        double currCount = wheels.getWheel(2).getCurrentPosition();//current position of encoder

        intake.toggleFinger();//ensures intake is open if needed

        ElapsedTime time = new ElapsedTime(0);//timer to attempt a blind intake for
        wheels.setPower(1);//wheels run until end of method

        time.reset();//resets time to 0
        intake.intakeStart();//starts intake system

        while(!intake.intakeFill() && time.seconds() < timeout)//runs until intake is filled, or time has timed out

        wheels.setPower(0);//stops wheels
        intake.intakeStop();//stops intake

        intake.toggleFinger();//closes/opens intake

        return ( wheels.getWheel(2).getCurrentPosition()-currCount ) / wheels.getCountsPerInch() / wheels.getDistanceModY();//returns difference in encoder position in inches
    }//attempts to fetch a block until a certain amount of time, exits if block is already in bay, returns distance travelled (Y)

    public double intake(double timeout, int dir /*-1 or 1, sets direction of travel -1 for left, 1 for right*/, boolean useArm ){

        double currCount = wheels.getWheel(3).getCurrentPosition();//current position of encoder

        intake.toggleFinger();//ensures intake is open if needed

        //sets wheels to move l/r

        wheels.setPower(0, dir);//frontleft
        wheels.setPower(1, -dir);//frontright

        wheels.setPower(2, -dir*0.95);//backleft
        wheels.setPower(3, dir*0.95);//backright

        //starts timer

        ElapsedTime time = new ElapsedTime(0);
        time.reset();

        colors.enableLed(true);
        for(float[] hsvValues = {0F, 0F, 0F};time.seconds() < timeout && (hsvValues[0] >= 0 && hsvValues[0] <= 100 && hsvValues[1] > .1 && hsvValues[1] < .5 && hsvValues[2] > .005 && hsvValues[2] < .6);Color.RGBToHSV(colors.red(), colors.green(), colors.blue(), hsvValues) );

        wheels.setPower(0);

        if(useArm) {
            //rotate bot 180 degrees [!]
            grabBlock();//attempts to grab block via arm
        }else{
            move(0,-intake(timeout - time.seconds()) / wheels.getCountsPerInch()/ wheels.getDistanceModX());//attepts to intake block with time left and then moves back into position
        }

        intake.toggleFinger();//closes/opens intake

        return ( wheels.getWheel(3).getCurrentPosition()-currCount ) / wheels.getCountsPerInch() / wheels.getDistanceModX();//returns difference in encoder position in inches
    }//attempts to fetch a block matching the color profile, use for green path, exits if block is already in bay, returns distance traveled (X)
    public void grabTray(){

        /*
        arm.baseMotor.setPower(???); //set for ??? rotations
        arm.wristServo.setPosition(???); //set to ??? position
        wheels.setPower(???); //set for ??? time/rotations

        arm.wristServo.setPosition(???); //release tray from wrist
        arm.baseMotor.setPower(???); //move arm up from tray
        */


    }//moves bot arm to grab tray on the ground, when called again, release tray

    public void grabBlock(){

        /*

        wheels.setPower(???); //set for ??? rotations
        arm.baseMotor.setPower(???); //set for ??? rotations
        arm.wristServo.setPosition(???); //set to ??? position
        arm.handGrab(); // grab block

        arm.baseMotor.setPower(???); //set for ??? rotations
        arm.wristServo.setPosition(???); //set to ??? position
        arm.handGrab(); // release block

        */

    }//grabs a block from behind robot and places into storage

    public void calibrateGyro(){
        gyro.calibrate();//starts gyro calibration
        while(gyro.isCalibrating());//waits until gyro finishes calibrating
    }//calibrates the gyroscope

}