package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.LinkedList;

/*
    This class is used to test the distance sensor
 */

@Autonomous(name = "testLinearOpMode")
public class DistanceSensorTest extends LinearOpMode {

    DistanceSensor dist = null;
    LinkedList<DcMotor> motors = null;

    private ElapsedTime passTime = new ElapsedTime(0);//"clock" to keep track of passed time

    @Override//overrides opMode
    public void runOpMode() {

        try{

            dist = hardwareMap.getAll(DistanceSensor.class).get(0);
            motors = (LinkedList<DcMotor>) hardwareMap.getAll(DcMotor.class);

        }catch (NullPointerException e){

        }

        passTime.reset();
        while(passTime.seconds() < 30){

            if(dist.getDistance(DistanceUnit.INCH) < 1){
                for(int i = 0; i < motors.size(); i++){
                    motors.get(i).setPower(1);
                }
            }else{
                for(int i = 0; i < motors.size(); i++){
                    motors.get(i).setPower(0);
                }
            }

        }//runs for 30 seconds

    }

}
