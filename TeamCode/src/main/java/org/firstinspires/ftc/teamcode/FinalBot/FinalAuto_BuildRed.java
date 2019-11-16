package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="BuildRed")
public class FinalAuto_BuildRed extends LinearOpMode {




    @Override
    public void runOpMode() throws InterruptedException {

        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;
        BotWheels wheels = bot.getWheels();

        //runs robot up over build plate edge, then backs up and drags plate


        wheels.setPower(-1);
        sleep(3000);

        wheels.setPower(1, 1);
        wheels.setPower(4, 1);
        wheels.setPower(2, -.2); //moves front wheels forward to keep robot on plate
        wheels.setPower(3, -.2); //moves front wheels forward to keep robot on plate
        sleep(10000);
        wheels.setPower(0);
        sleep(100);
        wheels.setPower(1);
        sleep(3000);
        wheels.setPower(0);





     }
}
