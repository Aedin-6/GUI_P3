package View;

import GameModel.Assets.Enemy;
import GameModel.Assets.Obstacle;
import GameModel.Assets.Player;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class GameScene extends Scene
{
    //private Scene _scene;
    private Text start;
    int difficulty;
    Pane pane;
    Thread th;
    Boolean over;
    Text gameOver = new Text();
    Stage stage;


    public GameScene(Pane pane, int w, int h, int difficulty, Stage stage, Scene menuScene)
    {
        super(pane, w, h);
        this.pane = pane;
        this.stage = stage;
        this.difficulty = difficulty;
        over = false;
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

        for (int i = 0; i < 10+(difficulty*5); i++)
        {
            Enemy _enemy = new Enemy((int)(Math.random()*3));
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
        score.setX(0 + (this.getWidth()/4));
        score.setY(50);
        score.setFont(new Font("Times New Roman", 40));
        score.setFill(Color.BLACK);
        score.setVisible(true);
        score.setMouseTransparent(true);
        pane.getChildren().add(score);
        pane.getChildren().add(gameOver);

        Text lives = new Text();
        lives.setX(this.getWidth() - (this.getWidth()/4));
        lives.setY(50);
        lives.setFont(new Font("Times New Roman", 40));
        lives.setFill(Color.BLACK);
        lives.setVisible(true);
        lives.setMouseTransparent(true);
        pane.getChildren().add(lives);

        final KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN,
                KeyCombination.SHIFT_ANY);
        this.setOnKeyPressed(keyEvent ->
        {
            if (keyComb1.match(keyEvent))
            {
                over = true;
                Enemy.enemyList.clear();
                Obstacle.obstacleList.clear();
                Player.ClearScore();
                Player.ClearLives();
                stage.setScene(menuScene);
            }
        });


        Image kursor = new Image("kursor.png");
        ImageCursor cursor = new ImageCursor(kursor, kursor.getWidth()/2, kursor.getHeight()/2);
        this.setCursor(cursor);

        Runnable neww = () ->
        {
            try {
                while (!over) {
                    Thread.sleep(20);
                    Platform.runLater(() ->
                    {
                        updateEnemy();
                        updateBlocker();
                        score.setText("Score: " + Player.GetScore());
                        lives.setText("Lives: " + Player.GetLives());
                        if(Player.GetLives() <= 0 && !isOver() || ifAllKilled())
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

    private boolean ifAllKilled()
    {
        boolean allDead = false;
        int counter=0;
        for (Enemy en: Enemy.enemyList)
        {
            if(en.CheckDead())
                counter++;
        }
        if (counter == Enemy.enemyList.size())
            allDead = true;
        return allDead;
    }

    private boolean isOver()
    {
        return over;
    }

    private void GameOver()
    {
        gameOver.setX(this.getWidth()/3);
        gameOver.setY(this.getHeight()/3);
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
                Pane scorePane = new Pane();
                ScoresScene scoresScene = new ScoresScene(scorePane, 300,300);
                Image icon = new Image("duck.jpg");
                stage.getIcons().add(icon);
                stage.setResizable(false);
                stage.setTitle("Duck Hunter!");
                stage.setScene(scoresScene);
            }
        });
    }


    private void updateBlocker()
    {
        for (Obstacle o: Obstacle.obstacleList)
        {
            o.GetView().setCenterY(o.GetView().getCenterY()+1);
            if(o.GetView().getCenterY()-o.GetView().getRadius() > this.getHeight())
                o.GetView().setCenterY((int)(Math.random()*(-this.getHeight())));
        }
    }

    private void updateEnemy()
    {
        for (Enemy e: Enemy.enemyList)
        {
            e.GetView().setX(e.GetView().getX()+difficulty+1);
            e.GetView().setRotate(e.GetView().getRotate() + 1);

            if (e.GetView().getX() > this.getWidth() && !e.CheckDead())
            {
                Player.LoseLife();
                e.GetView().setX((int) (Math.random()*-300));
                e.GetView().setY((int) (Math.random()*1200));
            }
        }
    }
}
