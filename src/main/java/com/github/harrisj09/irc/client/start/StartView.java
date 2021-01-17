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
    private TextField ip;
    private TextField port;
    private TextField username;
    private HBox ipBox;
    private HBox portBox;
    private HBox userNameBox;
    private Button button;

    public StartView(StartController startController, Stage stage) {
        this.startController = startController;
        this.stage = stage;
    }

    public VBox createLayout() {
        ip = new TextField();
        port = new TextField();
        username = new TextField();
        ipBox = new HBox(new Text("IP"), ip);
        portBox = new HBox(new Text("Port"), port);
        userNameBox = new HBox(new Text("UserName"), username);
        button = new Button("Submit");
        return new VBox(ipBox, portBox, userNameBox, button);
    }

    public Button getButton() {
        return button;
    }

    public TextField getIp() {
        return ip;
    }

    public TextField getPort() {
        return port;
    }

    public TextField getUsername() {
        return username;
    }
}
