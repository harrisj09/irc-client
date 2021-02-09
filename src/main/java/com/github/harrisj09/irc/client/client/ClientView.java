package com.github.harrisj09.irc.client.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.harrisj09.irc.client.data.Channel;
import com.github.harrisj09.irc.client.data.cell.ChannelCell;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        refreshData();
        return borderPane;
    }

    public void refreshData() {
        Thread thread = new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException exc) {
                    throw new Error("Unexpected interruption", exc);
                }
                if (clientController.isConnectedToServer()) {
                    Platform.runLater(() -> {
                        try {
                            clientController.setChannels(clientController.grabChannels());
                            Channel[] channels = clientController.getChannelsArray();
                            reCreateLeft(channels);
                            if (selectedChannel != null) {

                            }
                        } catch (URISyntaxException | JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
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
        return channelListView;
    }

    // TODO Fix this
    public void reCreateLeft(Channel[] channelArray) {
        channelListView = new ListView<>();
        ObservableList<Channel> channels = FXCollections.observableArrayList(Arrays.asList(channelArray));
        channelListView.setItems(channels);
        channelListView.setCellFactory(param -> new ChannelCell(clientController));
        channelListView.getSelectionModel().selectedItemProperty().addListener(e -> {
            selectedChannel = channelListView.getSelectionModel().getSelectedItem();
            clientController.changeChannel(selectedChannel);
        });
    }
}
