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
    private DataMappingHandlers dataMappingHandlers;

    public DataRetrieveHandler() {
        this.dataMappingHandlers = new DataMappingHandlers();
    }

    public String grabChannels(String ip, String port) throws URISyntaxException {
        this.server += ip + ":" + port + "/";
        HttpRequest build = HttpRequest.newBuilder().GET().uri(new URI(server + "connect/servers")).build();
        HttpResponse<String> send;
        try {
            send = getResponse(build);
            // Successful
            if (send.statusCode() == 200) {
                // pass this into dataMapping
                dataMappingHandlers.createChannelArray(send.body());
                return null;
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

    private HttpResponse<String> getResponse(HttpRequest build) throws IOException, InterruptedException {
        return HttpClient.newBuilder().build().send(build, HttpResponse.BodyHandlers.ofString());
    }

    public String grabUsers() {
        return null;
    }

    public String grabMessages() {
        return null;
    }
}
