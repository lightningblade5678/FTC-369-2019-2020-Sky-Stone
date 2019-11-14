//    [!] CHECK DEVICE NAMES [!]

//    [!] DO NOT RUN UNTIL PARAMETERS ARE CHECKED [!]

package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="BrickRed")
public class FinalAuto_BrickRed extends LinearOpMode {

    private static final double COUNTS_PER_MOTOR_REV = 1120;
    private static final double DRIVE_GEAR_REDUCTION = 1;
    private static final double WHEEL_DIAMETER_INCHES = 4.0;
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);

    FinalBot bot = new FinalBot(hardwareMap);


    private ElapsedTime time = new ElapsedTime();
    public BotWheels wheels;
    public BotArm arm;

    @Override
    public void runOpMode() throws InterruptedException {


        // [!] VALUES MAY VARY
        time.reset();
        bot.move(0, 30.5); //Move the bot to in front of the bricks, within scanning distance


        bot.intakeTime( 30); // [!] Check parameters!

        bot.move(24, -24); //Moves bot to under the bridge
        bot.rotate(90); //Turns bot to face build platform
        bot.move(0, 36); //Moves bot to in front of build platform


        //Moves bot to in front of the build platform
        while (!bot.detectColor()) {
            bot.move(0, 1);
        }
        if(bot.detectColor()){
            bot.move(0, -10);
            bot.rotate(180);
            bot.move(0, -10);
            bot.placeBlock(1);
        }

        bot.move(0, 18); //Moves bot onto midline [!] Check params

    }
}