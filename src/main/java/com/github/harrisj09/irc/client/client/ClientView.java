package com.github.harrisj09.irc.client.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.harrisj09.irc.client.data.Channel;
import com.github.harrisj09.irc.client.data.cell.ChannelCell;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
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
    private ListView<Channel> channelListView = new ListView<>();
    private Channel selectedChannel = null;

    public ClientView(ClientController clientController, Stage stage) {
        this.clientController = clientController;
        this.stage = stage;
        borderPane = new BorderPane();
    }

    public BorderPane getLayout() throws JsonProcessingException {
        borderPane.setCenter(createCenter());
        borderPane.setLeft(createLeft());
        borderPane.setRight(createRight());
        borderPane.setBottom(new HBox(new TextField(), new Button("Submit")));
        return borderPane;
    }

    public Node createCenter() throws JsonProcessingException {
        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ScrollPane.html
        return new ScrollPane();
    }

    public Node createRight() throws JsonProcessingException {
        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ScrollPane.html
        return new ScrollPane(new Text("Users"));
    }

    public Node createLeft() throws JsonProcessingException {
        channelListView = new ListView<>();
        ObservableList<Channel> channels = FXCollections.observableArrayList(Arrays.asList(clientController.getChannelsArray()));
        channelListView.setItems(channels);
        channelListView.setCellFactory(param -> new ChannelCell(clientController));
        channelListView.getSelectionModel().selectedItemProperty().addListener(e -> {
            selectedChannel = channelListView.getSelectionModel().getSelectedItem();
            clientController.changeChannel(selectedChannel);
            System.out.println(selectedChannel.getChannelName());
        });
    //list.setCellFactory(param -> new MusicCell(musicController));
        /*
        Have a method in ClientController that accepts what channel you clicked on in ClientController so you
        can perform whatever's needed.
         */
        return channelListView;
    }

    public void refreshData() throws JsonProcessingException {
        channelListView = new ListView<>();
        ObservableList<Channel> channels = FXCollections.observableArrayList(Arrays.asList(clientController.getChannelsArray()));
        channelListView.setItems(channels);
        channelListView.setCellFactory(param -> new ChannelCell(clientController));
        channelListView.getSelectionModel().selectedItemProperty().addListener(e -> {
            selectedChannel = channelListView.getSelectionModel().getSelectedItem();
            clientController.changeChannel(selectedChannel);
            System.out.println(selectedChannel.getChannelName());
        });
        borderPane.setLeft(channelListView);
        // grab users
        if (selectedChannel != null) {
            // grab messages
        }
    }
}
