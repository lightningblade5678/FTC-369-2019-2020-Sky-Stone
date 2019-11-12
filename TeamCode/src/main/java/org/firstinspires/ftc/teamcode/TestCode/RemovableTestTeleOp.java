package org.firstinspires.ftc.teamcode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class RemovableTestTeleOp extends OpMode{
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

    @Override

    public void init(){

        telemetry.addData("Status", "Initializing");

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        intakeMotorRight = hardwareMap.get(DcMotor.class, "inRight");
        intakeMotorLeft = hardwareMap.get(DcMotor.class, "inLeft");

        finger = hardwareMap.get(Servo.class, "finger");

        arm = hardwareMap.get(DcMotor.class,"arm");


        wrist = hardwareMap.get(Servo.class,"wrist");
        hand = hardwareMap.get(Servo.class,"hand");
        telemetry.addData("Status", "Initialized");

    }

    public void loop(){

        int i = 1;

        if (gamepad1.right_stick_y > 0){

            telemetry.addData("Direction","Forwards");

            allMotors(gamepad1.right_stick_y);

        }
        if (gamepad1.right_stick_y < 0){

            telemetry.addData("Direction","Backwards");

            allMotors(gamepad1.right_stick_y);

        }

        if (gamepad1.right_stick_x < 0){

            telemetry.addData("Direction","Strafe Left");

            frontLeft.setPower(-1);
            frontRight.setPower(1);
            backLeft.setPower(1);
            backRight.setPower(-1);

        }

        if(gamepad1.right_stick_x + gamepad1.right_stick_y + gamepad1.left_stick_y + gamepad1.left_stick_x == 0){

            allMotors(0);

        }

        if(gamepad1.right_stick_x > 0){

            telemetry.addData("Direction","Strafe Right");

            frontLeft.setPower(1);
            frontRight.setPower(-1);
            backLeft.setPower(-1);
            backRight.setPower(1);

        }

        if(gamepad1.left_stick_x < 0 || gamepad1.left_stick_y < 0){

            frontLeft.setPower(-1);
            frontRight.setPower(1);
            backLeft.setPower(-1);
            backRight.setPower(1);

        }

        if(gamepad1.left_stick_x > 0 || gamepad1.left_stick_y > 0){

            frontLeft.setPower(1);
            frontRight.setPower(-1);
            backLeft.setPower(1);
            backRight.setPower(-1);

        }

        if(gamepad2.dpad_left){

            intakeMotorRight.setPower(-0.1);
            intakeMotorLeft.setPower(0.1);

        }
        if(gamepad2.dpad_up){

            intakeMotorRight.setPower(-0.3);
            intakeMotorLeft.setPower(0.3);

        }
        if(gamepad2.dpad_right){

            intakeMotorRight.setPower(-0.5);
            intakeMotorLeft.setPower(0.5);

        }
        if(gamepad2.dpad_down){

            intakeMotorRight.setPower(0);
            intakeMotorLeft.setPower(0);

        }

        if(gamepad2.right_trigger > 0){

            telemetry.addData("Servo","Finger moving up");
            telemetry.update();
            wait(3000);
            finger.setPosition(finger.getPosition() + 0.1);
        }
        if(gamepad2.right_bumper && finger.getPosition() - 0.1 >= 0){
            telemetry.addData("Servo","Finger moving down");
            telemetry.update();
            wait(3000);
            finger.setPosition(finger.getPosition() - 0.1);

        }

        if(gamepad2.left_trigger > 0 && wrist.getPosition() + 0.1 <= 1){
            telemetry.addData("Servo","Wrist moving up");
            telemetry.update();
            wait(3000);
            wrist.setPosition(wrist.getPosition() + 0.1);

        }
        if(gamepad2.left_bumper && wrist.getPosition() - 0.1 >= 0){
            telemetry.addData("Servo","Wrist moving down");
            telemetry.update();
            wait(3000);
            wrist.setPosition(wrist.getPosition() - 0.1);

        }

        if(gamepad2.x && hand.getPosition() + 0.1 <= 1){
            telemetry.addData("Servo","Hand moving up");
            telemetry.update();
            wait(3000);
            hand.setPosition(hand.getPosition() + 0.1);

        }
        if(gamepad2.y && hand.getPosition() - 0.1 >= 0){
            telemetry.addData("Servo","Hand moving down");
            telemetry.update();
            wait(3000);
            hand.setPosition(hand.getPosition() - 0.1);

        }


    }

    private void allMotors(double x){

        frontLeft.setPower(x);
        frontRight.setPower(x);
        backLeft.setPower(x);
        backRight.setPower(x);

    }

    private void wait(int ms){
        passTime.reset();
        while(passTime.milliseconds() < ms);//waits for ms
    }

}