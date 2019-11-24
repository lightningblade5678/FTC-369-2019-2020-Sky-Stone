package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class DropWaitParkRed extends LinearOpMode {

    public void runOpMode(){
        //wait
        FinalBot bot = new FinalBot(hardwareMap);

        bot.arm.baseRotateDegree(30, 1);
        bot.arm.baseMotor.setPower(.25);

        waitForStart();

        telemetry.addData("Readjustment: ","Wrist");
        telemetry.update();

        bot.arm.wristServo.setPower(1);

        sleep(40);

        bot.arm.wristServo.setPower(0);

        telemetry.addData("Readjustment: ","Drop Arm");
        telemetry.update();

        bot.arm.baseRotateDegree(-50,0.1);

        //

        //sleep(20000);

        bot.move(0,60);
        bot.placeBlock();
        bot.move(0,-32);

    }

}
