package com.github.harrisj09.irc.client.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.harrisj09.irc.client.data.Channel;
import com.github.harrisj09.irc.client.data.Message;
import com.github.harrisj09.irc.client.data.User;
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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientView {
    private final ClientController clientController;
    private final Stage stage;
    private BorderPane borderPane;
    private ListView<Channel> channelListView = new ListView<>();
    private Channel selectedChannel = null;
    private ExecutorService executor;

    private Channel[] channels;
    private User[] users;
    private Message[] messages;

    public ClientView(ClientController clientController, Stage stage) {
        this.clientController = clientController;
        this.stage = stage;
        executor = Executors.newFixedThreadPool(3);
        borderPane = new BorderPane();
    }


    /*
    Do something like this

    Follow this: https://www.baeldung.com/java-executor-service-tutorial
    Number 6 would be helpful for this

    Runnable runnableTask = () -> {
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    if(currentChannel != null) {
        Message[] messages = executorService.invokeAny(borderPane.setCenter(createCenter));
    }

    Channel[] channels = executorService.invokeAny(borderPane.setLeft(createLeft()));
    Users[] users = executorService.invokeAny(borderPane.setRight(createRight()));
     */

    public BorderPane getLayout() throws JsonProcessingException {
        borderPane.setBottom(createBottom());
        // have all of these run in executor thread
        borderPane.setCenter(createCenter());
        borderPane.setLeft(createLeft());
        borderPane.setRight(createRight());
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

    public Node createRight() throws JsonProcessingException {
        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ScrollPane.html
        ScrollPane usersList = new ScrollPane();
        usersList.setPrefWidth(300);
        return new ScrollPane(new Text("Users"));
    }

    public Node createLeft() throws JsonProcessingException {
        channelListView = new ListView<>();
/*
        call grabChannels(), apply that to observable list
        set the items
        setCellFactory
        addListeners for clicks

        ----
        ObservableList<Channel> channels = FXCollections.observableArrayList(Arrays.asList(clientController.getChannelsArray()));
        channelListView.setItems(channels);
        channelListView.setCellFactory(param -> new ChannelCell(clientController));
        channelListView.getSelectionModel().selectedItemProperty().addListener(e -> {
            selectedChannel = channelListView.getSelectionModel().getSelectedItem();
            clientController.changeChannel(selectedChannel);
            System.out.println(selectedChannel.getChannelName());
        });*/
        return channelListView;
    }
}
