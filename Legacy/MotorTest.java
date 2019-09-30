package org.firstinspires.ftc;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
@TeleOp(name="motortest", group="K9bot")
public class MotorTest extends LinearOpMode {
    @Override
    //testing motors
    public void runOpMode () {
        HardwareK9bot testMotors = new HardwareK9bot();
        testMotors.init(hardwareMap);
        waitForStart();//
        while (opModeIsActive()) {
            testMotors.leftBack.setPower(1);
            testMotors.rightBack.setPower(1);
            testMotors.leftDrive.setPower(1);
            testMotors.rightDrive.setPower(1);
            telemetry.addData("Left Back Motor: ", "" + testMotors.leftBack.getCurrentPosition());
            telemetry.addData("Right Back Motor: ", "" + testMotors.rightBack.getCurrentPosition());
            telemetry.addData("Left Front Motor: ", "" + testMotors.leftDrive.getCurrentPosition());
            telemetry.addData("RIght Front Motor: ", "" +testMotors.rightDrive.getCurrentPosition());
            telemetry.update();
        }
    }


    }
