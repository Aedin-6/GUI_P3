import GameModel.Assets.Enemy;
import GameModel.Assets.Obstacle;
import GameModel.Assets.Player;
import View.GameScene;
import View.ScoresScene;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Main extends Application
{
    Scene menuScene;
    Scene diffChoice;
    Scene gameScene;
    Scene scoreScene;
    public static Stage stage;
    int difficulty;
    boolean over;
    ArrayList<Enemy> enemyMenuList = new ArrayList<>();
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

        VBox root = new VBox(20,startBtn, scoreBtn, exitBtn);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: transparent");
        root.setTranslateX(100);
        root.setTranslateY(100);





        startBtn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                stage.setScene(diffChoice);
                over = true;
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
                Group scorePane = new Group();
                ScoresScene scoresScene = new ScoresScene(scorePane, 300,500, false);
                over = true;
                StageSetter(stage);
                stage.setScene(scoresScene);
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
        easy.setFont(Font.font("Impact",FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text medium = new Text("Medium");
        medium.setFont(Font.font("Impact",FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text hard = new Text("Hard");
        hard.setFont(Font.font("Impact",FontWeight.BOLD, FontPosture.REGULAR, 30));


        VBox root2 = new VBox(20, easy, medium, hard);
        root2.setAlignment(Pos.CENTER);
        diffChoice = new Scene(root2, 300, 300, Color.GREENYELLOW);



        easy.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                difficulty = 0;
                Pane gameRoot = new Pane();
                StageSetter(stage);
                GameScene gameScene = new GameScene(gameRoot, 1200, 600, difficulty, stage, menuScene);
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
                Pane gameRoot = new Pane();
                StageSetter(stage);
                GameScene gameScene = new GameScene(gameRoot, 1200, 600, difficulty, stage, menuScene);
                stage.setX(250);
                stage.setY(150);
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
                Pane gameRoot = new Pane();
                StageSetter(stage);
                GameScene gameScene = new GameScene(gameRoot, 1200, 600, difficulty, stage, menuScene);
                stage.setX(250);
                stage.setY(150);
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


        stage.setOnCloseRequest(t ->
        {
            Platform.exit();
            System.exit(0);
        });

        Pane pane = new Pane();
        pane.toBack();
        pane.getChildren().add(root);
        menuScene = new Scene(pane, 400, 400, Color.rgb(148,197,33));

        for (int i = 0; i <30; i++)
        {
            Enemy _enemy = new Enemy(true);
            pane.getChildren().add(_enemy.GetView());
            enemyMenuList.add(_enemy);
        }

        Runnable neww = () ->
        {
            try {
                while (!over) {
                    Thread.sleep(20);
                    Platform.runLater(() ->
                    {
                        updateMenu();
                    });
                }
            } catch (InterruptedException ex) {
                System.out.println("Interrupted");
            }
        };

        Thread _thread = new Thread(neww);
        _thread.start();



        StageSetter(stage);
        stage.setScene(menuScene);
        stage.show();

    }

    private void updateMenu()
    {
        for (Enemy e: enemyMenuList)
        {
            e.GetView().setY(e.GetView().getY()+1);
            e.GetView().setOpacity(0.5);
            if(e.GetView().getY()-e.GetView().getHeight() > menuScene.getHeight())
                e.GetView().setY((int)(Math.random()*(-menuScene.getHeight())));
        }
    }



    private void StageSetter(Stage stage)
    {
        Image icon = new Image("duck.jpg");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setTitle("Duck Hunter!");

    }

    public static void main(String[] args)
    {
        //launch(args);
        launch();
    }
}
