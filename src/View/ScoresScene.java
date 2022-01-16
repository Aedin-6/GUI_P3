package View;

import GameModel.Assets.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class ScoresScene extends Scene
{
    private final ObservableList<Player> data =
            FXCollections.observableArrayList(
                    new Player("Jacob", "Smith"),
                    new Player("Isabella", "Johnson"),
                    new Player("Ethan", "Williams"),
                    new Player("Emma", "Jones"),
                    new Player("Michael", "Brown"));
    public ScoresScene(Parent parent, double v, double v1)
    {
        super(parent, v, v1);

        final HBox hb = new HBox();
        TableView<Player> table = new TableView<Player>();
        //table.setTitle("Table View Sample");

        final Label label = new Label("Scores");
        label.setFont(new Font("Times New Roman", 20));

        table.setEditable(true);

        TableColumn nickname = new TableColumn("Nickname");
        nickname.setMinWidth(100);
        nickname.setCellValueFactory(
                new PropertyValueFactory<Player, String>("nickname"));

        TableColumn score = new TableColumn("Score");
        nickname.setMinWidth(100);
        nickname.setCellValueFactory(
                new PropertyValueFactory<Player, String>("score"));



        table.setItems(data);
        table.getColumns().addAll(nickname, score);

        final TextField addNickname = new TextField();
        addNickname.setPromptText("Nickname");
        addNickname.setMaxWidth(nickname.getPrefWidth());
        final TextField addScore = new TextField();
        addScore.setMaxWidth(score.getPrefWidth());
        addScore.setPromptText("Score");


        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new Player(addNickname.getText(),addScore.getText()));
                addNickname.clear();
                addScore.clear();
            }
        });

        hb.getChildren().addAll(addNickname, addScore, addButton);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);

        ((Group) this.getRoot()).getChildren().addAll(vbox);
    }
}
