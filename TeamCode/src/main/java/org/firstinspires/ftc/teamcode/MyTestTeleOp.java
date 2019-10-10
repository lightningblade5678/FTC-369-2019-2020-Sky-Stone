package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MyTestTeleOp extends OpMode {

    private DcMotor leftForwardMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightForwardMotor;
    private DcMotor rightBackMotor;

    public void init(){

        leftForwardMotor = hardwareMap.dcMotor.get("frontLeft");
        leftBackMotor = hardwareMap.dcMotor.get("backLeft");
        rightForwardMotor = hardwareMap.dcMotor.get("frontRight");
        rightBackMotor = hardwareMap.dcMotor.get("backRight");

    }

    public void loop(){

        leftForwardMotor.setPower(0);
        leftBackMotor.setPower(0);
        rightForwardMotor.setPower(0);
        rightBackMotor.setPower(0);

    }

}
