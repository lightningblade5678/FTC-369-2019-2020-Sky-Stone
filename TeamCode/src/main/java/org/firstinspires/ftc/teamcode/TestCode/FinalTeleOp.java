package org.firstinspires.ftc.teamcode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
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

    private Servo finger;

    private DcMotor arm;
    private Servo wrist;
    private Servo hand;

    double pos;

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

        finger = hardwareMap.get(Servo.class, "finger");

        arm = hardwareMap.get(DcMotor.class,"baseMotor");


        wrist = hardwareMap.get(Servo.class,"wristServo");
        hand = hardwareMap.get(Servo.class,"handServo");
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        pos = finger.getPosition();
        finger.setPosition(1); // [!] check number
    }

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

        //FOR TESTING
        if(gamepad2.left_stick_x < 0){

            telemetry.addData("Intake", "Moving");
            telemetry.update();

            intakeMotorRight.setPower(-0.1);
            intakeMotorLeft.setPower(0.1);

        }
        if(gamepad2.left_stick_x > 0){

            telemetry.addData("Intake", "Moving");
            telemetry.update();

            intakeMotorRight.setPower(-0.3);
            intakeMotorLeft.setPower(0.3);

        }
        if(gamepad2.left_stick_y < 0){

            telemetry.addData("Intake", "Moving");
            telemetry.update();

            intakeMotorRight.setPower(-0.5);
            intakeMotorLeft.setPower(0.5);

        }
        if(gamepad2.left_stick_y > 0){

            telemetry.addData("Intake", "Moving");
            telemetry.update();

            intakeMotorRight.setPower(0);
            intakeMotorLeft.setPower(0);
        }

        if(gamepad2.right_stick_y < 0){
            telemetry.addData("Arm", "Up");
            arm.setPower(.5);
        }else if(gamepad2.right_stick_y > 0){
            telemetry.addData("Arm", "Down");
            arm.setPower(0);
        }
        telemetry.update();



/*
        while(gamepad2.a){
            telemetry.addData("OG Position", finger.getPosition());
            telemetry.update();
            pos =- .01;
            finger.setPosition(pos);
            telemetry.addData("Position", finger.getPosition());
            telemetry.update();
        }//outputs position data of finger


        while(gamepad2.b){
            pos =+ .01;
            finger.setPosition(pos);
            telemetry.addData("Position", pos);
            telemetry.update();
        }//outputs position data of finger */


        /*
        if(gamepad2.right_trigger > 0 && finger.getPosition() <= .9){

            telemetry.addData("Servo","Finger moving up");
            finger.setPosition(finger.getPosition() + 0.1);
        }
        if(gamepad2.right_bumper && finger.getPosition() >= .1 ){
            telemetry.addData("Servo","Finger moving down");
            finger.setPosition(finger.getPosition() - 0.1);

        }
*/
        if(gamepad2.left_trigger > 0){
            wrist.setPosition(gamepad2.left_trigger);
        }
        if(gamepad2.left_trigger < 0){
            wrist.setPosition(gamepad2.left_trigger);
        }

        if(gamepad2.x && hand.getPosition() <= .9){
            telemetry.addData("Servo","Hand moving up");
            hand.setPosition(hand.getPosition() + 0.1);

        }
        if(gamepad2.y && hand.getPosition() >= .1){
            telemetry.addData("Servo","Hand moving down");
            hand.setPosition(hand.getPosition() - 0.1);
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
;
}