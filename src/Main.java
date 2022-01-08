import View.StartScene;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application
{
    Scene menuScene;
    Scene diffChoice;
    Scene gameScene;
    Scene scoreScene;
    int difficulty;
    @Override
    public void start(Stage stage) throws Exception
    {
        //Menu Scene
        Group root = new Group();
        menuScene = new Scene(root, 400, 400, Color.rgb(148,197,33));
        Button startBtn = new Button("Start New Game");
        Button scoreBtn = new Button("High Scores");
        Button exitBtn = new Button("Exit");
        startBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                stage.setScene(diffChoice);
                //StageSetter(stage);

            }
        });
        scoreBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                StageSetter(stage);
                stage.setScene(scoreScene);
            }
        });
        exitBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                System.exit(0);
            }
        });
        root.getChildren().add(startBtn);
        root.getChildren().add(scoreBtn);
        root.getChildren().add(exitBtn);

        //Difficulty Choice Menu
        Group root2 = new Group();
        diffChoice = new Scene(root2, 400, 400, Color.rgb(0,0,33));
        Button easy = new Button("Easy");
        Button medium = new Button("Medium");
        Button hard = new Button("Hard");

        easy.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                difficulty = 0;
                StageSetter(stage);
                stage.setScene(gameScene);
            }
        });
        medium.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                difficulty = 1;
                StageSetter(stage);
                stage.setScene(gameScene);
            }
        });
        hard.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                difficulty = 2;
                StageSetter(stage);
                stage.setScene(gameScene);
            }
        });
        root2.getChildren().add(easy);
        root2.getChildren().add(medium);
        root2.getChildren().add(hard);

        StageSetter(stage);
        stage.setScene(menuScene);
        stage.show();

    }

    private void StageSetter(Stage stage)
    {
        Image icon = new Image("duck.jpg");
        stage.getIcons().add(icon);
        stage.setResizable(true);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("Press Q to exit full screen");
        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));
        stage.setTitle("Duck Hunter!");
    }

    public static void main(String[] args)
    {
        //launch(args);
        launch();
    }

}
