package com.github.harrisj09.irc.client.data.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.harrisj09.irc.client.data.Channel;
import com.github.harrisj09.irc.client.data.Message;
import com.github.harrisj09.irc.client.data.User;
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

    public String fetchData(String ip, String port, String endpoint) throws URISyntaxException {
        this.server += ip + ":" + port + "/" + endpoint;
        HttpRequest build = HttpRequest.newBuilder().GET().uri(new URI(server)).build();
        HttpResponse<String> send;
        try {
            send = getResponse(build);
            // Successful
            if (send.statusCode() == 200) {
                return send.body();
            }
            if (send.statusCode() == 409) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Conflict occurred");
                alert.show();
            }
        } catch (IOException | InterruptedException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Failed to connect");
            alert.show();
        }
        return null;
    }

    private HttpResponse<String> getResponse(HttpRequest build) throws IOException, InterruptedException {
        return HttpClient.newBuilder().build().send(build, HttpResponse.BodyHandlers.ofString());
    }

    public Channel[] grabChannels(String data) throws JsonProcessingException {
        return dataMappingHandlers.createChannelArray(data);
    }

    public User[] grabUsers(String data) throws JsonProcessingException {
        return dataMappingHandlers.createUserArray(data);
    }

    public Message[] grabMessages(String data) throws JsonProcessingException {
        return dataMappingHandlers.createMessageArray(data);
    }
}
