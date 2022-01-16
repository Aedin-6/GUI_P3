package GameModel.Assets;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;


import java.util.ArrayList;

public class Enemy
{
    public static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private String id = "e";
    private int diff;
    private int lives;
    private Rectangle view;
    private static int counter;
    private boolean isDead;
    private Color color;

    public Enemy(int diff)
    {
        id = id + counter;
        isDead = false;
        view = AddView();
        GetColorDiff(diff);
        Clicked();
    }


    private void GetColorDiff(int diff)
    {
        if (diff == 2)
        {
            lives = 15;
            view.setFill(Color.RED);
            color = Color.RED;
        }
        else if (diff == 0)
        {
            lives = 5;
            view.setFill(Color.DEEPSKYBLUE);
            color = Color.DEEPSKYBLUE;
        }
        else if (diff == 1)
        {
            lives = 10;
            view.setFill(Color.YELLOWGREEN);
            color = Color.YELLOWGREEN;
        }
    }

    public Rectangle AddView()
    {
        Rectangle rect = new Rectangle();
        rect.setWidth(30);
        rect.setHeight(30);
        rect.setFill(Color.DEEPSKYBLUE);
        rect.setX((int)(Math.random()*(-1200)));
        rect.setY((int)(Math.random()*600));
        rect.setRotate((int)(Math.random()*360));
        rect.setStroke(Color.SKYBLUE);
        rect.setStrokeWidth(5);
        rect.setArcHeight(5);
        rect.setArcWidth(5);
        rect.toFront();
        return rect;
    }

    public Rectangle GetView()
    {
        return view;
    }

    public void Clicked()
    {
        view.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                lives= lives -1;
                view.setFill(Color.rgb((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
                FadeTransition _fadeTransition = new FadeTransition(Duration.seconds(0.2), view);
                _fadeTransition.setFromValue(1.0);
                _fadeTransition.setToValue(0.0);
                _fadeTransition.setAutoReverse(true);
                _fadeTransition.setCycleCount(2);
                _fadeTransition.play();
                if(CheckDestroy())
                    Destroy();
            }
        });

    }
     public boolean CheckDestroy()
     {
         return lives <= 0;
     }

    public void Destroy()
    {

        Player.IncreaseScore(color);
        Kill();
        view.setVisible(false);
        view.setHeight(1);
        view.setWidth(1);
    }

    public boolean CheckDead()
    {
        return isDead;
    }

    public void Kill()
    {
        isDead = true;
    }
}
