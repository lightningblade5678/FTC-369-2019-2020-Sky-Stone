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
        
        //move towards tray
        bot.move(0,-27);//back into tray
        bot.hook.dropHook();//drops hook onto block
        bot.placeBlock();//places block
        bot.move(0,27);//moves bot back onto starting loc
        bot.hook.raiseHook();//raises hook, is done with hook

        bot.move(24,0);
        bot.move(0,-21);
        bot.move(-24,0);//rams plate

        bot.move(0,-24);
        bot.move(-17,0);
        bot.move(0,24);//rams plate

        bot.move(65,0);//parks under bridge

    }
}
