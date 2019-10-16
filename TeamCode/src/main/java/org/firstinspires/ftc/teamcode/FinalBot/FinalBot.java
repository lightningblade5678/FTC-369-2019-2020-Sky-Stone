package org.firstinspires.ftc.teamcode.FinalBot;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;

public class FinalBot {

    //motors
    private BotWheels wheels;//movement is handled internally

    public BotWheels getWheels(){
        return wheels;
    }//gets the wheels for external use if required

    public BotIntake intake;
    public BotArm arm;

    //sensors

    private ColorSensor color;
    private Gyroscope gyro;

    //constructors

    public FinalBot(HardwareMap map){

        wheels = new BotWheels(map.get(DcMotor.class, "frontLeft"),map.get(DcMotor.class, "frontRight"),map.get(DcMotor.class, "backLeft"),map.get(DcMotor.class, "backRight"));
        //initializes botWheels

        intake = new BotIntake(map.get(DcMotor.class, "intakeLeft"),map.get(DcMotor.class, "intakeRight"),map.get(DistanceSensor.class,"intakeDistance"));
        //initializes intake motors and touch sensor(can replace with distance sensor in the future)

        /*[!]*/arm = new  BotArm(null);//placeholder replace null later (!)

        color = map.get(ColorSensor.class, "colorSensor");//initializes color sensor

        gyro = map.get(Gyroscope.class, "gyroscope");

    }//basic constructor for initializing from a HardwareMap, use this in implementations of this class

    public FinalBot(BotWheels wheels, BotIntake intake, BotArm arm){

        this.wheels = wheels;
        this.intake = intake;
        this.arm = arm;

        //insert sensor code here

    }//basic constructor used to set all parts of the bot

    //start work methods

    public void move(double x, double y) {

        //implement code here

    }//moves bot by x/y values
    /*
        Assume x/y are in inches
        x and y move relative to the front of the bot:
            -x = side to side relative to forward
            -y = forward and back
     */


    public void rotate(double degree) {

        //implement code here (use a gyro, not math to calculate degrees)

    }//rotates bot by degree rotates counterclockwise IE: unit circle

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

    public boolean intake(double timeout, Color color){

        //implement code here
        return false;
    }//attempts to fetch a block matching the color profile, use for green path, exits if block is already in bay, returns at end of method, true, if block is in bay

    public void grab(){

        //implement code here

    }//moves bot arm to grab tray on the ground, when called again, release tray


}
