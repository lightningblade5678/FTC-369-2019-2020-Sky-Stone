package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp
public class FinalTeleOp_FinalBot extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;


    }


    private boolean ifStick(char axis, int gamepad, char side) {

        if (axis == 'x' || axis == 'X') {

            if (gamepad == 1) {

                if (side == 'l' || side == 'L') {

                    if (gamepad1.left_stick_x >= -1) {
                        return true;
                    } else {
                        return false;
                    }

                }
                if (side == 'r' || side == 'R') {
                    if (gamepad1.right_stick_x >= -1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }

            if (gamepad == 2) {
                if (side == 'l' || side == 'L') {
                    if (gamepad2.left_stick_x >= -1) {
                        return true;
                    } else {
                        return false;
                    }
                }
                if (side == 'r' || side == 'R') {

                    if (gamepad2.right_stick_x >= -1) {
                        return true;
                    } else {
                        return false;
                    }

                }

            }
        } else if (axis == 'y' || axis == 'Y') {
            if (gamepad == 1) {
                if (side == 'l' || side == 'L') {
                    if (gamepad1.left_stick_y >= -1) {
                        return true;
                    } else {
                        return false;
                    }
                }
                if (side == 'r' || side == 'R') {
                    if (gamepad1.right_stick_y >= -1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            if (gamepad == 2) {

                if (side == 'l' || side == 'L') {
                    if (gamepad2.left_stick_y >= -1) {
                        return true;
                    } else {
                        return false;
                    }
                }
                if (side == 'r' || side == 'R') {
                    if (gamepad2.right_stick_y >= -1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
