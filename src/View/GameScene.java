package View;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameScene extends Scene
{
    private Scene gameScene;
    private Text start;
    public GameScene(StackPane gameRoot, int w , int h)
    {
        super(gameRoot, w, h);
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
            gameRoot.setBackground(bGround);

            start = new Text("GO!");
            start.setFont(Font.font("Arial", 72));
            start.setFill(Color.RED);
            start.setVisible(true);
            gameRoot.getChildren().add(start);
            start.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent mouseEvent)
                {
                    System.out.println("Clicked!");
                }
            });
        }
        catch (FileNotFoundException e){};
    }

    public Text GetText()
    {
        return start;
    }
}
