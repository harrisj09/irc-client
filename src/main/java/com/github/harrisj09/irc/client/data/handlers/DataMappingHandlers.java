package com.github.harrisj09.irc.client.data.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.harrisj09.irc.client.data.Channel;
import com.github.harrisj09.irc.client.data.Message;
import com.github.harrisj09.irc.client.data.User;

public class DataMappingHandlers {

    public Channel[] createChannelArray(String channels) throws JsonProcessingException {
        return mapChannelData(channels);
    }

    public User[] createUserArray(String users) throws JsonProcessingException {
        return mapUserData(users);
    }

    public Message[] createMessageArray(String messages) throws JsonProcessingException {
        return mapMessageData(messages);
    }

    private Channel[] mapChannelData(String data) throws JsonProcessingException {
        return new ObjectMapper().readValue(data, Channel[].class);
    }

    private User[] mapUserData(String data) throws JsonProcessingException {
        return new ObjectMapper().readValue(data, User[].class);
    }

    private Message[] mapMessageData(String data) throws JsonProcessingException {
        return new ObjectMapper().readValue(data, Message[].class);
    }
}