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

        /*

        telemetry.addData("Rotating: ","90");
        telemetry.update();

        telemetry.addData("Heading",bot.calibrateGyro());
        telemetry.update();
        sleep(2000);

        bot.rotate(90);

        telemetry.addData("Rotating: ","-270");
        telemetry.update();

        bot.rotate(-270);

         */
        telemetry.addData("Strafe: ","24in");
        telemetry.update();

        bot.move(24,0);

        sleep(6000);

        telemetry.addData("CounterStrafe: ","24in");
        telemetry.update();

        bot.move(-24,0);

        sleep(6000);

        telemetry.addData("Move: ","24in");
        telemetry.update();

        bot.move(0,24);

        sleep(6000);

        telemetry.addData("Reverse: ","24in");
        telemetry.update();

        bot.move(0,-24);

        sleep(6000);

        telemetry.addData("Diagonal: ","R24in, U24in");
        telemetry.update();

        bot.move(24,24);

        sleep(6000);

        telemetry.addData("Finishing: ","360");
        telemetry.update();

        bot.rotate(360);



    }

}
