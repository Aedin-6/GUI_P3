package GameModel.Assets;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Obstacle
{
    public static ArrayList<Obstacle> obstacleList = new ArrayList<>();
    private Circle view;

    public Obstacle()
    {
        view = AddView();
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

    public Circle GetView()
    {
        return view;
    }
}
