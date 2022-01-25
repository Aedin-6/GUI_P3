package View;

import GameModel.Assets.Player;
import GameModel.TimeFlow;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.*;
import java.util.ArrayList;

public class ScoresScene extends Scene implements Serializable
{
    private ObservableList<Scores> scores;


    public ScoresScene(Parent parent, double v, double v1, boolean isSave)
    {
        super(parent, v, v1);
        HBox hBox = new HBox();
        TableView<Scores> scoresTable = new TableView<>();
        Label scoreLabel = new Label("Scores");
        scoreLabel.setFont(new Font("Times New Roman", 20));
        File f = new File("ScoreBoard");
        if(f.exists() && !f.isDirectory())
        {
            Load();
        }
        else
        {
            ArrayList<Scores> list = new ArrayList<>();
            scores = FXCollections.observableList(list);
        }

        scoresTable.setEditable(true);

        TableColumn nickname = new TableColumn("Nickname");
        nickname.setMinWidth(100);
        nickname.setCellValueFactory(new PropertyValueFactory<Scores, String>("nickname"));

        TableColumn score = new TableColumn("Score");
        score.setMinWidth(100);
        score.setCellValueFactory(new PropertyValueFactory<Scores, String>("score"));

        TableColumn time = new TableColumn("Time");
        time.setMinWidth(100);
        time.setCellValueFactory(new PropertyValueFactory<Scores, String>("time"));



        scoresTable.setItems(scores);
        scoresTable.getColumns().addAll(nickname, score, time);
        scoresTable.getSortOrder().add(score);
        scoresTable.getSortOrder().add(time);
        scoresTable.getSortOrder().add(nickname);

        TextField addNickname = new TextField();
        addNickname.setPromptText("Nickname");
        if(!isSave)
            addNickname.setDisable(true);
        addNickname.setMaxWidth(nickname.getPrefWidth());

        Label addScore = new Label();
        addScore.setMaxWidth(score.getPrefWidth());
        addScore.setText(String.valueOf(Player.GetScore()));

        Label addTime = new Label();
        addTime.setMaxWidth(time.getPrefWidth());
        addTime.setText(TimeFlow.GetTime());


        Button addBtn = new Button("Add Score");
        if(!isSave)
            addBtn.setDisable(true);
        addBtn.setOnAction(e ->
        {
            scores.add(new Scores(addNickname.getText(), addScore.getText(), addTime.getText()));
            addNickname.clear();
            //addScore.clear();
        });

        Button saveAndExitBtn = new Button("Save & Exit");
        if(!isSave)
            saveAndExitBtn.setText("Exit");
            saveAndExitBtn.setOnAction(e ->
        {
            if(isSave)
            {
                SaveScores();
            }
            Platform.exit();
            System.exit(0);
        });

        hBox.getChildren().addAll(addNickname, addScore, addTime, addBtn, saveAndExitBtn);
        hBox.setSpacing(3);

        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10, 0, 0, 10));
        vBox.getChildren().addAll(scoreLabel, scoresTable, hBox);
        setFill(Color.GREENYELLOW);
        ((Group) this.getRoot()).getChildren().addAll(vBox);
    }

    private void SaveScores()
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("ScoreBoard");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<>(scores));
            oos.close();
        }
        catch (Exception e)
        {
            Alert errorMsg = new Alert(Alert.AlertType.ERROR);
            errorMsg.setTitle("Error");
            errorMsg.setHeaderText("Saving: Error Occurred");
            errorMsg.setContentText("Error during saving the scoreboard: " + e.toString());
            errorMsg.showAndWait();
        }
    }

    private void Load()
    {
        try
        {
            FileInputStream fis = new FileInputStream("ScoreBoard");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Scores> list = (ArrayList<Scores>) ois.readObject();
            scores = FXCollections.observableList(list);
            ois.close();

        }
        catch (Exception e)
        {
            Alert errorMsg = new Alert(Alert.AlertType.ERROR);
            errorMsg.setTitle("Error");
            errorMsg.setHeaderText("Loading: Error Occurred\"");
            errorMsg.setContentText("Error during loading scoreboard: " + e.toString());
            errorMsg.showAndWait();
        }
    }
}

