package GameModel.Assets;

import javafx.scene.paint.Color;

public class Enemy
{
    private String id = "e";
    private Color color;
    private int lives;
    private static int counter;

    Enemy(Color color)
    {
        id = id + counter;
        GetColorDiff(color);
    }

    private void GetColorDiff(Color color)
    {
        if (Color.RED.equals(color))
        {
            lives = 15;
        }
        else if (Color.GREEN.equals(color))
        {
            lives = 5;
        }
        else if (Color.YELLOW.equals(color))
        {
            lives = 10;
        }
        else
        {
            lives = 20;
        }

    }
    public void Clicked()
    {
        lives =-1;
    }
}
