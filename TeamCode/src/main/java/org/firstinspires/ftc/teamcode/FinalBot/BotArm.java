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

import com.qualcomm.robotcore.hardware.CRServo;
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
    public CRServo wristServo;
    public Servo handServo;
    private ElapsedTime time = new ElapsedTime();

    private boolean currWrist;//inside intake or not

    public BotArm(DcMotor base, CRServo wrist, Servo hand){
        baseMotor = base;
        wristServo = wrist;
        handServo = hand;

        //set servos and motors to 0

        baseMotor.setPower(0);
        wristServo.setPower(0);
        handGrab(false);

        currWrist = true;//assume arm start in intake

    }//basic constructor to make a motor

    //start work method

    public void handGrab(boolean state){
        if (state){
            handServo.setPosition(0);
        }
        else{
            handServo.setPosition(0.6);
        }//closes hand || 1 - open     0 - closed

    }//detects if hand is closed or not and inverts results

    public void baseRotateDegree(DcMotor base, double deg, double speed /*Never set this below 0 or above 1*/ ){
        
        double timeRot /*in seconds*/ = Math.abs(deg)/ ( (rpm*speed)/60*360 ) * 5;//calculates time that the arm needs to rotate for

        ElapsedTime time = new ElapsedTime(0);
        time.reset();

        base.setPower(speed*( Math.abs(deg)/deg ));
        while(time.seconds() < timeRot);
        base.setPower(0);

    }//rotates base degrees to l/r

    public void toggleWrist(boolean intake){//true if move into intake loc

        ElapsedTime time = new ElapsedTime(0);

        if(intake && !currWrist){

            wristServo.setPower(1);
            while(time.seconds() < 1);
            currWrist = true;

        }else if(currWrist){

            wristServo.setPower(-1);
            while(time.seconds() < 1);
            currWrist = false;

        }

    }//moves wrist to expected position

/*
    public void baseRotateDegreeTo(DcMotor armMotor, double degree, double power){

        double currDeg = armMotor.getCurrentPosition()/COUNTS_PER_MOTOR_REV/DRIVE_GEAR_REDUCTION*360;//converts current encoder pos to a degree angle
        currDeg = currDeg-(((int)currDeg)/360*360);//removes excess 360's
        baseRotateDegree(armMotor,degree-currDeg,1);//rotates arm to pos
    }//rotates arm to target degree position
*/
    public void setGrabPos(){
        // [!] Check all parameters
        handGrab(false);
        wristServo.setPower(1);
        baseMotor.setPower(-.3);
        time.reset();
        while(time.milliseconds() < 2000){}
        baseMotor.setPower(0);
        wristServo.setPower(0);

        handGrab(true);


    }//sets all motors to pos to grab stored stone
}
