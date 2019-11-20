package org.firstinspires.ftc.teamcode.UtilityCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.LinkedList;

/*
This class is used to
 */

@TeleOp(name = "Servo Position Finder")
public class FindServoPos extends LinearOpMode {

    public void runOpMode(){
        LinkedList<Servo> servos = (LinkedList<Servo>) hardwareMap.getAll(Servo.class);
    }

}
