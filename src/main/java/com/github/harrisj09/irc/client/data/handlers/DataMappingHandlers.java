package com.github.harrisj09.irc.client.data.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.harrisj09.irc.client.data.Channel;
import com.github.harrisj09.irc.client.data.Message;
import com.github.harrisj09.irc.client.data.User;

public class DataMappingHandlers {

    public Channel[] createChannelArray(String channels) throws JsonProcessingException {
        if (channels != null) {
            String[] channelString = new ObjectMapper().readValue(channels, String[].class);
            Channel[] channelArray = new Channel[channelString.length];
            for (int i = 0; i < channelArray.length; i++) {
                channelArray[i] = new Channel(channelString[i]);
            }
            return channelArray;
        }
        return new Channel[0];
    }

    public User[] createUserArray(String users) throws JsonProcessingException {
        if (users != null) {
            String[] userString = new ObjectMapper().readValue(users, String[].class);
            User[] usersArray = new User[userString.length];
            for (int i = 0; i < usersArray.length; i++) {
                usersArray[i] = new User(userString[i]);
            }
            return usersArray;
        }
        return null;
    }

    public Message[] createMessageArray(String messages) throws JsonProcessingException {
        return null;
    }
}