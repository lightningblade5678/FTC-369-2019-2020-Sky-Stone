/*
Dimensions of Arm:

10 inches tall (bot to top of arm (width) )
14 inches long (arm)
4 inches tall (bricks)
3 inches long (wrist)
 */
package org.firstinspires.ftc.teamcode.FinalBot;

/*
    This class is used to manipulate the bot arm
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class BotArm {

    private static final double rpm = 152;

    public DcMotor baseMotor;
    public Servo wristServo;
    public Servo handServo;

    public BotArm(DcMotor base, Servo wrist, Servo hand){
    base = baseMotor;
    wrist = wristServo;
    hand = handServo;
    }//basic constructor to make a motor

    //start work method

    public void rotateWrist(double wristDegree /*never set this above 180 or below 0*/ ){
        wristServo.setPosition(wristDegree/180); // [!] Check params
    }//sets servo to degrees between 0-180


    public void handGrab(boolean state){
        if (state){
            handServo.setPosition(0); // [!] check parameter
        }
        else{
            handServo.setPosition(1); // [!] check parameter
        }//closes hand

    }//detects if hand is closed or not and inverts results

    public void baseRotateDegree(DcMotor base, double deg, double speed /*Never set this below 0 or above 1*/ ){
        
        double timeRot /*in seconds*/= deg/ ( (rpm*speed)/60*360 );//calculates time that the arm needs to rotate for

        ElapsedTime time = new ElapsedTime(0);
        time.reset();

        base.setPower(speed);
        while(time.seconds() < timeRot);
        base.setPower(0);

    }//rotates base degrees to l/r

    public void setGrabPos(){
        // [!] Check all parameters
        handGrab(false);
        baseRotateDegree(baseMotor, 0, 1);
        rotateWrist(180); //[!] check params


    }//sets all motors to pos to grab stored stone
}
