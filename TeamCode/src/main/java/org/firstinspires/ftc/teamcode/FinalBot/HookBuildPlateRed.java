package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.FinalBot.FinalBot;

/*
Auto code for the blue route, assumes bot is facing wall with left side aligned with the line of the first block at the wall
 */

@Autonomous
public class HookBuildPlateRed extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        //wait
        FinalBot bot = new FinalBot(hardwareMap);

        bot.arm.baseRotateDegree(20, 0.8);
        bot.arm.baseMotor.setPower(.25);

        waitForStart();

        //telemetry.addData("Readjustment: ","Wrist");
        //telemetry.update();

        bot.arm.wristServo.setPower(1);

        sleep(35);

        bot.arm.wristServo.setPower(0);

        //telemetry.addData("Readjustment: ","Drop Arm");
        //telemetry.update();

        bot.arm.baseRotateDegree(-50,0.1);

        //

        //telemetry.addData("Move: ","To Tray");
        //telemetry.update();

        bot.move(-13,0);
        //move towards tray
        bot.move(0,-30);//back into tray
        bot.getWheels().moveRelativeY(-8,0.2);

        //telemetry.addData("Action: ","Block");
        //telemetry.update();

        bot.hook.dropHook();//drops hook onto block
        bot.placeBlock();//places block

        //telemetry.addData("Move: ","Block");
        //telemetry.update();

        for(int i = 0; i < 5; i++) {
               bot.getWheels().moveRelativeY(8, 0.3);
               sleep(5);
        }

        bot.hook.raiseHook();//raises hook, is done with hook


        //telemetry.addData("Ram: ","Tray");
        //telemetry.update();

        ElapsedTime times = new ElapsedTime();

        bot.move(30,0);

        bot.move(0,-42);
        /*
        times.reset();
        while(times.milliseconds() < 300);
        */

        bot.move(-28,0);//rams plate

        bot.move(0,32);

        //telemetry.addData("Park: ","Bot");
        //telemetry.update();

        bot.move(43,0);//parks under bridge

    }
}
