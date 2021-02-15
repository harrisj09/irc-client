package com.github.harrisj09.irc.client.data.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.harrisj09.irc.client.data.Channel;

public class DataMappingHandlers {

    private ObjectMapper objectMapper;


    /*
    TODO
    Get this working the way its supposed to
     */
    public Channel[] createChannelArray(String channels) throws JsonProcessingException {
        String[] stringArray = mapData(channels);
        return (Channel[]) convertToObjectArray(stringArray, null);
    }

    private Object[] convertToObjectArray(String[] data, Object objectType) {
        return null;
    }

    private String[] mapData(String data) throws JsonProcessingException {
        return new ObjectMapper().readValue(data, String[].class);
    }

        /*
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
     */
}
