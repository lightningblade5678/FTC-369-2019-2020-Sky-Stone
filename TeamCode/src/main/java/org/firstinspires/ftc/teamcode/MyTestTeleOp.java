package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class MyTestTeleOp extends OpMode {

    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor frontRight;
    public DcMotor backRight;

    public DcMotor intakeMotorRight;
    public DcMotor intakeMotorLeft;

    @Override

    public void init(){

        telemetry.addData("Status", "Initializing");

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        intakeMotorRight = hardwareMap.get(DcMotor.class, "inRight");
        intakeMotorLeft = hardwareMap.get(DcMotor.class, "inLeft");


        telemetry.addData("Status", "Initialized");

    }

    public void loop(){

        if(gamepad1.right_stick_y == 1){

            telemetry.addData("Direction","Forwards");

            allMotors(1);

        }
        if(gamepad1.right_stick_y == -1){

            telemetry.addData("Direction","Backwards");

            allMotors(-1);

        }

        if(gamepad1.right_stick_x == -1){

            telemetry.addData("Direction","Strafe Left");

            frontLeft.setPower(-1);
            frontRight.setPower(1);
            backLeft.setPower(1);
            backRight.setPower(-1);

        }

        if(gamepad1.right_stick_x == 1){

            telemetry.addData("Direction","Strafe Right");

            frontLeft.setPower(1);
            frontRight.setPower(-1);
            backLeft.setPower(-1);
            backRight.setPower(1);

        }

        if(gamepad1.left_stick_x == 1 || gamepad1.left_stick_y == 1){

            frontLeft.setPower(-1);
            frontRight.setPower(1);
            backLeft.setPower(-1);
            backRight.setPower(1);

        }

        if(gamepad1.left_stick_x == -1 || gamepad1.left_stick_y == -1){

            frontLeft.setPower(1);
            frontRight.setPower(-1);
            backLeft.setPower(1);
            backRight.setPower(-1);

        }

        if(gamepad2.right_trigger == 1){

            double x = -1;

            //intakeMotorLeft.intakeStart();
            intakeMotorRight.setPower(-1*x);

        }

    }

    public void allMotors(double x){

        frontLeft.setPower(x);
        frontRight.setPower(x);
        backLeft.setPower(x);
        backRight.setPower(x);

    }

}
