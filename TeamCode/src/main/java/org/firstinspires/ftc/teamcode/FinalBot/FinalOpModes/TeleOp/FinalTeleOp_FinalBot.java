/*
Gamepad 1 (movement):

    -Left Joystick
        -Forwards and Backwards (y-axis)
        -Strafing (x-axis)

    -Right Joystick
        - Turning (x-axis)

    -Button A/B
        -Toggles hook position (0/1)

    -Button X + Y + Triggers
        -Drops capstone


Gamepad 2 (arm, intake, claw):

    -Right Joystick
        -Base Motor movement (y-axis)

    -Triggers
        -Finger Movement

    -Left Joystick
        -Wrist Movement (x-axis)

    -Button A/B/Y
        -Hand Movement
            - A = Up
            - B = Down
    -D-Pad
         -Intake
 */

package org.firstinspires.ftc.teamcode.FinalBot.FinalOpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotArm;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotHook;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotIntake;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.BotWheels;
import org.firstinspires.ftc.teamcode.FinalBot.Internal_Code.FinalBot;

@TeleOp(name="!!MainTeleOp_FinalBot")
public class FinalTeleOp_FinalBot extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {

        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;
        BotWheels wheels = bot.getWheels();
        BotHook hook = bot.hook;
        BotIntake intake = bot.intake;
/*
        //initializes to average pos
        arm.dropCap.setPosition(.5);
        intake.finger.setPower(0);
        arm.wristServo.setPosition(.5);
        wheels.setPower(0);
        arm.baseMotor.setPower(0);
        arm.handGrab(false);
*/
        waitForStart();



        while (opModeIsActive()) {
            

            //                  [!] CONTROLLER 1 [!]
            if (ifStick('y', 1, 'l', .5)) {
                wheels.setPower(gamepad1.left_stick_y);
            }//Forwards and backwards

            else if (ifStick('x', 1, 'l', .5)) {

                wheels.setPower(0, gamepad1.left_stick_x);
                wheels.setPower(1, gamepad1.left_stick_x);
                wheels.setPower(2, -gamepad1.left_stick_x);
                wheels.setPower(3, -gamepad1.left_stick_x);

            }//Strafing

            else if (ifStick('x', 1, 'r', .5)) {

                wheels.setPower(0, gamepad1.right_stick_x);
                wheels.setPower(1, -gamepad1.right_stick_x);
                wheels.setPower(2, gamepad1.right_stick_x);
                wheels.setPower(3, -gamepad1.right_stick_x);

            }//rotating

            if (gamepad1.a) {
                hookPos();
            }//hook toggle

            if(gamepad1.x && gamepad1.y && gamepad1.left_trigger > .5 && gamepad1.right_trigger > .5){ //hard to do accidentally
            arm.dropCap.setPosition(1); //check pos
            } //drops capstone




            //                  [!] CONTROLLER 2 [!]
            if (ifStick('y', 2, 'r', .5)) {

                double power = gamepad2.right_stick_y;

                if (gamepad2.right_stick_y < -.3) {
                    power = -.3;
                }//failsafe (doesn't slam arm down)

                arm.baseMotor.setPower(power);
            }//arm power


            if (ifStick('x', 2, 'l', .5)) {
                double pos = arm.wristServo.getPosition();

                if(gamepad2.left_stick_x < 0){
                    arm.wristServo.setPosition(pos - .05);
                }else if(gamepad2.left_stick_x > 0){
                    arm.wristServo.setPosition(pos + .05);
                }


            }//wrist movement


            if (ifTrig(2, 'l')) {
                intake.finger.setPower(-gamepad2.left_trigger);
            } else if (ifTrig(2, 'r')) {
                intake.finger.setPower(gamepad2.right_trigger);
            }//finger movement

            if (gamepad2.a) {
                handPos();
            }//hand movement

            intakeMovement(); //intake movement


            if(!ifStick('x', 1, 'l', .5) && !ifStick('y', 1, 'l', .5)){
                wheels.setPower(0);
            }else if(!ifStick('x', 1, 'r', .5)){
                wheels.setPower(0);
            }//sets wheel power to 0 if joystick is at resting pos

        }


    }





    //                    [!] METHODS [!]
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

    private boolean ifStick(char axis, int gamepad, char side, double deadzone) { //full of errors

        if (axis == 'x' || axis == 'X') {

            if (gamepad == 1) {

                if (side == 'l' || side == 'L') {

                    if (Math.abs(gamepad1.left_stick_x) >= deadzone) {
                        return true;
                    } else {
                        return false;
                    }

                }
                if (side == 'r' || side == 'R') {
                    if (Math.abs(gamepad1.right_stick_x) >= deadzone) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }

            if (gamepad == 2) {
                if (side == 'l' || side == 'L') {
                    if (Math.abs(gamepad2.left_stick_x) >= deadzone) {
                        return true;
                    } else {
                        return false;
                    }
                }
                if (side == 'r' || side == 'R') {

                    if (Math.abs(gamepad2.right_stick_x) >= deadzone) {
                        return true;
                    } else {
                        return false;
                    }

                }

            }
        } else if (axis == 'y' || axis == 'Y') {
            if (gamepad == 1) {
                if (side == 'l' || side == 'L') {
                    if (Math.abs(gamepad1.left_stick_y) >= Math.abs(deadzone)) {
                        return true;
                    } else {
                        return false;
                    }
                }
                if (side == 'r' || side == 'R') {
                    if (Math.abs(gamepad1.right_stick_y) >= deadzone) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            if (gamepad == 2) {

                if (side == 'l' || side == 'L') {
                    if (Math.abs(gamepad2.left_stick_y) >= deadzone) {
                        return true;
                    } else {
                        return false;
                    }
                }
                if (side == 'r' || side == 'R') {
                    if (Math.abs(gamepad2.right_stick_y) >= deadzone) {
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
            intakePower(.3);
        }else if(gamepad2.dpad_down){
            intakePower(0);
        }else if(gamepad2.dpad_right || gamepad2.dpad_left){
            intakePower(-.3);
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

    private char domAxis(int gamepad, char side){
        if(gamepad == 1){
            if(side == 'l' || side == 'L'){
                if(Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.left_stick_y)){
                    return 'x';
                }else if (Math.abs(gamepad1.left_stick_x) < Math.abs(gamepad1.left_stick_y)){
                    return 'y';
                }else{
                    return '-';
                }
            }
            if(side == 'r' || side == 'R'){
                if(Math.abs(gamepad1.right_stick_x) > Math.abs(gamepad1.right_stick_y)){
                    return 'x';
                }else if (Math.abs(gamepad1.right_stick_x) < Math.abs(gamepad1.right_stick_y)){
                    return 'y';
                }else{
                    return '-';
                }
            }
        }

        if(gamepad == 2){
            if(side == 'l' || side == 'L'){

                if(Math.abs(gamepad2.left_stick_x) > Math.abs(gamepad2.left_stick_y)){
                    return 'x';
                }else if (Math.abs(gamepad2.left_stick_x) < Math.abs(gamepad2.left_stick_y)){
                    return 'y';
                }else{
                    return '-';
                }

            }else if(side == 'r' || side == 'R') {

                if (Math.abs(gamepad2.right_stick_x) > Math.abs(gamepad2.right_stick_y)) {
                    return 'x';
                } else if (Math.abs(gamepad2.right_stick_x) < Math.abs(gamepad2.right_stick_y)) {
                    return 'y';
                } else {
                    return '-';
                }
            }
        }

        return '-';
    }
}
