package org.firstinspires.ftc.teamcode.FinalBot;

import android.graphics.Color;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

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

        intake = new BotIntake(map.get(DcMotor.class, "intakeLeft"),map.get(DcMotor.class, "intakeRight"),map.get(ModernRoboticsI2cRangeSensor.class,"intakeDistance"));
        //initializes intake motors and touch sensor(can replace with distance sensor in the future)

        /*[!]*/arm = new  BotArm(null, null);//placeholder replace null later (!)

        colors = map.get(ColorSensor.class, "colorSensor");//initializes color sensor

        gyro = map.get(GyroSensor.class, "gyroscope");

    }//basic constructor for initializing from a HardwareMap, use this in implementations of this class

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
            double steps = 0.1;//move in steps of 0.1 inches (diagonally)
            double target = Math.sqrt(Math.abs(x * x) + Math.abs(y * y));//sets diagonal target
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

    public void placeBlock(double height){

        //implement code here

    }//places block from internal storage onto tower

    public boolean intake(double timeout){
        ElapsedTime time = new ElapsedTime(0);//timer to attempt a blind intake for
        wheels.setPower(1);//wheels run until end of method

        time.reset();//resets time to 0
        intake.intakeStart();//starts intake system

        while(!intake.intakeFill() && time.seconds() < timeout)//runs until intake is filled, or time has timed out

        wheels.setPower(0);//stops wheels
        intake.intakeStop();//stops intake

        return intake.intakeFill();//returns whether or not a block has been been inputted into bay
    }//attempts to fetch a block until a certain amount of time, exits if block is already in bay, returns true if at end of method, block is in bay

    public boolean intake(double timeout, Color color, int dir /*-1 or 1, sets direction of travel -1 for left, 1 for right*/, boolean useArm ){

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
            grabBlock();//attempts to grab block via arm
        }else{
            intake(timeout - time.seconds());//attepts to intake block with time left
        }

        return intake.intakeFill();//returns whether or not intake has successfully in-took a block
    }//attempts to fetch a block matching the color profile, use for green path, exits if block is already in bay, returns at end of method, true, if block is in bay

    public void grabTray(){

        //implement code here

    }//moves bot arm to grab tray on the ground, when called again, release tray

    public void grabBlock(){

        //implement code here

    }//grabs a block and places into storage

    public void calibrateGyro(){
        gyro.calibrate();//starts gyro calibration
        while(gyro.isCalibrating());//waits until gyro finishes calibrating
    }//calibrates the gyroscope

}
