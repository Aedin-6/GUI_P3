package GameModel.Assets;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Player
{
    private static int lives = 10;
    private static int score = 0;

    public Player(int diff)
    {
        switch (diff)
        {
            case 0 -> score = score + 50;
            case 1 -> score = score + 75;
            case 2 -> score = score + 100;
        }
    }


    public static void LoseLife()
    {
        lives = lives - 1;
    }

    public static int GetScore()
    {
        return score;
    }
    public static int GetLives()
    {
        return lives;
    }

    public static void IncreaseScore(Paint color)
    {
        if (Color.RED == color)
        {
            score = score + 15;
        }
        else if (Color.DEEPSKYBLUE == color)
        {
            score = score +5;
        }
        else if (Color.YELLOWGREEN == color)
        {
            score = score +10;
        }
    }

    public static void ClearScore()
    {
        score = 0;
    }

    public static void ClearLives()
    {
        lives = 10;
    }
}
