package GameModel;

import GameModel.Assets.Enemy;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

import java.time.LocalTime;

import static java.lang.Thread.sleep;

public class EnemiesHandler implements Runnable
{
    StackPane stage;
    int diff;
    public static LocalTime time = LocalTime.ofSecondOfDay(1);

    EnemiesHandler(int diff, StackPane stage)
    {
        this.diff = diff;
        this.stage = stage;
    }

    @Override
    public void run()
    {
        Platform.runLater(() -> {
            try
            {
                GenerateEnemies(diff, stage);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        });
    }


    void GenerateEnemies(int diff, StackPane stage) throws InterruptedException
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
                        pos = Pos.TOP_RIGHT;

                  //  Enemy.enemyList.add(new Enemy(diff, stage, 0, (int)(Math.random() * stage.getHeight())));

                    sleep(5000);
                    StackPane.setAlignment(Enemy.enemyList.get(i).GetView(), pos);
                    stage.getChildren().add(Enemy.enemyList.get(i).GetView());
                }
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
                        pos = Pos.TOP_RIGHT;

                   // Enemy.enemyList.add(new Enemy(diff, stage, 0, (int)(Math.random() * stage.getHeight())));
                    sleep(5000);
                    StackPane.setAlignment(Enemy.enemyList.get(i).GetView(), pos);
                    stage.getChildren().add(Enemy.enemyList.get(i).GetView());
                }
            }
            case 2:
            {
                Pos pos;
                int randomPos = (int) (Math.random() * 6);

                if(randomPos < 2)
                    pos = Pos.CENTER_LEFT;
                else if (randomPos > 4)
                    pos = Pos.BOTTOM_LEFT;
                else
                    pos = Pos.TOP_RIGHT;

                for(int i = 0; i < 40; i++)
                {
                  //  Enemy.enemyList.add(new Enemy(diff, stage, 0, (int)(Math.random() * stage.getHeight())));

                    sleep(5000);
                    StackPane.setAlignment(Enemy.enemyList.get(i).GetView(), pos);
                    stage.getChildren().add(Enemy.enemyList.get(i).GetView());
                }
            }
        }
    }
}
