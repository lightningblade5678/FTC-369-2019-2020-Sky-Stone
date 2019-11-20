/*
Gamepad 1 (movement):

    -Left Joystick
        -Forwards and Backwards (y-axis)
        -Strafing (x-axis)

    -Right Joystick
        - Turning (x-axis)


Gamepad 2 (arm, intake, claw):

    -Right Joystick
        -Base Motor movement (y-axis)

    -Right Trigger/Bumper
        -Finger Movement

    -Left Joystick [!] TEST [!]
        -Wrist Movement

    -Button A/B
        -Hand Movement [!] WORK IN PROGRESS [!]

    -D-Pad
        -Intake
            [!] WORK IN PROGRESS [!]

 */

package org.firstinspires.ftc.teamcode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class FinalTeleOp extends OpMode{
    private ElapsedTime passTime = new ElapsedTime(0);

    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;

    private DcMotor intakeMotorRight;
    private DcMotor intakeMotorLeft;

    private CRServo finger;

    private DcMotor arm;
    private CRServo wrist;
    private Servo hand;

    @Override

    public void init(){



        telemetry.addData("Status", "Initializing");
        telemetry.update();

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        intakeMotorRight = hardwareMap.get(DcMotor.class, "intakeRight");
        intakeMotorLeft = hardwareMap.get(DcMotor.class, "intakeLeft");

        finger = hardwareMap.get(CRServo.class, "finger");

        arm = hardwareMap.get(DcMotor.class,"baseMotor");


        wrist = hardwareMap.get(CRServo.class,"wristServo");
        hand = hardwareMap.get(Servo.class,"handServo");

        telemetry.addData("Status", "Initialized");
        telemetry.addData("Status", "Test1");
        telemetry.addData("Status", "Test2");
        telemetry.update();


    }

    Boolean wristBool = true;
    float intPow = 0;

    public void loop(){

        if (gamepad1.left_stick_y < 0){ //Forwards

            telemetry.addData("Direction","Forwards");

            allMotors(1);
            //allMotors(gamepad1.right_stick_y);

        }
        if (gamepad1.left_stick_y > 0){ //Backwards

            telemetry.addData("Direction","Backwards");

            allMotors(-1);

           // allMotors(gamepad1.right_stick_y);

        }

        if (gamepad1.left_stick_x < 0){ //Strafe Left

            telemetry.addData("Direction","Strafe Left");

            frontLeft.setPower(-1);
            frontRight.setPower(-1);
            backLeft.setPower(1);
            backRight.setPower(1);

        }

        if(gamepad1.right_stick_x + gamepad1.right_stick_y + gamepad1.left_stick_y + gamepad1.left_stick_x == 0){

            allMotors(0);

        }

        if(gamepad1.left_stick_x > 0){ //Strafe Right

            telemetry.addData("Direction","Strafe Right");

            frontLeft.setPower(1);
            frontRight.setPower(1);
            backLeft.setPower(-1);
            backRight.setPower(-1);

        }

        if(gamepad1.right_stick_x < 0){ //Turning Left

            frontLeft.setPower(-1);
            frontRight.setPower(-1);
            backLeft.setPower(-1);
            backRight.setPower(-1);

        }

        if(gamepad1.right_stick_x > 0){ //Turning Right

            frontLeft.setPower(1);
            frontRight.setPower(1);
            backLeft.setPower(1);
            backRight.setPower(1);

        }

        //For testing to find perfect intake speed [!] UNTESTED [!]

            if (gamepad2.dpad_right) {
                if (intPow >= 0 && intPow <= 1) {
                    intakePower(intPow);
                }
            } else if (gamepad2.dpad_left) {
                intakePower(0);
            } else if (gamepad2.dpad_up) {
                intPow += .05;
            } else if (gamepad2.dpad_down) {
                intPow -= .05;
            }

            while(gamepad2.dpad_right || gamepad2.dpad_left || gamepad2.dpad_down || gamepad2.dpad_up); //stops program until dpad button is released


        telemetry.addData("Intake Power", intPow);
        telemetry.update();


        if(gamepad2.right_stick_y < 0){
            telemetry.addData("Arm", "Up");
            arm.setPower(.5);
        }else if(gamepad2.right_stick_y > 0){
            telemetry.addData("Arm", "Down");
            arm.setPower(0);
        }
        telemetry.update();



        //Moves finger
        if(gamepad2.right_trigger > 0){
            telemetry.addData("CRServo","Finger moving up");
            finger.setPower(1);
        }
        else if(!gamepad2.right_bumper){
            finger.setPower(0);
        }
        if(gamepad2.right_bumper){

            telemetry.addData("CRServo","Finger moving down");
            finger.setPower(-1);
        }
        else if(gamepad2.right_trigger <= 0){
            finger.setPower(0);
        }


        // uses joystick to move wrist


        if(gamepad2.left_stick_x > 0){
            wrist.setPower(.05);
        }else if(gamepad2.left_stick_x < 0){
            wrist.setPower(-.05);
        }else{
            wrist.setPower(0);
        }


        //moves hand servo
        if(gamepad2.a){
            hand.setPosition(1);
        }else if(gamepad2.b){
            hand.setPosition(0);
        }

        telemetry.update();//updates telemetry with new input data
    }

    private void allMotors(double x){

        frontLeft.setPower(x);
        frontRight.setPower(-x);
        backLeft.setPower(x);
        backRight.setPower(-x);

    }

    private void wait(int ms){
        passTime.reset();
        while(passTime.milliseconds() < ms);//waits for ms
    }
    private void intakePower(float x){
        intakeMotorLeft.setPower(x);
        intakeMotorRight.setPower(-x);
    }
;
}