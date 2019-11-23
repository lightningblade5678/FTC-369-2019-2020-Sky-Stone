package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FinalBot.FinalBot;

public class HookTest2 extends LinearOpMode {

    public void runOpMode(){

        FinalBot bot = new FinalBot(hardwareMap);

        bot.hook.dropHook();

        sleep(5000);

        bot.hook.raiseHook();

    }

}
