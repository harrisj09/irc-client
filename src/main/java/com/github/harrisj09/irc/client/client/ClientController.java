package com.github.harrisj09.irc.client.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.harrisj09.irc.client.data.Channel;
import com.github.harrisj09.irc.client.data.Message;
import com.github.harrisj09.irc.client.data.User;
import com.github.harrisj09.irc.client.data.handlers.DataRetrieveHandler;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientController {
    private ClientModel clientModel;
    private Channel currentChannel;
    private DataRetrieveHandler dataRetrieveHandler;

    public ClientController(ClientModel clientModel, DataRetrieveHandler dataRetrieveHandler) {
        this.clientModel = clientModel;
        this.dataRetrieveHandler = dataRetrieveHandler;
        currentChannel = null;
    }

    public List<Channel> grabChannels() throws URISyntaxException, JsonProcessingException {
        String data = dataRetrieveHandler.fetchData(clientModel.getIp(), clientModel.getPort(), "connect/servers");
        return Arrays.asList(dataRetrieveHandler.grabChannels(data));
    }

    public void changeChannel(Channel channel) {
        currentChannel = channel;
    }

    public List<User> grabUsers() throws URISyntaxException, JsonProcessingException {
        String data = dataRetrieveHandler.fetchData(clientModel.getIp(), clientModel.getPort(), "connect/users");
        return Arrays.asList(dataRetrieveHandler.grabUsers(data));
    }

    public List<Message> grabMessages(Channel currentChannel) throws URISyntaxException, JsonProcessingException {
        // channels/hello/latest
        String data = dataRetrieveHandler.fetchData(clientModel.getIp(), clientModel.getPort(), "channels/" + currentChannel.getChannelName() + "/latest");
        return Arrays.asList(dataRetrieveHandler.grabMessages(data));
    }
}