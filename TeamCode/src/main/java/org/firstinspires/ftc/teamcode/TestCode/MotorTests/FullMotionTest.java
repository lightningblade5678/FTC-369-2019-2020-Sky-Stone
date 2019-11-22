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
        telemetry.addData("Heading",bot.calibrateGyro());
        telemetry.update();
        sleep(2000);

        bot.rotate(90);

        telemetry.addData("Rotating: ","-270");
        telemetry.update();

        bot.rotate(-180);

        telemetry.addData("Strafe: ","6in");
        telemetry.update();

        bot.move(6,0);

        telemetry.addData("Test: ","DONE!");
        telemetry.update();

        sleep(1000);

        telemetry.addData("CounterStrafe: ","6in");
        telemetry.update();

        bot.move(-6,0);

        telemetry.addData("Test: ","DONE!");
        telemetry.update();

        sleep(1000);

        telemetry.addData("Move: ","6in");
        telemetry.update();

        bot.move(0,6);

        telemetry.addData("Test: ","DONE!");
        telemetry.update();

        sleep(1000);

        telemetry.addData("Reverse: ","in");
        telemetry.update();

        bot.move(0,-6);

        telemetry.addData("Test: ","DONE!");
        telemetry.update();

        sleep(1000);

        telemetry.addData("Diagonal: ","R6in, U6in");
        telemetry.update();

        bot.move(6,6);

        telemetry.addData("Test: ","DONE!");
        telemetry.update();

        sleep(1000);

        telemetry.addData("Finishing: ","360");
        telemetry.update();

        bot.rotate(360);

        telemetry.addData("Test: ","DONE!");
        telemetry.update();

        sleep(1000);

        telemetry.addData("Test: ", "Arm up 90");
        telemetry.update();

        bot.arm.baseRotateDegree(bot.arm.baseMotor, 90, 0.5);
        bot.arm.toggleWrist(false);

        telemetry.addData("Test: ","DONE!");
        telemetry.update();

        sleep(1000);
        */

        bot.placeBlock();

    }

}
