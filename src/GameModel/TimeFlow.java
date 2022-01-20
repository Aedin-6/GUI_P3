package GameModel;


import javafx.scene.text.Text;

import java.time.LocalTime;
import static java.lang.Thread.sleep;

public class TimeFlow implements Runnable
{
    private static LocalTime time = LocalTime.ofSecondOfDay(1);

    Text timeLabel;
    public TimeFlow(Text timeLabel)
    {
        this.timeLabel = timeLabel;
    }
    @Override
    public void run()
    {
        try
        {
            while(!GameFlow.over)
            {
                TimeIncrese();
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Something interrupted the date.");
        }
    }

    private synchronized void TimeIncrese() throws InterruptedException
    {
            timeLabel.setText("Time: " + time.toString());
            time = time.plusSeconds(1);
            sleep(1000);
    }

    public static String GetTime()
    {
        return time.toString();
    }
}
