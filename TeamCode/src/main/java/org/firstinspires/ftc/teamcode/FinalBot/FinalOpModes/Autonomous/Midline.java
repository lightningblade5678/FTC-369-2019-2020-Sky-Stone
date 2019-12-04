package org.firstinspires.ftc.teamcode.FinalBot.FinalOpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotArm;

@Autonomous(name="!Midline")
public class Midline extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;

        bot.arm.baseRotateDegree(45, 1);
        bot.arm.baseMotor.setPower(.25);

        waitForStart();

        arm.baseMotor.setPower(0);

        bot.move(0, 18);

    }
}
