package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="BrickRed")
public class FinalAuto_BrickRed extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;
        BotWheels wheels = bot.getWheels();

        arm.baseRotateDegree(30, 1);
        arm.baseMotor.setPower(.25);

        waitForStart();

        arm.baseMotor.setPower(0);

        bot.move(24, 0);
        bot.move(0, -72);

        bot.placeBlock();

        bot.move(0, 36);
    }
}
