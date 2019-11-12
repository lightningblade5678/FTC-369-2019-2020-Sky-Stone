package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;



public class FinalAutonomousOp_BuildRed extends LinearOpMode {


    FinalBot bot = new FinalBot(hardwareMap);
    public BotWheels wheels;
    public BotArm arm;

    @Override
    public void runOpMode() throws InterruptedException {


        bot.move(0, 30); //Orients bot in front of build plate

        arm.baseRotateDegree(arm.baseMotor, 225, 1); //"Grabs" plate
        bot.move(0, -30); //Drags plate into zone
        arm.baseRotateDegree(arm.baseMotor, 135, 1); //"Releases" plate
        bot.move(-12, 0); //moves robot away from plate

        //moves bot through midbridge
        bot.move(0, 48);
        bot.rotate(-90);
        bot.move(0, 24);
        

    }
}
