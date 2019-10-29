package org.firstinspires.ftc.teamcode.FinalBot;

/*
    This class is used to manipulate the bot arm
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class BotArm {

    public DcMotor baseMotor;
    public Servo wristServo;
    public Servo handServo;

    public BotArm(DcMotor base, Servo wrist, Servo hand){
    base = baseMotor;
    wrist = wristServo;
    hand = handServo;
    }//basic constructor to make a motor

    //start work method

    public void rotateWrist(double wristDegree){

        if(wristServo.getPosition() == 1){ // value may vary [!]
            wristServo.setPosition(0);

        } else if(wristServo.getPosition() == 0){ // value may vary [!]
            wristServo.setPosition(1);
        }

    }//detects if wrist is in grabbing position or placing position and inverts results


    public void rotateBase(double baseDegree){

        //insert code here

    }//rotates the arm up/down

    public void handGrab(){

    if(handServo.getPosition() == 1){ // value may vary [!]
        handServo.setPosition(0);

    } else if(handServo.getPosition() == 0){ // value may vary [!]
        handServo.setPosition(1);
    }

    }//detects if hand is closed or not and inverts results

}
