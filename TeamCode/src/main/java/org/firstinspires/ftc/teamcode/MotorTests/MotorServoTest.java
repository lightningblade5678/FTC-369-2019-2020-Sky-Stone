package org.firstinspires.ftc.teamcode.MotorTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.LinkedList;
/*
    This class is used to test all the motors/servos on the bot
 */

@TeleOp(name = "MotorServo Test")
public class MotorServoTest extends LinearOpMode {

    LinkedList<DcMotor> motors = null;
    LinkedList<Servo> servo = null;
    private ElapsedTime passTime = new ElapsedTime(0);//"clock" to keep track of passed time

    @Override//overrides opMode
    public void runOpMode() {

        try{
            motors = (LinkedList<DcMotor>) hardwareMap.getAll(DcMotor.class);
            servo = (LinkedList<Servo>) hardwareMap.getAll(Servo.class);
        }catch (NullPointerException e){

        }

        for(int i = 0; i < motors.size(); i++){

            telemetry.addData("Testing Motor: ",hardwareMap.getNamesOf(motors.get(i))+"| Press b to continue");
            telemetry.update();
            while(!gamepad1.b);//stalls until b is pressed

            motors.get(i).setPower(-1);
            wait(300);
            motors.get(i).setPower(0);

            motors.get(i).setPower(1);
            wait(300);
            motors.get(i).setPower(0);

            telemetry.addData("Finished Testing Motor: ",hardwareMap.getNamesOf(motors.get(i))+"| Press b to continue");
            telemetry.update();
            while(!gamepad1.b);//stalls until b is pressed
            wait(300);

        }//loops through all motors

        for(int i = 0; i < servo.size(); i++){

            telemetry.addData("Testing Servo: ",hardwareMap.getNamesOf(servo.get(i))+"| Press b to continue");
            telemetry.update();
            while(!gamepad1.b);//stalls until b is pressed

            servo.get(i).setPosition(1);
            wait(300);

            servo.get(i).setPosition(0);
            wait(300);

            telemetry.addData("Finished Testing Servo: ",hardwareMap.getNamesOf(servo.get(i))+"| Press b to continue");
            telemetry.update();
            while(!gamepad1.b);//stalls until b is pressed
            wait(300);

        }//loops through all servos
    }

    private void wait(int ms){
        passTime.reset();
        while(passTime.milliseconds() < ms);//waits for ms
    }

}
