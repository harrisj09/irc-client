package com.github.harrisj09.irc.client.data.cell;

import com.github.harrisj09.irc.client.client.ClientController;
import com.github.harrisj09.irc.client.data.Channel;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ChannelCell extends ListCell<Channel> {

    private ClientController clientController;

    public ChannelCell(ClientController clientController) {
        super();
        this.clientController = clientController;
    }

    @Override
    protected void updateItem(Channel item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            setGraphic(createChannelCell(item));
        }
    }

    private Node createChannelCell(Channel item) {
        return new HBox(new TextField(item.getChannelName()));
    }
}
