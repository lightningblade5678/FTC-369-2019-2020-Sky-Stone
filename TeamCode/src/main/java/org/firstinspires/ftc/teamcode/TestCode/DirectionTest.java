package org.firstinspires.ftc.teamcode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.FinalBot.BotArm;
import org.firstinspires.ftc.teamcode.FinalBot.FinalBot;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous
public class DirectionTest extends LinearOpMode {


    FinalBot bot = new FinalBot(hardwareMap);
    public BotArm arm = bot.arm;

    private double power = .1;

    @Override
    public void runOpMode() throws InterruptedException {

     telemetry.addData("Arm Power", "Positive");
     telemetry.update();

     wait(2000);

     arm.baseMotor.setPower(power);
     wait(2000);
     arm.baseMotor.setPower(0);

     telemetry.addData("Arm Power", "Negative");
     telemetry.update();

     wait(2000);

     arm.baseMotor.setPower(-power);
     wait(2000);
     arm.baseMotor.setPower(0);

     telemetry.addData("Wrist Position", "1");
     telemetry.update();

     wait(2000);

     arm.wristServo.setPosition(1);

     wait(2000);

     telemetry.addData("Wrist Position", "0");
     telemetry.update();

     wait(2000);

     arm.wristServo.setPosition(0);

     wait(2000);

     telemetry.addData("Hand Position", "1");
     telemetry.update();
     arm.handServo.setPosition(1);

     wait(2000);

     telemetry.addData("Hand Position", "0");
     telemetry.update();
     arm.handServo.setPosition(0);

     wait(2000);

    }
}
