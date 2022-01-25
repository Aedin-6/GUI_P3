package GameModel.Assets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Obstacle
{
    public static ArrayList<Obstacle> obstacleList = new ArrayList<>();
    private ImageView img;
    private String id = "o";
    private static int counter;

    public Obstacle()
    {
        counter++;
        id = id + counter;
        img = AddImg();
    }
    public Obstacle(boolean menu)
    {
        id = id + counter;
        img = AddMenuImg();
    }


    private ImageView AddMenuImg()
    {
        ImageView obstacle = new ImageView();
        obstacle.setImage(new Image("file:cloud-icon.png"));
        obstacle.setFitHeight(75);
        obstacle.setFitWidth(75);
        obstacle.toFront();
        obstacle.setMouseTransparent(true);
        obstacle.setY((int)(Math.random()*(300)));
        obstacle.setX((int)(Math.random()*(-600)));
        obstacle.toFront();
        obstacle.setMouseTransparent(true);

        return obstacle;
    }

    private ImageView AddImg()
    {
        ImageView obstacle = new ImageView();
        obstacle.setImage(new Image("file:cloud-icon.png"));
        obstacle.setFitHeight(75);
        obstacle.setFitWidth(75);
        obstacle.toFront();
        obstacle.setMouseTransparent(true);
        obstacle.setY((int)(Math.random()*(1000)));
        obstacle.setX((int)(Math.random()*1200));
        obstacle.setOpacity(0.8);
        obstacle.toFront();
        obstacle.setMouseTransparent(true);

        return obstacle;
    }

    public ImageView GetImg() { return img;}
}
