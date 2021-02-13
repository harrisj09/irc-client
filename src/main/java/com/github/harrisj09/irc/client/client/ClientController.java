package com.github.harrisj09.irc.client.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.harrisj09.irc.client.data.Channel;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientController {
    private ClientModel clientModel;
    private String channels;
    private Channel currentChannel;
    private boolean connectedToServer = false;

    public ClientController(ClientModel clientModel) {
        this.clientModel = clientModel;
        currentChannel = null;
    }

    public void refreshData() {
        Thread thread = new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException exc) {
                    throw new Error("Unexpected interruption", exc);
                }
                if (isConnectedToServer()) {
                    Platform.runLater(() -> System.out.println("Hello"));
/*
                    try {
                        grabChannelsData();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
*/
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Deprecated
    // Move this to DataRetrieveHandler
    public String grabChannels() throws URISyntaxException {
        HttpRequest build = HttpRequest.newBuilder().GET().uri(
                new URI("http://" + clientModel.getIp() + ":" + clientModel.getPort() + "/connect/servers")).build();
        HttpResponse<String> send;
        try {
            send = HttpClient.newBuilder()
                    .build()
                    .send(build, HttpResponse.BodyHandlers.ofString());
            if (send.statusCode() == 200) {
                return send.body();
            }
            if (send.statusCode() == 409) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Username already taken");
                alert.show();
            }
            // Add if statement for CONFLICT HttpStatus
        } catch (IOException | InterruptedException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Failed to connect");
            alert.show();
        }
        return null;
    }

    /*
    TODO:
        - Remove these
     */
    public String grabChannelMessages() {
        return "";
    }

    public String grabServerUsers() {
        return "";
    }

    public Channel[] getChannelsArray() throws JsonProcessingException {
        return clientModel.setChannels(channels);
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public void changeChannel(Channel channel) {
        currentChannel = channel;
    }

    public Channel getCurrentChannel() {
        return currentChannel;
    }

    public boolean isConnectedToServer() {
        return connectedToServer;
    }
}
