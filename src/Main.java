import View.GameScene;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.management.monitor.MonitorSettingException;
import java.io.FileNotFoundException;

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
        Text startBtn = new Text("Start New Game");
        startBtn.setFont(Font.font("Impact",FontWeight.BOLD, FontPosture.REGULAR, 34));
        Text scoreBtn = new Text("High Scores");
        scoreBtn.setFont(Font.font("Impact",FontWeight.BOLD, FontPosture.REGULAR, 34));
        Text exitBtn = new Text("Exit");
        exitBtn.setFont(Font.font("Impact",FontWeight.BOLD, FontPosture.REGULAR, 34));


        //scoreBtn.setStyle("-fx-pref-width: 130px");
        //startBtn.setStyle("-fx-pref-width: 130px");
        //exitBtn.setStyle("-fx-pref-width: 130px");



        VBox root = new VBox(20,startBtn, scoreBtn, exitBtn);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: transparent");

        menuScene = new Scene(root, 400, 400, Color.rgb(148,197,33));


        startBtn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                stage.setScene(diffChoice);
                //StageSetter(stage);

            }
        });
        startBtn.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                startBtn.setFill(Color.RED);
            }
        });
        startBtn.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                startBtn.setFill(Color.BLACK);
            }
        });
        scoreBtn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                StageSetter(stage);
                stage.setScene(scoreScene);
            }
        });
        scoreBtn.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                scoreBtn.setFill(Color.RED);
            }
        });
        scoreBtn.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                scoreBtn.setFill(Color.BLACK);
            }
        });
        exitBtn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                System.exit(0);
            }
        });
        exitBtn.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                exitBtn.setFill(Color.RED);
            }
        });
        exitBtn.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                exitBtn.setFill(Color.BLACK);
            }
        });

        //Difficulty Choice Menu

        Text easy = new Text("Easy");
        easy.setFont(Font.font("Impact",FontWeight.BOLD, FontPosture.REGULAR, 24));
        Text medium = new Text("Medium");
        medium.setFont(Font.font("Impact",FontWeight.BOLD, FontPosture.REGULAR, 24));
        Text hard = new Text("Hard");
        hard.setFont(Font.font("Impact",FontWeight.BOLD, FontPosture.REGULAR, 24));


        VBox root2 = new VBox(20, easy, medium, hard);
        root2.setAlignment(Pos.CENTER);
        root2.setStyle("-fx-background-color: transparent");
        diffChoice = new Scene(root2, 200, 200, Color.rgb(43,120,33));



        easy.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                difficulty = 0;
                StackPane gameRoot = new StackPane();
                StageSetter(stage);
                GameScene gameScene = new GameScene(gameRoot, 1280, 656, difficulty);
                stage.setX(250);
                stage.setY(150);
                stage.setScene(gameScene);
            }
        });
        easy.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                easy.setFill(Color.RED);
            }
        });
        easy.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                easy.setFill(Color.BLACK);
            }
        });
        medium.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                difficulty = 1;
                StackPane gameRoot = new StackPane();
                StageSetter(stage);
                stage.setX(250);
                stage.setY(150);
                GameScene gameScene = new GameScene(gameRoot, 1280, 656, difficulty);
                stage.setScene(gameScene);
            }
        });
        medium.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                medium.setFill(Color.RED);
            }
        });
        medium.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                medium.setFill(Color.BLACK);
            }
        });
        hard.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                difficulty = 2;
                StackPane gameRoot = new StackPane();
                StageSetter(stage);
                stage.setX(250);
                stage.setY(150);
                GameScene gameScene = new GameScene(gameRoot, 1280, 656, difficulty);
                stage.setScene(gameScene);
            }
        });
        hard.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                hard.setFill(Color.RED);
            }
        });
        hard.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                hard.setFill(Color.BLACK);
            }
        });


        StageSetter(stage);
        stage.setScene(menuScene);
        stage.show();

    }

    private void StartTrans(Text text)
    {
        FadeTransition ft = new FadeTransition(Duration.millis(2000), text);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();
    }


    private void StageSetter(Stage stage)
    {
        Image icon = new Image("duck.jpg");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        //stage.setFullScreen(true);
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
