package com.github.harrisj09.irc.client.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.harrisj09.irc.client.data.Channel;
import com.github.harrisj09.irc.client.data.Message;
import com.github.harrisj09.irc.client.data.User;
import com.github.harrisj09.irc.client.data.handlers.DataRetrieveHandler;

import java.net.URISyntaxException;

public class ClientController {
    private ClientModel clientModel;
    private Channel currentChannel;
    private DataRetrieveHandler dataRetrieveHandler;

    public ClientController(ClientModel clientModel, DataRetrieveHandler dataRetrieveHandler) {
        this.clientModel = clientModel;
        this.dataRetrieveHandler = dataRetrieveHandler;
        currentChannel = null;
    }

    public Channel[] grabChannels() throws URISyntaxException, JsonProcessingException {
        String data = dataRetrieveHandler.fetchData(clientModel.getIp(), clientModel.getPort(), "connect/servers");
        return dataRetrieveHandler.grabChannels(data);
    }

    public void changeChannel(Channel channel) {
        currentChannel = channel;
    }

    public User[] grabUsers() throws URISyntaxException, JsonProcessingException {
        String data = dataRetrieveHandler.fetchData(clientModel.getIp(), clientModel.getPort(), "connect/users");
        return dataRetrieveHandler.grabUsers(data);
    }

    public Message[] grabMessages(Channel currentChannel) throws URISyntaxException {
        //dataRetrieveHandler.grabMessages(clientModel.getIp(), clientModel.getPort());
        return null;
    }
}
