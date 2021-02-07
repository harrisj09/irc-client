package com.github.harrisj09.irc.client.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.harrisj09.irc.client.data.Channel;

public class ClientController {
    private ClientModel clientModel;
    private String channels;
    private Channel currentChannel;

    public ClientController(ClientModel clientModel) {
        this.clientModel = clientModel;
        currentChannel = null;
    }

    public void connectToServer(String ip, String port, String username, String channels) throws JsonProcessingException {
        clientModel.setIp(ip);
        clientModel.setPort(port);
        clientModel.setUsername(username);
        clientModel.setChannels(channels);
        this.channels = channels;
        /*
                HttpRequest build = HttpRequest.newBuilder().GET().uri(
                new URI("http://" + ip + ":" + port + "/connect/" + username)).build();
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
        } catch (ConnectException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Failed to connect");
            alert.show();
        }
         */
    }

    public Channel[] getChannelsArray() throws JsonProcessingException {
        return clientModel.setChannels(channels);
    }


    public void changeChannel(Channel channel) {
        currentChannel = channel;
    }

    public Channel getCurrentChannel() {
        return currentChannel;
    }
}
