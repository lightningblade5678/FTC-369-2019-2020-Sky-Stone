package org.firstinspires.ftc.teamcode.FinalBot;

/*
    This class is used to manipulate the bot arm
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class BotArm {

    DcMotor[] motors;//motors utilised by the bot
    Servo[] servos;//servos to be used by the bot

    public BotArm(DcMotor[] motors, Servo[] servos){
        this.motors = motors;
        this.servos = servos;
    }//basic constructor to make a motor

    //start work method
    public void rotateWrist(double degree){

        //insert code here
        
    }

    public void rotateBase(double degree){

        //insert code here

    }//rotates the arm up/down

    public void grab(){

        //insert code here

    }//toggles if the claw is "grabbed" or not and negates its mode

}
