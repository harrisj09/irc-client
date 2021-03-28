package com.github.harrisj09.irc.client.data.DTO;

import com.github.harrisj09.irc.client.data.User;
import lombok.Data;

import java.net.InetAddress;

@Data
public class UserDTO {
    private String userName;
    private InetAddress ip;

    public User createUser() {
        return new User(userName, ip);
    }
}
