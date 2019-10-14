package org.firstinspires.ftc.teamcode.MotorTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
/*
    This class is used to test all the motors/servos on the bot
 */

@TeleOp(name = "Motor Test")
public class MotorServoTest extends LinearOpMode {

    ArrayList<DcMotor> motors = (ArrayList<DcMotor>) hardwareMap.getAll(DcMotor.class);
    ArrayList<Servo> servo = (ArrayList<Servo>) hardwareMap.getAll(Servo.class);
    private ElapsedTime passTime = new ElapsedTime(0);//"clock" to keep track of passed time

    @Override//overrides opMode
    public void runOpMode() {

        for(int i = 0; i < motors.size(); i++){

            telemetry.addData("Testing Motor: ",hardwareMap.getNamesOf(motors.get(i))+"| Press b to continue");
            while(!gamepad1.b);//stalls until b is pressed

            motors.get(i).setPower(-1);
            wait(300);
            motors.get(i).setPower(0);

            motors.get(i).setPower(1);
            wait(300);
            motors.get(i).setPower(0);

            telemetry.addData("Finished Testing Motor: ",hardwareMap.getNamesOf(motors.get(i))+"| Press b to continue");

        }//loops through all motors

        for(int i = 0; i < servo.size(); i++){

            telemetry.addData("Testing Servo: ",hardwareMap.getNamesOf(servo.get(i))+"| Press b to continue");
            while(!gamepad1.b);//stalls until b is pressed

            servo.get(i).setPosition(1);
            wait(300);

            servo.get(i).setPosition(-1);
            wait(300);

            telemetry.addData("Finished Testing Servo: ",hardwareMap.getNamesOf(servo.get(i))+"| Press b to continue");

        }//loops through all servos
    }

    private void wait(int ms){
        passTime.reset();
        while(passTime.milliseconds() < ms);//waits for ms
    }

}
