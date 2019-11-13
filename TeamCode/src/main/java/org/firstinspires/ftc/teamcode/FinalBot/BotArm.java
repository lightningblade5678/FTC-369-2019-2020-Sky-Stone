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

public class BotArm {

    //vars for encoders

    private static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder is 1440, neverest classic 40 are 1120
    private static final double     DRIVE_GEAR_REDUCTION    =  120/80;     // This is < 1.0 if geared UP

    //end encoder vars


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
        wristServo.setPosition(wristDegree/180); // [!] Check params
    }//sets servo to degrees between 180


    public void handGrab(boolean state){
        if (state){
            handServo.setPosition(0); // [!] check parameter
        }
        else{
            handServo.setPosition(1); // [!] check parameter
        }


    }//detects if hand is closed or not and inverts results

    public void baseRotateDegree(DcMotor armMotor, double degrees, double power){

        DcMotor.RunMode temp = armMotor.getMode();//saves runmode of motor

        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//sets mode to use encoder

        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);//begins runmode

        double target = armMotor.getCurrentPosition()+( (degrees/360) *COUNTS_PER_MOTOR_REV*DRIVE_GEAR_REDUCTION);//sets target to # of rotations and converts that to motor counts

        armMotor.setTargetPosition((int)target);//sets target

        armMotor.setPower(power);//starts motor at desired power

        while(armMotor.isBusy());//waits until arm is done

        armMotor.setPower(0);//stops motor

        if(Math.abs(armMotor.getCurrentPosition()-target) > COUNTS_PER_MOTOR_REV*0.1){//10% error threshold
            baseRotateDegree(armMotor, (target-armMotor.getCurrentPosition())/COUNTS_PER_MOTOR_REV/DRIVE_GEAR_REDUCTION*360 , power/2  );//corrects error at slower speed
        }//error correction

        armMotor.setMode(temp);

    }//rotates motor # of degrees (Rotates in direction of motor)

    public void baseRotateDegreeTo(DcMotor armMotor, double degree, double power){

        double currDeg = armMotor.getCurrentPosition()/COUNTS_PER_MOTOR_REV/DRIVE_GEAR_REDUCTION*360;//converts current encoder pos to a degree angle
        currDeg = currDeg-(((int)currDeg)/360*360);//removes excess 360's

        baseRotateDegree(armMotor,degree-currDeg,1);//rotates arm to pos

    }//rotates arm to target degree position

    public double getDegree(DcMotor armMotor){
        return armMotor.getCurrentPosition()/(COUNTS_PER_MOTOR_REV*DRIVE_GEAR_REDUCTION)*360;
    }//returns current bot arm pos

    public void setGrabPos(){
        // [!] Check all parameters
        handGrab(false);
        baseRotateDegree(baseMotor, 0, 1);
        rotateWrist(180); //[!] check params


    }//sets all motors to pos to grab stored stone
}
