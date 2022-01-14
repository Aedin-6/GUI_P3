package GameModel.Assets;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;

public class Enemy
{
    public static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private String id = "e";
    private int diff;
    private int lives;
    private Rectangle view;
    private static int counter;

    public Enemy(int diff)
    {
        id = id + counter;
        GetColorDiff(diff);

    }

    private void GetColorDiff(int diff)
    {
        if (diff == 2)
        {
            lives = 15;
        }
        else if (diff == 0)
        {
            lives = 5;
        }
        else if (diff == 1)
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

    public void AddView(Rectangle rectangle)
    {
        view = rectangle;
    }

    public Node GetView()
    {
        return view;
    }
}
