package org.firstinspires.ftc.teamcode.FinalBot;

import com.qualcomm.robotcore.hardware.Servo;

public class BotHook {

    Servo hook;

    public BotHook(Servo hook){
        this.hook = hook;
    }

    public void dropHook(){
        /*(!)*/hook.setPosition(0);//change vals later
    }

    public void raiseHook(){
        /*(!)*/hook.setPosition(1);//change vals later
    }

}
