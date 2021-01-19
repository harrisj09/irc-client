package com.github.harrisj09.irc.client.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.harrisj09.irc.client.data.Channel;
import com.github.harrisj09.irc.client.data.Message;
import com.github.harrisj09.irc.client.data.User;

import java.util.Arrays;
import java.util.Collection;

public class ClientModel {

    private String ip;
    private String port;
    private String username;
    private Collection<Channel> channels;
    private Collection<User> users;
    private Collection<Message> channelMessages;

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
        String[] channelStrings = new ObjectMapper().readValue(channels, String[].class);
        Channel[] channelArray = new Channel[channelStrings.length];
        for (int i = 0; i < channelArray.length; i++) {
            channelArray[i] = new Channel(channelStrings[i]);
        }
        return channelArray;
    }
}
