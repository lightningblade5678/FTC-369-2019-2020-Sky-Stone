package org.firstinspires.ftc;
//movement
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
@TeleOp(name="Correct Teleop", group="K9bot")
public class TeleOPNotFinalEdition extends OpModeBase{
    HardwareK9bot   robot         = new HardwareK9bot();
    @Override
    public void runOpMode()
    {
        robot.init(hardwareMap);
        waitForStart();
        while(opModeIsActive())
        //turning
        {
            double turn = getGamepad(1).right_stick_x;
            double lateral = getGamepad(1).left_stick_x;
            double forward = -getGamepad(1).left_stick_y;

            double leftDrive, leftBack, rightDrive, rightBack;

            leftDrive = forward + lateral + turn;
            leftBack = forward - lateral +  turn;
            rightDrive = forward - lateral - turn;
            rightBack = forward + lateral - turn;

            telemetry.addData("Controller X:", getGamepad(1).left_stick_x);
            telemetry.addData("Controller Y:", getGamepad(1).left_stick_y);
//controls
            robot.shaft1.setPower(getGamepad(2).right_trigger- getGamepad(2).left_trigger);
            robot.leftDrive.setPower(leftDrive);
            robot.leftBack.setPower(leftBack);
            robot.rightDrive.setPower(rightDrive);
            robot.rightBack.setPower(rightBack);

            robot.shaft2.setPower(getGamepad(2).left_stick_y);

            if(getGamepad(2).x)
                robot.rake.setPower(.5);
            else if(getGamepad(2).y)
                robot.rake.setPower(-.5);
            else
                robot.rake.setPower(0);


            telemetry.update();
            sleep(20);
        }

    }
    private Gamepad getGamepad(int number) {
        if(number == 1)
            return gamepad1;
        else if (gamepad2 == null)
            return gamepad1;
        else
            return gamepad2;
    }
}
