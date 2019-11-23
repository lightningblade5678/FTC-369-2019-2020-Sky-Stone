// [!] START WITH TWO LEFT WHEELS ON WALLS (facing towards closest wall)

package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="!.BrickRedAuto")
public class FinalAuto_BrickRed extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;
        BotWheels wheels = bot.getWheels();

        //sets arm to 18x18x18 box pos
        arm.baseRotateDegree(30, 1);
        arm.baseMotor.setPower(.25);

        waitForStart();

        //sets arm back down
        arm.baseMotor.setPower(0);

        //strafes bot to infront of build plate
        bot.move(24, 0);
        bot.move(0, -72);

        //grabs, moves, and places block [!] dysfunctional
        arm.handGrab(true);
        bot.placeBlock();
        arm.setGrabPos();

        //moves bot onto midline
        bot.move(0, 36);
        ;


    }
}
