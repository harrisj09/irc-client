package com.github.harrisj09.irc.client.client.connection;

import com.github.harrisj09.irc.client.client.ClientModel;
import javafx.scene.control.Alert;
import lombok.Data;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Data
public class ConnectionHandler {

    private String channels;
    private String ip;
    private String port;
    private String username;
    private ClientModel model;

    public ConnectionHandler(String ip, String port, String username, ClientModel model) {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.model = model;
    }

    public boolean canConnect() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest build = HttpRequest.newBuilder().GET().uri(
                new URI("http://" + this.ip + ":" + this.port + "/connect/" + this.username)).build();
        HttpResponse<String> send;
        try {
            send = HttpClient.newBuilder()
                    .build()
                    .send(build, HttpResponse.BodyHandlers.ofString());
            if (send.statusCode() == 200) {
                setChannels(send.body());
                return true;
            }
            if (send.statusCode() == 409) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Username already taken");
                alert.show();
            }
            // Add if statement for CONFLICT HttpStatus
        } catch (ConnectException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Failed to connect");
            alert.show();
        }
        return false;
    }

    public void connectToServer() throws IOException {
        model.setIp(getIp());
        model.setPort(getPort());
        model.setUsername(getUsername());
        model.setChannels(getChannels());
        model.setConnectedToServer(true);
    }
}
