package org.firstinspires.ftc.teamcode;

/*
    This bot is for testing motor(s) only
    A robot with (4) motors used to test basic movement during initial stages of programming
 */

import com.qualcomm.robotcore.hardware.*; //imports robot-core hardware, imports all of them for convenience
import com.qualcomm.robotcore.util.ElapsedTime;

public class TestBot {

    //motor initialization

    public DcMotor backLeft;//name corresponds with motor location
    public DcMotor backRight;
    public DcMotor frontLeft;
    public DcMotor frontRight;

    //initialize other vars

    private HardwareMap hardwareMap;//map of where all the hardware is at, used to "link" motors to DCMotor class
    private ElapsedTime passTime;//"clock" to keep track of passed time

    //constructor(s)

    public TestBot() {

    }//default constructor

    //req. methods

    /*
        Run this before using the bot
        Maps motors to those specified by the hardwareMap and sets motor modes
     */
    public void init(HardwareMap hardMap, DcMotor.RunMode r) {
        hardwareMap = hardMap;//sets

        //sets motors
        backLeft = hardwareMap.get(DcMotor.class, "");
        backRight = hardwareMap.get(DcMotor.class, "");
        frontLeft = hardwareMap.get(DcMotor.class, "");
        frontRight = hardwareMap.get(DcMotor.class, "");
        //NOTE: fill "" with device name

        backLeft.setMode(r);
        backRight.setMode(r);
        frontLeft.setMode(r);
        frontRight.setMode(r);

    }


}
