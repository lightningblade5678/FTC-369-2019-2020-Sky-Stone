/*package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.FinalBot.BotArm;
import org.firstinspires.ftc.teamcode.FinalBot.BotIntake;
import org.firstinspires.ftc.teamcode.FinalBot.BotWheels;
import org.firstinspires.ftc.teamcode.FinalBot.FinalBot;

@TeleOp
public class MyTestTeleOp extends OpMode {

    FinalBot test;

    private BotWheels wheels = new BotWheels(hardwareMap.dcMotor.get("frontLeft"),hardwareMap.dcMotor.get("frontRight"),
                               hardwareMap.dcMotor.get("backLeft"),hardwareMap.dcMotor.get("backRight"));
    private BotIntake intake = new BotIntake(hardwareMap.dcMotor.get("intakeLeft"),hardwareMap.dcMotor.get("intakeRight"),
                                hardwareMap.touchSensor.get(""));
    private BotArm arm = new BotArm(new DcMotor []{hardwareMap.dcMotor.get("swing")});


    public void init(){

        telemetry.addData("Status", "Initializing...");

        test = new FinalBot(wheels,intake,arm);

        telemetry.addData("Status", "Initialized");


    }

    public void loop(){

        double power;
        double turn;

        double force = g

    }

}
*/