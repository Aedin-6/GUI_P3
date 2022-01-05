package GameModel.Assets;

import javafx.scene.paint.Color;

public class Player
{
    private int lives = 10;
    private static int score;

    Player(){};

    public void ReduceLives()
    {
        lives=-1;
    }

    public void IncreaseScore(Color color)
    {
        if (Color.RED.equals(color))
        {
            score += 15;
        }
        else if (Color.GREEN.equals(color))
        {
            score += 5;
        }
        else if (Color.YELLOW.equals(color))
        {
            score += 10;
        }
        else
        {
            score += 20;
        }
    }
}
