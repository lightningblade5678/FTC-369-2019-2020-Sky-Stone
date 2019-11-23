package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="!!!TEST!!!")
public class TEST extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        FinalBot bot = new FinalBot(hardwareMap);
        BotWheels wheels = bot.getWheels();

        wheels.setPower(0, -1);//frontleft
        wheels.setPower(1, 1);//frontright

        wheels.setPower(2, -1);//backleft
        wheels.setPower(3, 1);//backright

        sleep(2000);

        wheels.setPower(0);


    }
}
