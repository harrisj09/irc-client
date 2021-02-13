package com.github.harrisj09.irc.client.data.dto;


import com.github.harrisj09.irc.client.data.Message;
import lombok.Data;

import java.util.LinkedList;

@Data
public class ChannelDTO {

    private String channelName;
    private LinkedList<Message> messageList = new LinkedList<>() {
        @Override
        public boolean add(Message message) {
            if(this.size() > 50) {
                this.removeLast();
            }
            return super.add(message);
        }
    };
}