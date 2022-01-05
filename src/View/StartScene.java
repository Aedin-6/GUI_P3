package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class StartScene extends Scene
{
    Scene startScene;

    public StartScene(Parent parent, double v, double v1)
    {
        super(parent, v, v1);
        StackPane root = new StackPane();;
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        root.getChildren().add(btn);
    }


    public Scene GetScene()
    {
        return startScene;
    }
}

