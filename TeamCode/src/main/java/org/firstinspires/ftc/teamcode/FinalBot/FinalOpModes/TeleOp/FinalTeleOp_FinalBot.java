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


    public boolean hookPos = true;
    public boolean handPos = true;

    @Override
    public void runOpMode() throws InterruptedException {

        FinalBot bot = new FinalBot(hardwareMap);
        BotArm arm = bot.arm;
        BotWheels wheels = bot.getWheels();
        BotHook hook = bot.hook;
        BotIntake intake = bot.intake;

        arm.wristServo.setPosition(0);
        hook.dropHook();
        //initializes to average pos
        /*intake.finger.setPower(0);
        wheels.setPower(0);
        arm.baseMotor.setPower(0)
        arm.handGrab(false);
*/
        waitForStart();


        while (opModeIsActive()) {
            

            //                  [!] CONTROLLER 1 [!]
            if (ifStick('y', 1, 'l', .5)) {
                wheels.setPower(0, -gamepad1.left_stick_y);
                wheels.setPower(1, -gamepad1.left_stick_y);
                wheels.setPower(2, -gamepad1.left_stick_y*.95);
                wheels.setPower(3, -gamepad1.left_stick_y*.95);

            }//Forwards and backwards

            else if (ifStick('x', 1, 'l', .5)) {

                wheels.setPower(0, gamepad1.left_stick_x);
                wheels.setPower(1, -gamepad1.left_stick_x);
                wheels.setPower(2, -gamepad1.left_stick_x*.95);
                wheels.setPower(3, gamepad1.left_stick_x*.95);

            }//Strafing

            else if (ifStick('x', 1, 'r', .5)) {

                wheels.setPower(0, gamepad1.right_stick_x);
                wheels.setPower(1, -gamepad1.right_stick_x);
                wheels.setPower(2, gamepad1.right_stick_x*.95);
                wheels.setPower(3, -gamepad1.right_stick_x*.95);

            }//rotating

            else if(ifDPad(1, 'n')){
                wheels.setPower(0, .2);
                wheels.setPower(1, .2);
                wheels.setPower(2, .2*.95);
                wheels.setPower(3, .2*.95);
            }else if(ifDPad(1, 's')){
                wheels.setPower(0, -.2);
                wheels.setPower(1, -.2);
                wheels.setPower(2, -.2*.95);
                wheels.setPower(3, -.2*.95);
            }/*else if(ifDPad(1, 'w')) {
                wheels.setPower(0, -.2);
                wheels.setPower(1, .2);
                wheels.setPower(2, .2 * .95);
                wheels.setPower(3, -.2 * .95);
            }else if(ifDPad(1, 'e')) {
                wheels.setPower(0, .2);
                wheels.setPower(1, -.2);
                wheels.setPower(2, -.2 * .95);
                wheels.setPower(3, .2 * .95);
            }*/ //slow move omnidirectional
            telemetry.update();

            if (gamepad1.a) {
            hookPos(bot.hook);
            while(gamepad1.a);
            }//hook toggle






            //                  [!] CONTROLLER 2 [!]
            if (ifStick('y', 2, 'r', .5)) {

                double power = gamepad2.right_stick_y;
                if (power > .5){
                    power = .5;
                }
                if (power < -.1) {
                    power = -.1;
                }//failsafe (doesn't slam arm down)

                arm.baseMotor.setPower(power);
            }//arm power


            else if (ifStick('x', 2, 'l', .5)) {
                double pos = arm.wristServo.getPosition();

                if(gamepad2.left_stick_x < 0){
                    arm.wristServo.setPosition(pos - .05);
                }else if(gamepad2.left_stick_x > 0){
                    arm.wristServo.setPosition(pos + .05);
                }

            }//wrist movement


            if(ifTrig(2, 'l')){
                intake.finger.setPower(1);
                while(ifTrig(2, 'l'));
                intake.finger.setPower(0);
            }else if(ifTrig(2, 'r')){
                intake.finger.setPower(-1);
                while(ifTrig(2, 'r'));
                intake.finger.setPower(0);
            }//finger movement

            if (gamepad2.a) {
                handPos(bot.arm);
                while(gamepad2.a);
            }//hand movement

            intakeMovement(bot.intake); //intake movement

            if(gamepad1.left_bumper && gamepad1.right_bumper && wheels.getWheel(1).getPower() == 0){
                arm.dropCap.setPosition(0);
                sleep(1000);
                arm.dropCap.setPosition(1);

                while(gamepad1.left_bumper || gamepad1.right_bumper);
            }//DropCap toggle

            if(gamepad1.left_stick_x == 0 && gamepad1.left_stick_y == 0 && gamepad1.right_stick_x == 0 && gamepad1.right_stick_y == 0 && !gamepad1.dpad_up && !gamepad1.dpad_down && !gamepad1.dpad_right && !gamepad1.dpad_left){
                wheels.setPower(0);
            }

        }

    }





    //                    [!] METHODS [!]
    private void hookPos(BotHook hook){
        if(hookPos) {
            hook.raiseHook();
            hookPos = !hookPos;
        }else if(!hookPos){
            hook.dropHook();
            hookPos = !hookPos;
        }
    } //toggles hook pos when 'a' is pressed

    private void handPos(BotArm arm){
        if(handPos) {
            arm.handGrab(true);
            handPos = !handPos;
        }else if(!handPos){
            arm.handGrab(false);
            handPos = !handPos;
        }
    } //toggles hand pos when 'a' is pressed

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

    private void intakePower(double x, BotIntake intake){

        intake.motors[0].setPower(x);
        intake.motors[1].setPower(-x);

    }

    private void intakeMovement(BotIntake intake){
        if(gamepad2.dpad_up){

            intakePower(.3, intake);
        }else if(gamepad2.dpad_down){
            intakePower(0, intake);
        }else if(gamepad2.dpad_right || gamepad2.dpad_left){
            intakePower(-.3, intake);
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
                    return false;
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
                    return false;
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
