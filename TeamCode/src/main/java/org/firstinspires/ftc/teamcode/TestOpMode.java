package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/*
    Test class to run OpMode for TestBot
    Use for basic movement tests
*/
public class TestOpMode extends LinearOpMode {

    //initialize vars

    //static vars

    private ElapsedTime passTime = new ElapsedTime(0);//"clock" to keep track of passed time

    TestBot bot = new TestBot();//robot class that stores all motors

    //constructors

    public TestOpMode() {

    }//default constructor

    //req. methods

    /*
        Runs "Operation" to move robot
        Moves motors and inits TestBot
     */
    public void runOpMode() {

        //updates telemetry (phone data)
        telemetry.addData("Status: ","Initializing Bot");
        telemetry.update();

        bot.init(hardwareMap, DcMotor.RunMode.STOP_AND_RESET_ENCODER);//initializes motors for bot
        bot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//previous init set motors to reset, this sets it to a "real" encoder

        //starts active runmode, end init

        //updates telemetry
        telemetry.addData("Status: ","Waiting for Start");
        telemetry.update();

        waitForStart();//waits until start command is sent

        //updates telemetry
        telemetry.addData("Status: ","Running...");
        telemetry.update();

        runMotorWithEncoder(10,0.6, 5);//run motors

    }

    /*
        Work method that rotates motor with encoder
     */
    private void runMotorWithEncoder(int distance, double speed, double time){

        if(opModeIsActive()){

            //sets target pos
            bot.frontRight.setTargetPosition(distance);
            bot.frontLeft.setTargetPosition(distance);
            bot.backLeft.setTargetPosition(distance);
            bot.backRight.setTargetPosition(distance);

            bot.setMode(DcMotor.RunMode.RUN_TO_POSITION);//sets mode to active mode

            passTime.reset();//resets clock to 0

            bot.frontRight.setPower(speed);
            bot.frontLeft.setPower(speed);
            bot.backLeft.setPower(speed);
            bot.backRight.setPower(speed);

            while(passTime.seconds() < time)//idles until time is out

            //sets all motor power to 0 to make sure bot is stopped
            bot.frontRight.setPower(0);
            bot.frontLeft.setPower(0);
            bot.backLeft.setPower(0);
            bot.backRight.setPower(0);

            bot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//sets mode back to encoder

        }//checks for active opmode

    }

}
