package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
@Autonomous(name="servoTest")
public class servoTest extends LinearOpMode{

        //Define motor names

        public Servo claw;
        @Override

        public void runOpMode(){

            // Define hardware maps

            claw = hardwareMap.get(Servo.class, "mainServo");

            //Run motors for 4 seconds

            claw.setPosition(1);


            sleep(4000);

            claw.setPosition(0);

        }
    }

