package View;

import GameModel.Assets.Player;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.*;

public class ScoresScene extends Scene implements Serializable
{
    private ObservableList<Scores> scores = new ObservableListBase<Scores>()
    {
        @Override
        public Scores get(int index)
        {
            return null;
        }

        @Override
        public int size()
        {
            return 0;
        }
    };

    public ScoresScene(Parent parent, double v, double v1)
    {
        super(parent, v, v1);
        final HBox hBox = new HBox();
        TableView<Scores> table = new TableView<>();
        final Label scoreLabel = new Label("Scores");
        scoreLabel.setFont(new Font("Times New Roman", 20));

        table.setEditable(true);

        TableColumn nickname = new TableColumn("Nickname");
        nickname.setMinWidth(125);
        nickname.setCellValueFactory(
                new PropertyValueFactory<Scores, String>("nickname"));

        TableColumn score = new TableColumn("Score");
        score.setMinWidth(125);
        score.setCellValueFactory(
                new PropertyValueFactory<Scores, String>("score"));



        table.setItems(scores);
        table.getColumns().addAll(nickname, score);

        final TextField addNickname = new TextField();
        addNickname.setPromptText("Nickname");
        addNickname.setMaxWidth(nickname.getPrefWidth());
        final Label addScore = new Label();
        addScore.setMaxWidth(score.getPrefWidth());
        addScore.setText(String.valueOf(Player.GetScore()));


        final Button addBtn = new Button("Add");
        addBtn.setOnAction(e ->
        {
            scores.add(new Scores(addNickname.getText(), addScore.getText()));
            addNickname.clear();
            //addScore.clear();
        });

        final Button saveAndExitBtn = new Button("Save & Exit");
        saveAndExitBtn.setOnAction(e ->
        {
            SaveScores();
            Platform.exit();
            System.exit(0);
            //addScore.clear();
        });

        hBox.getChildren().addAll(addNickname, addScore, addBtn, saveAndExitBtn);
        hBox.setSpacing(3);

        final VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10, 0, 0, 10));
        vBox.getChildren().addAll(scoreLabel, table, hBox);

        ((Group) this.getRoot()).getChildren().addAll(vBox);
    }

    private void SaveScores()
    {
        try
        {
            FileOutputStream scoreBoard = new FileOutputStream("ScoreBoard");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(scoreBoard);
            objectOutputStream.writeObject(scores);
            objectOutputStream.close();
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

    private ObservableList<Scores> Load()
    {
        try
        {
            FileInputStream fis = new FileInputStream("ScoreBoard");
            ObjectInputStream ois = new ObjectInputStream(fis);
            scores = ( ObservableList<Scores>) ois.readObject();
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
        return scores;
    }
}

