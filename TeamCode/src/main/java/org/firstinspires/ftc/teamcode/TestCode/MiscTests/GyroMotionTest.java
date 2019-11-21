package org.firstinspires.ftc.teamcode.TestCode.MiscTests;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class GyroMotionTest extends LinearOpMode {

    /*
    Used to test gyroscope
     */

    @Override
    public void runOpMode(){

        ElapsedTime time = new ElapsedTime(0);
        GyroSensor gyro = hardwareMap.get(GyroSensor.class,"gyroscope");

        telemetry.addData("Gyro: ","Calibrating");
        telemetry.update();

        gyro.calibrate();
        while(gyro.isCalibrating());


        while(time.seconds() < 15){

            telemetry.addData("Heading: ", gyro.getHeading());
            telemetry.update();

        }
        

    }

}
