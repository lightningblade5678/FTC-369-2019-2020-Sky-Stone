package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="BuildRed")
public class FinalAuto_BuildRed extends LinearOpMode {




    @Override
    public void runOpMode() throws InterruptedException {

        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;
        BotWheels wheels = bot.getWheels();

        //runs robot up over build plate edge, then backs up and drags plate
        bot.move(0, -30);
/*
        sleep(1000);

        wheels.setPower(1, 1);
        wheels.setPower(4, 1);
        wheels.setPower(2, -.1); //moves front wheels forward to keep robot on plate
        wheels.setPower(3, -.1); //moves front wheels forward to keep robot on plate
        sleep(15000);

        wheels.setPower(0);
        */
     }
}
