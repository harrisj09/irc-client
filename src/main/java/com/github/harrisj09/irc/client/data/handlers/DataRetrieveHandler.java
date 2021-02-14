package com.github.harrisj09.irc.client.data.handlers;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DataRetrieveHandler {
    // http://localhost:8080/createchannel/{{name}}
    private String server = "http://";

    public DataRetrieveHandler(String ip, String port) {
        this.server += ip + ":" + port + "/";
    }

    public String grabChannels() throws URISyntaxException {
        HttpRequest build = HttpRequest.newBuilder().GET().uri(
                new URI(server + "connect/servers")).build();
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
}
