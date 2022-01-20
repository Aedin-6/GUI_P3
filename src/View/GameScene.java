package View;

import GameModel.Assets.Enemy;
import GameModel.Assets.Obstacle;
import GameModel.GameFlow;
import GameModel.TimeFlow;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class GameScene extends Scene
{
    int difficulty;
    Pane pane;
    Text gameOver = new Text();
    Stage stage;
    public static MediaPlayer shotPlayer;


    public GameScene(Pane pane, int w, int h, int difficulty, Stage stage, Scene menuScene)
    {
        super(pane, w, h);
        this.pane = pane;
        this.stage = stage;
        this.difficulty = difficulty;


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
        catch (Exception ignored){}

        GenerateEnemies();
        GenerateObstacles();


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

        Text time = new Text();
        time.setX(0);
        time.setY(50);
        time.setFont(new Font("Times New Roman", 40));
        time.setFill(Color.BLACK);
        time.setVisible(true);
        time.setMouseTransparent(true);
        pane.getChildren().add(time);


        Image kursor = new Image("kursor.png");
        ImageCursor cursor = new ImageCursor(kursor, kursor.getWidth()/2, kursor.getHeight()/2);
        this.setCursor(cursor);

        GameFlow gameFlow = new GameFlow(score,lives,gameOver,this, menuScene,stage,difficulty);
        Thread gfThread = new Thread(gameFlow);
        gfThread.start();

        TimeFlow timeFlow = new TimeFlow(time);
        Thread tfThread = new Thread(timeFlow);
        tfThread.start();

        Media shotMusic = new Media(getClass().getResource("/shot.mp3").toExternalForm());
        this.setOnMouseClicked(mouseEvent ->
        {
            shotPlayer = new MediaPlayer(shotMusic);
            shotPlayer.setVolume(0.4);
            shotPlayer.setAutoPlay(true);
        });
    }
    private void GenerateEnemies()
    {
        for (int i = 0; i < 10+(difficulty*10); i++)
        {
            Enemy _enemy = new Enemy((int)(Math.random()*3));
            pane.getChildren().add(_enemy.GetView());
            Enemy.enemyList.add(_enemy);
        }
    }
    private void GenerateObstacles()
    {
        for (int i = 0 ; i<100; i++)
        {
            Obstacle _obstacle = new Obstacle();
            pane.getChildren().add(_obstacle.GetView());
            Obstacle.obstacleList.add(_obstacle);
        }
    }
}
