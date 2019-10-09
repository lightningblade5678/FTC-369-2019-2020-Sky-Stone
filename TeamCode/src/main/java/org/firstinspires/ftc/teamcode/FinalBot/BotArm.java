package org.firstinspires.ftc.teamcode.FinalBot;

/*
    This class is used to manipulate the bot arm
 */

import com.qualcomm.robotcore.hardware.DcMotor;

public class BotArm {

    DcMotor[] motors;//motors utilised by the bot

    public BotArm(DcMotor[] motors){
        this.motors = motors;
    }//basic constructor to make a motor

    //start work method

    public void rotateHorizontal(double degree){

        //insert code here

    }//rotates the arm on the platform

    public void rotateVertical(double degree){

        //insert code here

    }//rotates the arm up/down

    public void grab(){

        //insert code here

    }//toggles if the claw is "grabbed" or not and negates its mode

}
