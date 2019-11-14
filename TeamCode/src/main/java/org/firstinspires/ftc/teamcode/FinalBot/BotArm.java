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

    /* For BaseRotateDegreeTo method
    //vars for encoders

    private static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder is 1440, neverest classic 40 are 1120
    private static final double     DRIVE_GEAR_REDUCTION    =  120/80;     // This is < 1.0 if geared UP

    //end encoder vars

    */

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

/*
    public void baseRotateDegreeTo(DcMotor armMotor, double degree, double power){

        double currDeg = armMotor.getCurrentPosition()/COUNTS_PER_MOTOR_REV/DRIVE_GEAR_REDUCTION*360;//converts current encoder pos to a degree angle
        currDeg = currDeg-(((int)currDeg)/360*360);//removes excess 360's

*/
    public void setGrabPos(){
        // [!] Check all parameters
        handGrab(false);
        baseRotateDegree(baseMotor, 0, 1);
        rotateWrist(180); //[!] check params


    }//sets all motors to pos to grab stored stone
}
