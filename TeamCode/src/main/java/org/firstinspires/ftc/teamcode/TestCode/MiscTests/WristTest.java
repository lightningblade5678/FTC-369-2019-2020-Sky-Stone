package org.firstinspires.ftc.teamcode.TestCode.MiscTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.FinalBot.FinalBot;

@Autonomous
public class WristTest extends LinearOpMode {

    public void runOpMode(){

        FinalBot bot = new FinalBot(hardwareMap);
        bot.placeBlock();

        /*
        ElapsedTime time = new ElapsedTime(0);
        //FinalBot bot = new FinalBot(hardwareMap);
        CRServo ser = hardwareMap.get(CRServo.class,"wristServo");

        ser.setPower(-1);

        time.reset();
        while(time.seconds() < 0.1);

        ser.setPower(1);

        time.reset();
        while(time.seconds() < 0.1);

        ser.setPower(0);
        */
        //bot.arm.toggleWrist(false);

    }

}
