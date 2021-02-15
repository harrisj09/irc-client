package com.github.harrisj09.irc.client.client;

import com.github.harrisj09.irc.client.data.Channel;
import com.github.harrisj09.irc.client.data.Message;
import com.github.harrisj09.irc.client.data.User;
import com.github.harrisj09.irc.client.data.handlers.DataRetrieveHandler;

import java.net.URISyntaxException;

public class ClientController {
    private ClientModel clientModel;
    private String channels;
    private Channel currentChannel;
    private boolean connectedToServer = false;
    private DataRetrieveHandler dataRetrieveHandler;

    public ClientController(ClientModel clientModel, DataRetrieveHandler dataRetrieveHandler) {
        this.clientModel = clientModel;
        this.dataRetrieveHandler = dataRetrieveHandler;
        currentChannel = null;
    }

    /*
    This will call a method from clientModel that
    uses the ExecutorService to retrive data
     */

    public Channel[] grabChannels() throws URISyntaxException {
        dataRetrieveHandler.grabChannels(clientModel.getIp(), clientModel.getPort());
        return null;
    }

    public User[] grabUsers() throws URISyntaxException {
        //dataRetrieveHandler.grabUsers(clientModel.getIp(), clientModel.getPort());
        return null;
    }

    public Message[] grabMessages(Channel currentChannel) throws URISyntaxException {
        //dataRetrieveHandler.grabMessages(clientModel.getIp(), clientModel.getPort());
        return null;
    }
}
