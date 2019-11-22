package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.hardware.Servo;

public class BotHook {

    Servo hook;

    public BotHook(Servo hook){
        this.hook = hook;
        raiseHook();
    }//basic constructor

    public void dropHook(){
        /*(!)*/hook.setPosition(0);//change vals later
    }//drops hook down

    public void raiseHook(){
        /*(!)*/hook.setPosition(0.3);//change vals later
    }//raises hook

    public boolean hookDown(){
        /*(!)*/return hook.getPosition() == 0;//change vals later
    }//returns status of hook, true if hook is down false if it is up

}
