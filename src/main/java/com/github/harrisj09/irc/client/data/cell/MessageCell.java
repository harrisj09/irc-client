package com.github.harrisj09.irc.client.data.cell;

import com.github.harrisj09.irc.client.client.ClientController;
import com.github.harrisj09.irc.client.data.Message;
import com.github.harrisj09.irc.client.data.User;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MessageCell extends ListCell<Message> {

    private ClientController clientController;

    public MessageCell(ClientController clientController) {
        super();
        this.clientController = clientController;
    }

    @Override
    protected void updateItem(Message item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            setGraphic(createChannelCell(item));
        }
    }

    private Node createChannelCell(Message item) {
        return new HBox(new Text(item.getUser()), new Text(item.getMessage()));
    }
}
