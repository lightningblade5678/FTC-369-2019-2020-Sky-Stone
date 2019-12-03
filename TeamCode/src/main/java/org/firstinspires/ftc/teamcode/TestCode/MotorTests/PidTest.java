package org.firstinspires.ftc.teamcode.TestCode.MotorTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/*
    The purpose of this program is to simply test PID control
 */

@Autonomous
public class PidTest extends LinearOpMode {

    public void runOpMode(){

        DcMotor motor = hardwareMap.get(DcMotor.class, "intakeLeft");

        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//start PID

        telemetry.addData("Curr Pos: ",motor.getCurrentPosition());

        

        telemetry.addData("Curr Pos: ",motor.getCurrentPosition());

    }

}
