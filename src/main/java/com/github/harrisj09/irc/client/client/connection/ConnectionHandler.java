package com.github.harrisj09.irc.client.client.connection;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnectionHandler {

    private String channels;
    private String ip;
    private String port;
    private String username;

    public ConnectionHandler(String ip, String port, String username) {
        this.ip = ip;
        this.port = port;
        this.username = username;
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

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
