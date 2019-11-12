package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;



public class FinalAutonomousOp_BuildRed extends LinearOpMode {


    FinalBot bot = new FinalBot(hardwareMap);
    public BotWheels wheels;
    public BotArm arm;

    @Override
    public void runOpMode() throws InterruptedException {


        //Orients bot in front of build plate
        bot.move(0, 12);
        bot.rotate(180);
        bot.move(0, 12);

        arm.baseRotateDegree(arm.baseMotor, 225, 1); //"Grabs" plate
        bot.move(0, -24); //Drags plate into zone
        arm.baseRotateDegree(arm.baseMotor, 135, 1); //"Releases" plate
        bot.move()




    }
}
