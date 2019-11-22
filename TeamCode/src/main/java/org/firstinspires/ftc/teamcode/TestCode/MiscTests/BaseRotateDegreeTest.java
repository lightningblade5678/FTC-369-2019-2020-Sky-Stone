package org.firstinspires.ftc.teamcode.TestCode.MiscTests;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.FinalBot.BotArm;
import org.firstinspires.ftc.teamcode.FinalBot.FinalBot;

@Autonomous
public class BaseRotateDegreeTest extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;

        arm.baseRotateDegree(arm.baseMotor, 10, 1); //moves arm up 10 degrees
        arm.baseMotor.setPower(.25);//holds arm at position
        sleep(2000);

        arm.baseRotateDegree(arm.baseMotor, 10, 1);
        arm.baseMotor.setPower(.25);
        sleep(2000);

        arm.baseRotateDegree(arm.baseMotor, 10, 1);
        arm.baseMotor.setPower(.25);
        sleep(2000);

        arm.baseRotateDegree(arm.baseMotor, 10, 1);
        arm.baseMotor.setPower(.25);
        sleep(2000);

        arm.baseRotateDegree(arm.baseMotor, 10, 1);
        arm.baseMotor.setPower(.25);
        sleep(2000);

        arm.baseRotateDegree(arm.baseMotor, 10, 1);
        arm.baseMotor.setPower(.25);
        sleep(2000);

        arm.baseMotor.setPower(0);

    }
}
