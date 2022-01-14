package GameModel;


import GameModel.Assets.Enemy;
import com.sun.tools.javac.Main;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import javafx.stage.Stage;
import javafx.util.Duration;

public class Start
{
    public void startGame(int diff, StackPane stage)
    {
        Enemy.enemyList.add(CreateEnemy(diff));

        StackPane.setAlignment(Enemy.enemyList.get(0).GetView(), Pos.CENTER_LEFT);
        stage.getChildren().add(Enemy.enemyList.get(0).GetView());

        PathTransition pathTransition = new PathTransition(Duration.seconds(5), (Shape) Enemy.enemyList.get(0).GetView());

        Path path = new Path();
        path.getElements().add(new MoveTo(20, 0));
        path.getElements().add(new LineTo(stage.getWidth(), 0));

        pathTransition.setNode(Enemy.enemyList.get(0).GetView());
        pathTransition.setPath(path);

        pathTransition.setCycleCount(Animation.INDEFINITE);
        //pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setAutoReverse(true);
        pathTransition.play();
    }

    private Enemy CreateEnemy(int diff)
    {
        Enemy _enemy = new Enemy(diff);
        Rectangle rectangle = new Rectangle(5,5);
        rectangle.setX(0);
        rectangle.setY(0);
        rectangle.setWidth(20);
        rectangle.setHeight(20);
        rectangle.setFill(Color.GREEN);
        rectangle.setStroke(Color.DARKGREEN);
        rectangle.setStrokeWidth(5);
        rectangle.setArcHeight(30);
        rectangle.setArcWidth(30);
        _enemy.AddView(rectangle);

        return _enemy;
    }

}
