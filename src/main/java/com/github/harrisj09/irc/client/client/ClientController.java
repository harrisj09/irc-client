package com.github.harrisj09.irc.client.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.harrisj09.irc.client.data.Channel;

public class ClientController {
    private ClientModel clientModel;
    private String channels;

    public ClientController(ClientModel clientModel) {
        this.clientModel = clientModel;
    }

    public void connectToServer(String ip, String port, String username, String channels) throws JsonProcessingException {
        clientModel.setIp(ip);
        clientModel.setPort(port);
        clientModel.setUsername(username);
        clientModel.setChannels(channels);
        this.channels = channels;
    }

    public Channel[] getChannelsArray() throws JsonProcessingException {
        return clientModel.setChannels(channels);
    }
}
