package com.github.harrisj09.irc.client.client;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClientView {
    private final ClientController clientController;
    private final Stage stage;
    private BorderPane borderPane;
    private Node top;
    private Node center;
    private Node bottom;

    public ClientView(ClientController clientController, Stage stage) {
        this.clientController = clientController;
        this.stage = stage;
        borderPane = new BorderPane();
    }

    public BorderPane getLayout() {
        borderPane.setCenter(new ScrollPane());
        borderPane.setLeft(new VBox(new Text("Channels")));
        borderPane.setRight(new VBox(new Text("Users")));
        borderPane.setBottom(new HBox(new TextField(), new Button("Submit")));
        return borderPane;
    }
}
