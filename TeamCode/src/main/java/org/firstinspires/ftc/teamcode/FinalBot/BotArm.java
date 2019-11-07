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

    public void rotateDegree(DcMotor armMotor, double degrees, double power){
        DcMotor.RunMode temp = armMotor.getMode();//saves runmode of motor
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//sets mode to use encoder

        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);//begins runmode

        double target = armMotor.getCurrentPosition()+( (degrees/360) *COUNTS_PER_MOTOR_REV*DRIVE_GEAR_REDUCTION);//sets target to # of rotations and converts that to motor counts

        armMotor.setTargetPosition((int)target);//sets target

        armMotor.setPower(power);//starts motor at desired power

        while(armMotor.isBusy());//waits until arm is done

        armMotor.setPower(0);//stops motor

        if(Math.abs(armMotor.getCurrentPosition()-target) > COUNTS_PER_MOTOR_REV*0.1){//10% error threshold
            rotateDegree(armMotor, (target-armMotor.getCurrentPosition())/COUNTS_PER_MOTOR_REV/DRIVE_GEAR_REDUCTION*360 , power/2  );//corrects error at slower speed
        }//error correction

    }//rotates motor # of degrees

}
