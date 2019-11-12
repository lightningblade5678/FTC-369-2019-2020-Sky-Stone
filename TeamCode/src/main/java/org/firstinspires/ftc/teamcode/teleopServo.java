package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
@Disabled
public class teleopServo {

        //Define motor names

        public Servo claw;
        @Override

        public void init(){

            // Define hardware maps

            claw = hardwareMap.get(Servo.class, "mainServo");

            //Run motors for 4 seconds

            claw.setPosition(1);


            sleep(4000);

            claw.setPosition(0);

        }
    }
}
