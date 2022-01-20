import GameModel.Assets.Enemy;
import GameModel.Assets.Obstacle;
import GameModel.Assets.Player;
import View.GameScene;
import View.ScoresScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Main extends Application
{
    Scene menuScene;
    Scene diffChoice;
    Pane pane;
    Pane diffPane;
    ImageView imageView;
    int difficulty;
    boolean over;
    boolean soundOn;
    Image soundImage;
    MediaPlayer bgMusicPlayer;
    ArrayList<Enemy> enemyMenuList = new ArrayList<>();
    ArrayList<Obstacle> menuBlockerList = new ArrayList<>();
    @Override
    public void start(Stage stage)
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

       imageView = VolumeIcon();



        root.setTranslateX(100);
        root.setTranslateY(100);


        startBtn.setOnMouseClicked(mouseEvent -> stage.setScene(diffChoice));
        startBtn.setOnMouseEntered(mouseEvent -> startBtn.setFill(Color.RED));
        startBtn.setOnMouseExited(mouseEvent -> startBtn.setFill(Color.BLACK));
        scoreBtn.setOnMouseClicked(mouseEvent ->
        {
            Group scorePane = new Group();
            ScoresScene scoresScene = new ScoresScene(scorePane, 325,500, false);
            over = true;
            StageSetter(stage);
            stage.setScene(scoresScene);
        });
        scoreBtn.setOnMouseEntered(mouseEvent -> scoreBtn.setFill(Color.RED));
        scoreBtn.setOnMouseExited(mouseEvent -> scoreBtn.setFill(Color.BLACK));
        exitBtn.setOnMouseClicked(mouseEvent -> System.exit(0));
        exitBtn.setOnMouseEntered(mouseEvent -> exitBtn.setFill(Color.RED));
        exitBtn.setOnMouseExited(mouseEvent -> exitBtn.setFill(Color.BLACK));

        //Difficulty Choice Menu

        Text easy = new Text("Easy");
        easy.setFont(Font.font("Impact",FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text medium = new Text("Medium");
        medium.setFont(Font.font("Impact",FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text hard = new Text("Hard");
        hard.setFont(Font.font("Impact",FontWeight.BOLD, FontPosture.REGULAR, 30));
        VBox root2 = new VBox(20, easy, medium, hard);
        root2.setAlignment(Pos.CENTER);
        root2.setLayoutX(100);
        root2.setLayoutY(70);
        diffPane = new Pane(imageView);
        diffPane.getChildren().add(root2);
        diffChoice = new Scene(diffPane, 300, 300);
        diffChoice.setFill(Color.GREEN);
        GenerateDiffChoiceEnem();
        DiffMenuThread();


        easy.setOnMouseClicked(mouseEvent ->
        {
            difficulty = 0;
            new Player(difficulty);
            over = true;
            Pane gameRoot = new Pane(imageView);
            StageSetter(stage);
            GameScene gameScene = new GameScene(gameRoot, 1200, 600, difficulty, stage, menuScene);
            stage.setX(250);
            stage.setY(150);
            stage.setScene(gameScene);
        });
        easy.setOnMouseEntered(mouseEvent -> easy.setFill(Color.RED));
        easy.setOnMouseExited(mouseEvent -> easy.setFill(Color.BLACK));
        medium.setOnMouseClicked(mouseEvent ->
        {
            difficulty = 1;
            new Player(difficulty);
            over = true;
            Pane gameRoot = new Pane(imageView);
            StageSetter(stage);
            GameScene gameScene = new GameScene(gameRoot, 1200, 600, difficulty, stage, menuScene);
            stage.setX(250);
            stage.setY(150);
            stage.setScene(gameScene);

        });
        medium.setOnMouseEntered(mouseEvent -> medium.setFill(Color.RED));
        medium.setOnMouseExited(mouseEvent -> medium.setFill(Color.BLACK));
        hard.setOnMouseClicked(mouseEvent ->
        {
            difficulty = 2;
            new Player(difficulty);
            over = true;
            Pane gameRoot = new Pane(imageView);
            StageSetter(stage);
            GameScene gameScene = new GameScene(gameRoot, 1200, 600, difficulty, stage, menuScene);
            stage.setX(250);
            stage.setY(150);
            stage.setScene(gameScene);
        });
        hard.setOnMouseEntered(mouseEvent -> hard.setFill(Color.RED));
        hard.setOnMouseExited(mouseEvent -> hard.setFill(Color.BLACK));

        stage.setOnCloseRequest(t ->
        {
            Platform.exit();
            System.exit(0);
        });

        pane = new Pane(imageView);
        pane.getChildren().add(root);
        menuScene = new Scene(pane, 400, 400, Color.rgb(148,197,33));
        GenerateMenuEnemies();
        MenuThread();

        SetOnMusicPlayer();

        StageSetter(stage);
        stage.setScene(menuScene);
        stage.show();

    }

    private ImageView VolumeIcon()
    {
        soundImage = new Image("soundon.jpg");
        ImageView imageView = new ImageView(soundImage);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        imageView.setPreserveRatio(true);
        imageView.setOpacity(0.5);
        imageView.toFront();
        imageView.setOnMouseClicked(mouseEvent ->
        {
            if(soundOn)
            {
                bgMusicPlayer.setVolume(0.0);
                imageView.setImage(new Image("soundoff.jpg"));
                soundOn = false;
            }
            else
            {
                bgMusicPlayer.setVolume(0.7);
                imageView.setImage(new Image("soundon.jpg"));
                soundOn = true;
            }
        });

        return imageView;
    }

    private void SetOnMusicPlayer()
    {
        Media bgMusic = new Media(getClass().getResource("backgroundMusic.mp3").toExternalForm());
        bgMusicPlayer = new MediaPlayer(bgMusic);
        bgMusicPlayer.setAutoPlay(true);
        bgMusicPlayer.setOnRepeat(() -> bgMusicPlayer.seek(Duration.ZERO));
        bgMusicPlayer.setVolume(0.7);
        soundOn = true;
    }

    private void MenuThread()
    {
        Runnable menuRun = () ->
        {
            try
            {
                while (!over)
                {
                    Thread.sleep(20);
                    Platform.runLater(() ->
                            updateMenu(menuScene));
                }
            } catch (InterruptedException ex) {
                System.out.println("Interrupted");
            }
        };

        Thread _thread = new Thread(menuRun);
        _thread.start();
    }
    private void DiffMenuThread()
    {
        Runnable diffMenuRun = () ->
        {
            try
            {
                while (!over)
                {
                    Thread.sleep(20);
                    Platform.runLater(() ->
                            updateDiffMenu(menuScene));
                }
            } catch (InterruptedException ex) {
                System.out.println("Interrupted");
            }
        };

        Thread _threadMenu = new Thread(diffMenuRun);
        _threadMenu.start();
    }

    private void GenerateMenuEnemies()
    {
        for (int i = 0; i <30; i++)
        {
            Enemy _enemy = new Enemy(true);
            pane.getChildren().add(_enemy.GetView());
            enemyMenuList.add(_enemy);
        }
    }

    private void GenerateDiffChoiceEnem()
    {
        for (int i = 0 ; i<30; i++)
        {
            Obstacle _obstacle = new Obstacle(true);
            diffPane.getChildren().add(_obstacle.GetView());
            menuBlockerList.add(_obstacle);
        }
    }

    private void updateMenu(Scene scene)
    {
        for (Enemy e: enemyMenuList)
        {
            e.GetView().setY(e.GetView().getY()+1);
            e.GetView().setOpacity(0.5);
            if(e.GetView().getY()-e.GetView().getHeight() > scene.getHeight())
                e.GetView().setY((int)(Math.random()*(-scene.getHeight())));
        }
    }

    private void updateDiffMenu(Scene scene)
    {
        for (Obstacle o: menuBlockerList)
        {
            o.GetView().setCenterX(o.GetView().getCenterX()+1);
            o.GetView().setOpacity(0.6);
            if(o.GetView().getCenterX()-o.GetView().getRadius() > scene.getWidth())
                o.GetView().setCenterX(0);
        }
    }



    private void StageSetter(Stage stage)
    {
        Image icon = new Image("square.jpg");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setTitle("Square Hunter!");

    }

    public static void main(String[] args)
    {

        launch();
    }
}
