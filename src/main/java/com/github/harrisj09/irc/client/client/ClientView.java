package com.github.harrisj09.irc.client.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.harrisj09.irc.client.data.Channel;
import com.github.harrisj09.irc.client.data.Message;
import com.github.harrisj09.irc.client.data.User;
import com.github.harrisj09.irc.client.data.cell.ChannelCell;
import com.github.harrisj09.irc.client.data.cell.UserCell;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientView {
    private final ClientController clientController;
    private final Stage stage;
    private BorderPane borderPane;
    private ListView<Channel> channelListView = new ListView<>();
    private ListView<User> usersListView = new ListView<>();
    private ListView<Message> messagesListView = new ListView<>();
    private Channel selectedChannel = null;
    private ExecutorService executor;

    public ClientView(ClientController clientController, Stage stage) {
/*        try {
            System.out.println(clientController.grabMessages(new Channel("hello")));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
        this.clientController = clientController;
        this.stage = stage;
        executor = Executors.newFixedThreadPool(3);
        borderPane = new BorderPane();
    }

    public BorderPane getLayout() throws JsonProcessingException, URISyntaxException {
        Thread thread = new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exc) {
                    throw new Error("Unexpected interruption", exc);
                }
                Platform.runLater(() -> {
                    try {
                        borderPane.setLeft(createLeft());
                        borderPane.setRight(createRight());
                    } catch (JsonProcessingException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
        thread.setDaemon(true);
        thread.start();
        borderPane.setBottom(createBottom());
        // have all of these run in executor thread
        // Messages
        borderPane.setCenter(createCenter());
        // user
        return borderPane;
    }

    private HBox createBottom() {
        TextField input = new TextField();
        input.setPrefWidth(600);
        input.setPrefHeight(100);
        input.setAlignment(Pos.CENTER);
        Button submit = new Button("Submit");
        submit.setPrefWidth(100);
        submit.setPrefWidth(100);
        return new HBox(input, submit);
    }

    public Node createCenter() throws JsonProcessingException {
        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ScrollPane.html
        return new ScrollPane();
    }

    public Node createRight() throws JsonProcessingException, URISyntaxException {
        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ScrollPane.html
        ObservableList<User> users = null;
        List<User> usersList = clientController.grabUsers();
        usersListView = new ListView<>();
        if(!usersList.isEmpty()) {
            users = FXCollections.observableArrayList(clientController.grabUsers());
        }
        usersListView.setItems(users);
        usersListView.setCellFactory(param -> new UserCell(clientController));
        return usersListView;
    }

    public Node createLeft() throws JsonProcessingException, URISyntaxException {
        ObservableList<Channel> channels = null;
        List<Channel> channelList = clientController.grabChannels();
        channelListView = new ListView<>();
        if(!channelList.isEmpty()) {
            channels = FXCollections.observableArrayList(clientController.grabChannels());
        }
        channelListView.setItems(channels);
        channelListView.setCellFactory(param -> new ChannelCell(clientController));
        channelListView.getSelectionModel().selectedItemProperty().addListener(e -> {
            selectedChannel = channelListView.getSelectionModel().getSelectedItem();
            clientController.changeChannel(selectedChannel);
            System.out.println(selectedChannel.getChannelName());
        });
        return channelListView;
    }
}
