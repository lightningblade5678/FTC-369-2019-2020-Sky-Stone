package org.firstinspires.ftc.teamcode.TestCode.MotorTests;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous
public class InitTest extends LinearOpMode {

    public void runOpMode(){
        DcMotor base = hardwareMap.get(DcMotor.class, "baseMotor");
        ModernRoboticsI2cRangeSensor range = (ModernRoboticsI2cRangeSensor) hardwareMap.get("armDistance");

        telemetry.addData("Distance: ",range.rawUltrasonic()+" - "+(5-range.rawUltrasonic()));
        telemetry.update();
        sleep(1000);

        base.setPower(1);

        while(range.rawUltrasonic() < 5){

            telemetry.addData("Distance: ",range.rawUltrasonic()+" - "+(-range.rawUltrasonic()));
            telemetry.update();

        }

        base.setPower(0.25);

        telemetry.addData("Done: ","INIT");
        telemetry.update();
        sleep(5000);
        base.setPower(0);
    }

}
