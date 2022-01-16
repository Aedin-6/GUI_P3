package GameModel.Assets;

import javafx.animation.PathTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.util.Duration;


import java.io.FileInputStream;
import java.util.ArrayList;

public class Enemy
{
    public static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    private String id = "e";
    private int diff;
    private int lives;
    private Rectangle view;
    private static int counter;
    private PathTransition pathTransition;
    private int height;
    private int width;

    public Enemy(int diff, Pane stage, int y, int x)
    {
        id = id + counter;
        GetColorDiff(diff);
        view = CreateEnemyView();
        pathTransition = GeneratePath(stage);
        this.height = y;
        this.width = x;

    }

    private PathTransition GeneratePath(Pane stage)
    {
        PathTransition pathTransition = new PathTransition(Duration.seconds(10), (Shape) GetView());
        Path path = new Path();
        path.getElements().add(new MoveTo(stage.getWidth()/3, 0));
        path.getElements().add(new LineTo(stage.getWidth(), (Math.random() * stage.getHeight())));
        path.getElements().add(new MoveTo((stage.getWidth()/3)*2, 0));
        path.getElements().add(new LineTo(stage.getWidth(), (Math.random() * stage.getHeight())));
        path.getElements().add(new MoveTo(stage.getWidth(), 0));
        path.getElements().add(new LineTo(stage.getWidth(), (Math.random() * stage.getHeight())));
        pathTransition.setNode(GetView());
        pathTransition.setPath(path);
        pathTransition.play();

        return pathTransition;
    }

    private Rectangle CreateEnemyView()
    {

        Image bird = null;
        try
        {
            FileInputStream inputstream = new FileInputStream("bird2.png");
            bird = new Image(inputstream);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
                ImagePattern pattern = new ImagePattern(bird, 20, 20, 40, 40, false);


        Rectangle rectangle = new Rectangle(5,5);
        rectangle.setWidth(40);
        rectangle.setHeight(40);
        rectangle.setFill(pattern);
        rectangle.setStroke(Color.DARKGREEN);
        rectangle.setStrokeWidth(5);
        rectangle.setArcHeight(30);
        rectangle.setArcWidth(30);



        rectangle.setFill(Color.RED);

        rectangle.setOnMouseClicked(mouseEvent ->
        {
            //rectangle.setFill(Color.rgb((int) (Math.random() * 255),(int) (Math.random() * 255),(int) (Math.random() * 255)));
            Cliecked();
            if(CheckDestroy())
                Destroy();
        });
        return rectangle;
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


    public Node GetView()
    {
        return view;
    }

    public void Cliecked()
    {
        lives= lives -1;
    }
     public boolean CheckDestroy()
     {
         return lives <= 0;
     }

    public void Destroy()
    {
        /*
        Player.IncreaseScore((Color) view.getFill());
        this.view.setVisible(false);
        this.view.setHeight(1);
        this.view.setWidth(1);

         */
    }
}
