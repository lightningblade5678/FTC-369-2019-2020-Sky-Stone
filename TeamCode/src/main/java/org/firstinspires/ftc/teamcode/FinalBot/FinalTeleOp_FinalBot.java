package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class FinalTeleOp_FinalBot extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;
        BotWheels wheels = bot.getWheels();
        BotHook hook = bot.hook;
        BotIntake intake = bot.intake;




        if (ifStick('y', 1, 'l')) {
            wheels.setPower(gamepad1.left_stick_y);
        }//Forwards and backwards

        if (ifStick('x', 1, 'l')) {
            wheels.strafe(gamepad1.left_stick_x);
        }//Strafing

        if (ifStick('x', 1, 'r')) {

            wheels.setPower(1, -gamepad1.right_stick_x);
            wheels.setPower(3, -gamepad1.right_stick_x);
            wheels.setPower(2, gamepad1.right_stick_x);
            wheels.setPower(4, gamepad1.right_stick_x);

        }//rotating

        if (gamepad1.a) {
            hookPos();
        }//hook toggle






        if (ifStick('y', 2, 'r')) {

            double power = gamepad2.right_stick_y;

            if (gamepad2.right_stick_y < -.3) {
                power = -.3;
            }//failsafe (doesn't slam arm down)

            arm.baseMotor.setPower(power);
        }//arm power


        if (ifStick('x', 2, 'l')) {
            arm.wristServo.setPower(gamepad2.left_stick_x / 10); //divides by 10 to account for sensitive servo

        }//wrist movement


        if(ifTrig(2, 'l')){
            intake.finger.setPower(-gamepad2.left_trigger);
        }else if(ifTrig(2, 'r')){
            intake.finger.setPower(gamepad2.right_trigger);
        }//finger movement

        if(gamepad2.a){
            handPos();
        }//hand movement

        intakeMovement(); //intake movement

    }

    private void hookPos(){

        FinalBot bot = new FinalBot(hardwareMap);
        BotHook hook = bot.hook;
        boolean pos = true;

        if(pos) {
            hook.raiseHook();
            pos = !pos;
        }else if(!pos){
            hook.dropHook();
            pos = !pos;
        }
    } //toggles hook pos when 'a' is pressed

    private void handPos(){

        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;
        boolean pos = true;

        if(pos) {
            arm.handGrab(true);
            pos = !pos;
        }else if(!pos){
            arm.handGrab(false);
            pos = !pos;
        }

    } //toggles hook pos when 'a' is pressed

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

    private boolean ifTrig(int gamepad, char side){
        if(gamepad == 1){
            if(side == 'l' || side == 'L'){
                if(gamepad1.left_trigger >= .05){
                    return true;
                }else{
                    return false;
                }
            }
            if(side == 'r' || side == 'R'){
                if(gamepad1.right_trigger >=.05){
                    return true;
                }else{
                    return false;
                }
            }
        }

        if(gamepad == 2){
            if(side == 'l' || side == 'L'){
                if(gamepad2.left_trigger >= .05){
                    return true;
                }else{
                    return false;
                }

            }
            if(side == 'r' || side == 'R'){
                if(gamepad2.right_trigger >= .05){
                    return true;
                }else{
                    return false;
                }
            }
        }




        return false;
    }

    private void intakePower(double x){

        FinalBot bot = new FinalBot(hardwareMap);
        BotIntake intake = bot.intake;

        intake.motors[0].setPower(x);
        intake.motors[1].setPower(-x);

    }

    private void intakeMovement(){
        if(gamepad2.dpad_up){
            intakePower(1);
        }else if(gamepad2.dpad_down){
            intakePower(0);
        }else if(gamepad2.dpad_right || gamepad2.dpad_left){
            intakePower(-1);
        }
    }

    private boolean ifDPad(int gamepad, char dir){
        if(gamepad == 1){
            if(dir == 'n' || dir == 'N'){
               if(gamepad1.dpad_up){
                   return true;
               }else{
                   return false;
               }
            }
            if(dir == 's' || dir == 'S'){
                if(gamepad1.dpad_down){
                    return true;
                }else{
                    return false;
                }
            }
            if(dir == 'w' || dir == 'W'){
                if(gamepad1.dpad_left){
                    return true;
                }else{
                    return false;
                }
            }
            if(dir == 'e' || dir == 'E'){
                if(gamepad1.dpad_right){
                    return true;
                }else{
                    return true;
                }
            }

        }


        if(gamepad == 2){
            if(dir == 'n' || dir == 'N'){
                if(gamepad2.dpad_up){
                    return true;
                }else{
                    return false;
                }
            }
            if(dir == 's' || dir == 'S'){
                if(gamepad2.dpad_down){
                    return true;
                }else{
                    return false;
                }
            }
            if(dir == 'w' || dir == 'W'){
                if(gamepad2.dpad_left){
                    return true;
                }else{
                    return false;
                }
            }
            if(dir == 'e' || dir == 'E'){
                if(gamepad2.dpad_right){
                    return true;
                }else{
                    return true;
                }
            }

        }
        return false;
    }
}
