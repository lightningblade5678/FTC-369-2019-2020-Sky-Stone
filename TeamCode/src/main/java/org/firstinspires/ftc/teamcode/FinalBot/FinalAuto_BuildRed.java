package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="BuildRed")
public class FinalAuto_BuildRed extends LinearOpMode {




    @Override
    public void runOpMode() throws InterruptedException {

        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;

        bot.move(0, 30); //Orients bot in front of build plate

        arm.baseRotateDegree(arm.baseMotor, 225, 1); //"Grabs" plate
        bot.move(0, -30); //Drags plate into zone
        arm.baseRotateDegree(arm.baseMotor, 135, 1); //"Releases" plate
        bot.move(-12, 0); //moves robot away from plate

        //moves bot through mid-bridge
        bot.move(0, 48);
        bot.rotate(-90);
        bot.move(0, 24);

        //Drives forward until a block is picked up
        bot.move(0, bot.intake(30));


        //move robot to in front of the build plate
        bot.rotate(-90);
        bot.move(0, 24);
        bot.rotate(-90);
        bot.move(0, 48);
        bot.rotate(180);

        //places block
        while (!bot.detectColor()) {
            bot.move(0, 1);
        }
        if(bot.detectColor()){
            bot.move(0, -10);
            bot.rotate(180);
            bot.move(0, -10);
            bot.placeBlock(1);
        }
        bot.move(0, 24); //moves bot onto mid-line
    }
}
