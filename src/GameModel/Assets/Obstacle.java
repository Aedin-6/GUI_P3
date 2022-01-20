package GameModel.Assets;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.ArrayList;

public class Obstacle
{
    public static ArrayList<Obstacle> obstacleList = new ArrayList<>();
    private Circle view;
    private String id = "o";
    private static int counter;

    public Obstacle()
    {
        counter++;
        id = id + counter;
        view = AddView();
    }
    public Obstacle(boolean menu)
    {
        id = id + counter;
        view = AddDiffMenuView();
    }

    private Circle AddView()
    {
        Circle circle = new Circle();
        circle.setCenterY((int)(Math.random()*(1000)));
        circle.setCenterX((int)(Math.random()*1200));
        circle.setRadius(30);
        circle.setFill(Color.rgb(204,0,0));
        circle.setStroke(Color.ORANGERED);
        circle.setStrokeWidth(10);
        circle.setOpacity(0.8);
        circle.toFront();

        return circle;
    }
    private Circle AddDiffMenuView()
    {
        Circle circle = new Circle();
        circle.setCenterY((int)(Math.random()*(300)));
        circle.setCenterX((int)(Math.random()*600));
        circle.setRadius(10);
        circle.setFill(Color.rgb((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
        circle.setStroke(Color.ORANGERED);
        circle.setStrokeWidth(2);
        circle.toFront();
        circle.setMouseTransparent(true);

        return circle;
    }

    public Circle GetView()
    {
        return view;
    }
}
