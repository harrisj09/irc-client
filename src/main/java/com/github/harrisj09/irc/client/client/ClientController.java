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

    /*
    This will call a method from clientModel that
    uses the ExecutorService to retrieve data

    // THIS COULD BE USEFUL SINCE YOU NEED A RUN LATER IF IM CORRECT
         public void refreshData() {
        Thread thread = new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException exc) {
                    throw new Error("Unexpected interruption", exc);
                }
                if (clientController.isConnectedToServer()) {
                    Platform.runLater(() -> {
                        try {
                            System.out.println("Here");
                            System.out.println(clientController.grabChannels());
                            clientController.setChannels(clientController.grabChannels());
                            Channel[] channels = clientController.getChannelsArray();
                            System.out.println(Arrays.toString(channels));
                            borderPane.setLeft(reCreateLeft(channels));
                            if (selectedChannel != null) {

                            }
                        } catch (URISyntaxException | JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
     */

    public Channel[] grabChannels() throws URISyntaxException, JsonProcessingException {
        String data = dataRetrieveHandler.fetchData(clientModel.getIp(), clientModel.getPort(), "connect/servers");
        return dataRetrieveHandler.grabChannels(data);
    }

    // fix this
    public User[] grabUsers() throws URISyntaxException {
        //dataRetrieveHandler.grabUsers(clientModel.getIp(), clientModel.getPort());
        return null;
    }

    public Message[] grabMessages(Channel currentChannel) throws URISyntaxException {
        //dataRetrieveHandler.grabMessages(clientModel.getIp(), clientModel.getPort());
        return null;
    }
}
