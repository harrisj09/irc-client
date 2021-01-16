package com.github.harrisj09.irc.client.start;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class StartView {
    private final StartController startController;
    private final Stage stage;
    private Node top;
    private Node center;
    private Node bottom;

    public StartView(StartController startController, Stage stage) {
        this.startController = startController;
        this.stage = stage;
    }

    public VBox createLayout() {
        TextField ip = new TextField();
        TextField port = new TextField();
        TextField userName = new TextField();
        HBox ipBox = new HBox(new Text("IP"), ip);
        HBox portBox = new HBox(new Text("Port"), port);
        HBox userNameBox = new HBox(new Text("UserName"), userName);
        Button button = new Button("Submit");
        return new VBox(ipBox, portBox, userNameBox, button);
    }
}
