import View.StartScene;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application
{
    boolean option = true;
    @Override
    public void start(Stage stage) throws Exception
    {

        Group root = new Group();
        Group root2 = new Group();
        StartScene newscene = new StartScene(root2,100, 150);
        Scene _scene = new Scene(root, 400, 400, Color.rgb(148,197,33));
        //Scene other = new Scene(root2, 200, 200, Color.rgb(148,0,33));
        Button startBtn = new Button("Start");
        Button startBtn2 = new Button("Second Start Start Start");
        root.getChildren().add(startBtn);
        //root2.getChildren().add(startBtn2);
        startBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                stage.setScene(newscene);
            }
        });
        startBtn2.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                stage.setScene(_scene);
            }
        });


        //Image title = new Image("title.png");
        //ImageView _tit = new ImageView(title);
        //root.getChildren().add(_tit);
        Image icon = new Image("duck.jpg");
        stage.getIcons().add(icon);
        stage.setResizable(true);
        //stage.setFullScreen(true);
        //stage.setFullScreenExitHint("Press Q to exit full screen");
        //stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));
        stage.setTitle("Duck Hunter!");
        stage.setScene(_scene);
        stage.show();

    }

    public static void main(String[] args)
    {
        //launch(args);
        launch();
    }

}
