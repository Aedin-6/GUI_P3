package GameModel;

import java.time.LocalTime;
import static java.lang.Thread.sleep;

public class TimeFlow implements Runnable
{
    public static LocalTime time = LocalTime.ofSecondOfDay(1);

    @Override
    public void run()
    {
        try
        {
            TimeIncrese();
        }
        catch (InterruptedException e)
        {
            System.out.println("Something interrupted the date.");
        }
    }

    private synchronized void TimeIncrese() throws InterruptedException
    {
        for (int i = 0; i<Integer.MAX_VALUE; i++)
        {
            time = time.plusSeconds(1);
            sleep(1000);
            System.out.println(time);
        }
    }
}
