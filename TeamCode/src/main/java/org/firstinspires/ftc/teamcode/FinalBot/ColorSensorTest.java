package org.firstinspires.ftc.teamcode.FinalBot;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous (name = "ColorSensor")
public class ColorSensorTest extends LinearOpMode {


    public ElapsedTime time = new ElapsedTime(0);
    public DcMotor frontLeft;
    public ColorSensor toneObserver;

    public void runOpMode() {


        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        toneObserver = hardwareMap.get(ColorSensor.class, "toneObserver");

        //turn on Color Sensor LED
        toneObserver.enableLed(true);

       time.reset();

        //Array contianing Hue, Saturation, and Value of sensor
        float hsvValues[] = {0F, 0F, 0F};


       while (time.seconds() <= 60){

            //Convert RGB to HSV and amplifies the values
            Color.RGBToHSV(toneObserver.red() * 8, toneObserver.green() * 8, toneObserver.blue() * 8, hsvValues);

           telemetry.addData("Detecting:", "Nothing");
           
            //Displays RGB and HSV values on phone
            telemetry.addData("Red:", toneObserver.red());
            telemetry.addData("Green:", toneObserver.green());
            telemetry.addData("Blue:", toneObserver.blue());
            telemetry.addData("Hue:", hsvValues[0]);
            telemetry.addData("Saturation:", hsvValues[1]);
            telemetry.addData("Value:", hsvValues[2]);

            telemetry.update();


            // 19 11 1 -- RGB

           if (17 < toneObserver.red() && toneObserver.red() < 21 && 9 < toneObserver.green() && toneObserver.green() < 13 && 0 < toneObserver.blue() && toneObserver.blue() < 3){
               telemetry.addData("Detecting:", "Yellow");
               telemetry.update();
               frontLeft.setPower(1);
           }
           else {
               frontLeft.setPower(0);
               telemetry.addData("Detecting:", "Nothing");
               telemetry.update();
           }


        }

    }

}
