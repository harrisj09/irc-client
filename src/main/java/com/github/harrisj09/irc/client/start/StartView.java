package com.github.harrisj09.irc.client.start;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class StartView {

    private final StartController startController;
    private final Stage stage;
    private BorderPane borderPane;
    private Node top;
    private Node center;
    private Node bottom;

    public StartView(StartController startController, Stage stage) {
        this.startController = startController;
        this.stage = stage;
        borderPane = new BorderPane();
    }


}
