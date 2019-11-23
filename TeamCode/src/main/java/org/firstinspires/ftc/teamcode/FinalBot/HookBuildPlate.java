package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FinalBot.FinalBot;

/*
Auto code for the blue route, assumes bot is facing wall with left side aligned with the line of the first block at the wall
 */

@Autonomous
public class HookBuildPlate extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        //wait
        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;
        BotWheels wheels = bot.getWheels();

        arm.baseRotateDegree(30, 1);
        arm.baseMotor.setPower(.25);

        waitForStart();

        bot.move(-13,0);
        //move towards tray
        bot.move(0,-30);//back into tray
        bot.getWheels().moveRelativeY(-8,0.1);
        bot.hook.dropHook();//drops hook onto block
        bot.placeBlock();//places block

        for(int i = 0; i < 10; i++) {
               bot.getWheels().moveRelativeY(4, 0.2);
               sleep(25);
        }

        bot.hook.raiseHook();//raises hook, is done with hook

        bot.move(30,0);
        bot.move(0,-42);
        bot.move(-28,0);//rams plate
        bot.move(0,32);

        bot.move(42,0);//parks under bridge

    }
}
