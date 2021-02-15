package com.github.harrisj09.irc.client.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.harrisj09.irc.client.client.connection.ConnectionHandler;
import com.github.harrisj09.irc.client.data.Channel;
import com.github.harrisj09.irc.client.data.Message;
import com.github.harrisj09.irc.client.data.User;
import lombok.Data;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;

@Data
public class ClientModel {

    private String ip;
    private String port;
    private String username;
    private Collection<Channel> channels;
    private Collection<User> users;
    private Collection<Message> channelMessages;
    private boolean connectedToServer = false;
    private ConnectionHandler connectionHandler;

    public ClientModel(String ip, String port, String username, ConnectionHandler connectionHandler) {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.connectionHandler = connectionHandler;
    }

    public boolean canConnect() throws InterruptedException, IOException, URISyntaxException {
        return connectionHandler.canConnect(ip, port, username);
    }

    public void connectToServer() throws IOException {
        setIp(connectionHandler.getIp());
        setPort(connectionHandler.getPort());
        setUsername(connectionHandler.getUsername());
        setChannels(connectionHandler.getChannels());
        setConnectedToServer(true);
    }

    public boolean isConnectedToServer() {
        return connectedToServer;
    }

    public void setConnectedToServer(boolean connectedToServer) {
        this.connectedToServer = connectedToServer;
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

    public Channel[] setChannels(String channels) throws JsonProcessingException {
        String[] channelString = mapData(channels);
        Channel[] channelArray = new Channel[channelString.length];
        for (int i = 0; i < channelArray.length; i++) {
            channelArray[i] = new Channel(channelString[i]);
        }
        return channelArray;
    }

    public Channel[] grabUsers(String users) throws JsonProcessingException {
        String[] userString = mapData(users);
        Channel[] usersArray = new Channel[userString.length];
        for (int i = 0; i < usersArray.length; i++) {
            usersArray[i] = new Channel(userString[i]);
        }
        return usersArray;
    }

    public String[] mapData(String data) throws JsonProcessingException {
        return new ObjectMapper().readValue(data, String[].class);
    }
}
