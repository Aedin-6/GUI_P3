package GameModel;

import GameModel.Assets.Enemy;
import GameModel.Assets.Obstacle;
import GameModel.Assets.Player;
import View.ScoresScene;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GameFlow implements Runnable
{
    Text score;
    Text lives;
    static boolean over;
    Text gameOver;
    Scene scene;
    Scene menuScene;
    Stage stage;
    int difficulty;

    public GameFlow(Text score, Text lives, Text gameOver, Scene scene, Scene menuScene, Stage stage, int difficulty)
    {
        this.score = score;
        this.lives = lives;
        over = false;
        this.gameOver = gameOver;
        this.scene = scene;
        this.stage = stage;
        this.difficulty = difficulty;
        this.menuScene = menuScene;
        SetInterKey();
    }

    @Override
    public void run()
    {
        try
        {
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
        }
        catch (InterruptedException ex)
        {
            System.out.println("Interrupted");
        }
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
        gameOver.setX(scene.getWidth()/3);
        gameOver.setY(scene.getHeight()/3);
        gameOver.setFont(new Font("Times New Roman", 60));
        gameOver.setText(" GAME OVER!\nClick to continue.");
        gameOver.setFill(Color.BLACK);
        gameOver.setVisible(true);
        over = true;
        gameOver.setOnMouseClicked(mouseEvent ->
        {
            Group scorePane = new Group();
            ScoresScene scoresScene = new ScoresScene(scorePane, 325,500, true);
            Image icon = new Image("file:duck.png");
            stage.getIcons().add(icon);
            stage.setResizable(false);
            stage.setTitle("Duck Hunter!");
            stage.setScene(scoresScene);
        });
    }


    private void updateBlocker()
    {
        for (Obstacle o: Obstacle.obstacleList)
        {
            o.GetImg().setY(o.GetImg().getY()+1);
            if(o.GetImg().getY()-o.GetImg().getFitHeight() > scene.getHeight())
                o.GetImg().setY((int)(Math.random()*(-scene.getHeight())));
        }
    }

    private void updateEnemy()
    {

        for (Enemy e: Enemy.enemyList)
        {
            if(e.GetSide() == 0)
            {
                e.GetImg().setX(e.GetImg().getX() + (difficulty/3.0) + 1);

                if (e.GetImg().getX() > scene.getWidth() && !e.CheckDead())
                {
                    Player.LoseLife();
                    e.GetImg().setX((int) (Math.random() * -600));
                    e.GetImg().setY((int) (Math.random() * 560));
                }
            }
            else
            {
                e.GetImg().setX(e.GetImg().getX() - (difficulty/3.0) - 1);

                if (e.GetImg().getX() < -25 && !e.CheckDead())
                {
                    Player.LoseLife();
                    e.GetImg().setX((int) (Math.random() * (1800 - 1200) + 1200));
                    e.GetImg().setY((int) (Math.random() * 590));
                }
            }
        }
    }
    KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN,
            KeyCombination.SHIFT_ANY);

    public void SetInterKey()
    {
        scene.setOnKeyPressed(keyEvent ->
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
    }
}
