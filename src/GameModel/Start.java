package GameModel;


import GameModel.Assets.Enemy;
import com.sun.tools.javac.Main;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import javafx.stage.Stage;
import javafx.util.Duration;

import static java.lang.Thread.sleep;

public class Start
{
    public void startGame(int diff, Pane stage)
    {
        TimeFlow timeFlow = new TimeFlow();
        Thread thread = new Thread(timeFlow);
        thread.start();
        /*
        EnemiesHandler enemiesHandler = new EnemiesHandler(diff, stage);

        Thread enemThread = new Thread(enemiesHandler);
        enemThread.start();
         */
        try
        {
            GenerateEnemies(diff, stage);
        }
        catch (InterruptedException ex) {};
/*
        StackPane.setAlignment(Enemy.enemyList.get(0).GetView(), Pos.CENTER_LEFT);
        stage.getChildren().add(Enemy.enemyList.get(0).GetView());

 */


    }

    void GenerateEnemies(int diff, Pane stage) throws InterruptedException
    {
        switch(diff)
        {
            case 0:
            {
                for(int i = 0; i < 20; i++)
                {
                    Pos pos;
                    int randomPos = (int) (Math.random() * 6);

                    if(randomPos < 2)
                        pos = Pos.CENTER_LEFT;
                    else if (randomPos > 4)
                        pos = Pos.BOTTOM_LEFT;
                    else
                        pos = Pos.TOP_LEFT;

                    Enemy.enemyList.add(new Enemy(diff, stage,(int)(Math.random() * stage.getHeight()), 0));
                    //sleep(5000);
                    //StackPane.setAlignment(Enemy.enemyList.get(i).GetView(), pos);
                    stage.getChildren().add(Enemy.enemyList.get(i).GetView());
                    Enemy.enemyList.get(i).GetView().relocate(0,(int)(Math.random() * stage.getHeight()));
                }
                break;
            }
            case 1:
            {
                for(int i = 0; i < 30; i++)
                {
                    Pos pos;
                    int randomPos = (int) (Math.random() * 6);

                    if(randomPos < 2)
                        pos = Pos.CENTER_LEFT;
                    else if (randomPos > 4)
                        pos = Pos.BOTTOM_LEFT;
                    else
                        pos = Pos.TOP_LEFT;

                    Enemy.enemyList.add(new Enemy(diff, stage, 0, (int)(Math.random() * stage.getHeight())));
                    //sleep(5000);
                    //StackPane.setAlignment(Enemy.enemyList.get(i).GetView(), pos);
                    stage.getChildren().add(Enemy.enemyList.get(i).GetView());
                }
                break;
            }
            case 2:
            {
                for(int i = 0; i < 40; i++)
                {
                    Pos pos;
                    int randomPos = (int) (Math.random() * 6);

                    if(randomPos < 2)
                        pos = Pos.TOP_LEFT;
                    else if (randomPos > 4)
                        pos = Pos.BOTTOM_LEFT;
                    else
                        pos = Pos.TOP_LEFT;

                    Enemy.enemyList.add(new Enemy(diff, stage, 0, (int)(Math.random() * stage.getHeight())));

                    stage.getChildren().add(Enemy.enemyList.get(i).GetView());
                }
                break;
            }
        }
    }
}
