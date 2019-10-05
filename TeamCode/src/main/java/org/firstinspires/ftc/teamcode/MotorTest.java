package org.firstinspires.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(name = "motorTest")

public class MotorTest extends LinearOpMode {

    public DcMotor backLeft;
    public DcMotor backRight;
    public DcMotor frontLeft;
    public DcMotor frontRight;

    @Override
    //testing motors
    public void runOpMode () {

        backleft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");

        waitForStart();
        while (opModeIsActive()) {
            testMotors.leftBack.setPower(1);
            testMotors.rightBack.setPower(1);
            testMotors.leftFront.setPower(1);
            testMotors.rightFront.setPower(1);

            // Let the motors run for 4 seconds
            sleep(4000);

            testMotors.leftBack.setPower(0);
            testMotors.rightBack.setPower(0);
            testMotors.leftFront.setPower(0);
            testMotors.rightFront.setPower(0);
        }
    }


    }
