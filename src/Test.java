import GameModel.Assets.Enemy;
import GameModel.Assets.Obstacle;
import GameModel.Assets.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.image.ImageConsumer;
import java.io.FileInputStream;

public class Test extends Application
{
    Scene _scene;
    Pane pane;
    Thread th;
    Boolean over;
    Text gameOver = new Text();
    @Override
    public void start(Stage stage)
    {
         over = false;
         pane = new Pane();
        _scene = new Scene(pane,1200,600);


        try
        {
            FileInputStream inputImg = new FileInputStream("forest.png");
            Image img = new Image(inputImg);
            BackgroundImage bImg = new BackgroundImage(img,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background bGround = new Background(bImg);
            pane.setBackground(bGround);
        }
        catch (Exception ex){}

        for (int i = 0; i < 10; i++)
        {
            Enemy _enemy = new Enemy(0);
            pane.getChildren().add(_enemy.GetView());
            Enemy.enemyList.add(_enemy);
        }
        for (int i = 0 ; i<100; i++)
        {
            Obstacle _obstacle = new Obstacle();
            pane.getChildren().add(_obstacle.GetView());
            Obstacle.obstacleList.add(_obstacle);
        }

        Text score = new Text();
        score.setX(0 + (_scene.getWidth()/4));
        score.setY(50);
        score.setFont(new Font("Times New Roman", 40));
        score.setFill(Color.BLACK);
        score.setVisible(true);
        pane.getChildren().add(score);
        pane.getChildren().add(gameOver);

        Text lives = new Text();
        lives.setX(_scene.getWidth() - (_scene.getWidth()/4));
        lives.setY(50);
        lives.setFont(new Font("Times New Roman", 40));
        lives.setFill(Color.BLACK);
        lives.setVisible(true);
        pane.getChildren().add(lives);


        Image kursor = new Image("kursor.png");
        ImageCursor imgkur = new ImageCursor(kursor);
        _scene.setCursor(imgkur);


        stage.setResizable(false);
        stage.setScene(_scene);
        stage.show();

        Runnable neww = () ->
        {
        try {
            while (!over) {
                Thread.sleep(10);
                Platform.runLater(() ->
                {
                    updateEnemy();
                    updateBlocker();
                    score.setText("Score: " + Player.GetScore());
                    lives.setText("Lives: " + Player.GetLives());
                    if(Player.GetLives() <= 0 && !isOver())
                    {
                        GameOver();
                    }
                });
            }
        } catch (InterruptedException ex) {
            System.out.println("Interrupted");
        }
        };

        th = new Thread(neww);
        th.start();
    }

    private boolean isOver()
    {
        return over;
    }

    private void GameOver()
    {
        gameOver.setX(_scene.getWidth()/3);
        gameOver.setY(_scene.getHeight()/3);
        gameOver.setFont(new Font("Times New Roman", 60));
        gameOver.setText(" GAME OVER!\nClick to continue.");
        gameOver.setFill(Color.BLACK);
        gameOver.setVisible(true);
        over = true;
        gameOver.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                //ScoresMenu();
                System.out.println("Score menu");
            }
        });
    }


    private void updateBlocker()
    {
        for (Obstacle o: Obstacle.obstacleList)
        {
            o.GetView().setCenterY(o.GetView().getCenterY()+1);
            int rand =((int) (Math.random()*10));
            int changer;
            if(rand > 5)
                changer = 2;
            else
                changer = -2;
            //c.setCenterX(c.getCenterX()+changer);
            if(o.GetView().getCenterY()-o.GetView().getRadius() > _scene.getHeight())
                o.GetView().setCenterY((int)(Math.random()*(-_scene.getHeight())));
        }
    }

    private void updateEnemy()
    {
        for (Enemy e: Enemy.enemyList)
        {
            e.GetView().setX(e.GetView().getX()+1);
            e.GetView().setRotate(e.GetView().getRotate() + 1);

            if (e.GetView().getX() > _scene.getWidth() && !e.CheckDead())
            {
                Player.LoseLife();
                e.GetView().setX((int) (Math.random()*-300));
                e.GetView().setY((int) (Math.random()*1200));
            }
        }
    }

    public static void main(String[] args)
    {
        //launch(args);
        launch();
    }
}
