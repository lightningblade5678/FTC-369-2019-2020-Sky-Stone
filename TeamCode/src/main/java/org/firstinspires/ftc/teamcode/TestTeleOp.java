package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/*
    This is a test class used to test remote control (Teleop)
    All this class does is move left/Right motors based on imput
 */

@TeleOp
public class TestTeleOp extends LinearOpMode {

    private TestBot bot = new TestBot();//robot with all the motors

    private ElapsedTime passTime = new ElapsedTime(0);//"clock" to keep track of passed time

    /*
        Work method that runs OpMode and moves motors according to to controller input
     */
    @Override
    public void runOpMode(){

        bot.init(hardwareMap, DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        passTime.reset();//sets timer to 0

        while(passTime.seconds() < 20){
            bot.setPower(gamepad1.left_stick_y,gamepad1.right_stick_y);
        }

        bot.setPower(0);

    }

}
