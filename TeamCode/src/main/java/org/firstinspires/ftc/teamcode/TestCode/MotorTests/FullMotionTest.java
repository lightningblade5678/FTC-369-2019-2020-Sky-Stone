package org.firstinspires.ftc.teamcode.TestCode.MotorTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.FinalBot.FinalBot;

@Autonomous(name="FullMotionTest")
public class FullMotionTest extends LinearOpMode {

    @Override
    public void runOpMode(){

        FinalBot bot = new FinalBot(hardwareMap);

        telemetry.addData("Rotating: ","90");
        telemetry.update();

        bot.calibrateGyro();

        bot.rotate(90);

        telemetry.addData("Rotating: ","-270");
        telemetry.update();

        bot.rotate(-270);

        telemetry.addData("Strafe: ","6in");
        telemetry.update();

        bot.move(6,0);

        telemetry.addData("CounterStrafe: ","6in");
        telemetry.update();

        bot.move(-6,0);

        telemetry.addData("Move: ","6in");
        telemetry.update();

        bot.move(0,6);

        telemetry.addData("Reverse: ","6in");
        telemetry.update();

        bot.move(0,-6);

        telemetry.addData("Diagonal: ","R6in, U6in");
        telemetry.update();

        bot.move(6,6);

        telemetry.addData("Finishing: ","360");
        telemetry.update();

        bot.rotate(360);



    }

}
