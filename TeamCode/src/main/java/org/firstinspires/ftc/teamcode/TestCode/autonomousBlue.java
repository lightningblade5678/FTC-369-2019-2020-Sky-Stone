package org.firstinspires.ftc.teamcode.TestCode;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

        import org.firstinspires.ftc.teamcode.FinalBot.FinalBot;

@Autonomous(name="blue")
public class autonomousBlue extends LinearOpMode {
    public FinalBot bot;
    @Override
    public void runOpMode()
    {
        bot.move (32.0, 0.0);
        bot.grabTray();
        bot.move (-30.0, 0.0);
        bot.grabTray();
        bot.rotate (225.0);
        bot.move (-87.0, 0.0);
        bot.rotate (90.0);
        bot.move (28.5, 0.0);
        bot.colorsense (black);
    if (positive bot.move (34.5,0.0))
        else ( bot.move (0.0,-1.0) until positive);
        bot.intake (5.0);
        bot.rotate (90.0);
        bot.move ( 48.0, 0.0);
        bot.rotate (90.0);
        bot.move (48.0, 0.0);
        bot.rotate(90.0);
        bot.move (7.0);
        bot.grabBlock();
        bot.place();
        if( time.seconds=>30 @override);

    }

}


