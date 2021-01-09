package com.github.harrisj09.irc.client.start;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
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
