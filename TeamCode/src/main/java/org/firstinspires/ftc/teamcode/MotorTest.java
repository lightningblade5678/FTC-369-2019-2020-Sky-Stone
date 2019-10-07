<<<<<<< HEAD
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="MotorTest")
public class MotorTest extends LinearOpMode {

    //Define motor names

    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor frontRight;
    public DcMotor backRight;

    @Override
    public void runOpMode(){

        // Define hardware maps

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        //Run motors for 4 seconds

        frontLeft.setPower(1);
        backLeft.setPower(1);
        frontRight.setPower(1);
        backRight.setPower(1);

        sleep(4000);

        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }
}
=======
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
>>>>>>> 74de945c4d136d566cb60e2a61e9e7141b7cb7bc
