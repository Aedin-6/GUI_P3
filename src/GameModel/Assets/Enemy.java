package GameModel.Assets;

import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.util.ArrayList;

public class Enemy
{
    public static ArrayList<Enemy> enemyList = new ArrayList<>();
    private String id = "e";
    private int lives;
    private ImageView img;
    private static int counter;
    private boolean isDead;
    private int color;
    private int leftOrRight;
    private MediaPlayer quackPlayer;

    public Enemy(int diff)
    {
        counter++;
        id = id + counter;
        isDead = false;
        color = ((int) (Math.random() * 3));
        img = AddImg();
        leftOrRight = (int) (Math.random() * 2);
        if(leftOrRight == 1)
        {
            img.setX(img.getX()+2400);
            if(color == 1)
                img.setImage(new Image("file:duckBluefliped.png"));
            if(color == 2)
                img.setImage(new Image("file:duckRedfliped.png"));
            if(color == 0)
                img.setImage(new Image("file:duckfliped.png"));
            img.setFitWidth(70);
            img.setFitHeight(70);
        }

        GetColorDiff(diff);
        Clicked();

    }
    public Enemy(boolean menu)
    {
        id = id + "m" + counter;
        img = AddMenuImg();
    }

    private ImageView AddMenuImg()
    {
        ImageView enemy = new ImageView();
        enemy.setImage(new Image("file:duck.png"));
        enemy.setX((int)(Math.random()*400));
        enemy.setY((int)(Math.random()*600));
        enemy.setFitWidth(70);
        enemy.setFitHeight(70);
        enemy.setMouseTransparent(true);
        enemy.toFront();
        return enemy;
    }

    private ImageView AddImg()
    {
        ImageView enemy = new ImageView();
        if(color == 1)
            enemy.setImage(new Image("file:duckBlue.png"));
        if(color == 2)
            enemy.setImage(new Image("file:duckRed.png"));
        if(color == 0)
            enemy.setImage(new Image("file:duck.png"));
        enemy.setX((int)(Math.random()*(-1200)));
        enemy.setY((int)(Math.random()*560));
        enemy.setFitWidth(70);
        enemy.setFitHeight(70);
        enemy.toFront();

        return enemy;
    }


    private void GetColorDiff(int color)
    {
        if (color == 2)
        {
            lives = 15;
        }
        else if (color == 0)
        {
            lives = 5;
        }
        else if (color == 1)
        {
            lives = 10;
        }
    }


    public void Clicked()
    {
        img.setOnMouseClicked(mouseEvent ->
        {
            lives = lives - 1;
            FadeTransition _fadeTransition = new FadeTransition(Duration.seconds(0.2), img);
            _fadeTransition.setFromValue(1.0);
            _fadeTransition.setToValue(0.0);
            _fadeTransition.setAutoReverse(true);
            _fadeTransition.setCycleCount(2);
            _fadeTransition.play();
            if (CheckDestroy())
                Destroy();
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
        quackPlayer = new MediaPlayer(new Media(getClass().getResource("/quack.wav").toExternalForm()));
        quackPlayer.setVolume(1.0);
        quackPlayer.play();

        img.setVisible(false);
        img.setMouseTransparent(true);
    }

    public boolean CheckDead()
    {
        return isDead;
    }

    public void Kill()
    {
        isDead = true;
    }

    public int GetSide()
    {
        return leftOrRight;
    }

    public ImageView GetImg()
    {
        return img;
    }
}
