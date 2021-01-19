package com.github.harrisj09.irc.client.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.harrisj09.irc.client.data.Channel;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;

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
        borderPane.setLeft(new ScrollPane(new Text("Channels")));
        borderPane.setRight(new ScrollPane(new Text("Users")));
        borderPane.setBottom(new HBox(new TextField(), new Button("Submit")));
        return borderPane;
    }

    public Node createCenter() throws JsonProcessingException {
        Channel[] channelNames = clientController.getChannelsArray();
        ListView<Channel> list = new ListView<>();
        ObservableList<Channel> channels = (ObservableList<Channel>) Arrays.asList(clientController.getChannelsArray());
        list.setItems(channels);
        // configure this to channels
        //list.setCellFactory(param -> new MusicCell(musicController));
        return list;
    }
}
