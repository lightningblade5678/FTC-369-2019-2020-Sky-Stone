package org.firstinspires.ftc.teamcode.FinalBot;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
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

        intake = new BotIntake(map.get(DcMotor.class, "intakeLeft"),map.get(DcMotor.class, "intakeRight"),map.get(TouchSensor.class,"intakeTouch"));
        //initializes intake motors and touch sensor(can replace with distance sensor in the future)

        /*[!]*/arm = new  BotArm(null);//placeholder replace null later (!)

        color = map.get(ColorSensor.class, "colorSensor");//initializes color sensor

        gyro = map.get(Gyroscope.class, "gyroscope");

    }//basic constructor for initializing from a hardWareMap

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

    public void intake(double timeout){

        //implement code here

    }//attempts to fetch a block until a certain amount of time, exits if block is already in bay

    public void intake(double timeout, Color color){

        //implement code here

    }//attempts to fetch a block matching the color profile, use for green path, exits if block is already in bay

    public void grab(){

        //implement code here

    }//moves bot arm to grab tray on the ground, when called again, release tray


}
