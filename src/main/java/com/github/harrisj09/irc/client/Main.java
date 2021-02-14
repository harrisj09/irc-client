package com.github.harrisj09.irc.client;

import com.github.harrisj09.irc.client.client.ClientController;
import com.github.harrisj09.irc.client.client.ClientModel;
import com.github.harrisj09.irc.client.client.ClientView;
import com.github.harrisj09.irc.client.client.connection.ConnectionHandler;
import com.github.harrisj09.irc.client.data.handlers.DataRetrieveHandler;
import com.github.harrisj09.irc.client.screen.ScreenController;
import com.github.harrisj09.irc.client.start.StartView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;


/**
 * Entry point of application
 *
 * @author John Harris
 */
public class Main extends Application {

    private ConnectionHandler connectionHandler;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new Pane());
        primaryStage.setScene(scene);

        StartView startView = new StartView(primaryStage);

        ScreenController screenController = new ScreenController(scene);
        screenController.addScreen("start", startView.createLayout());
        screenController.activate("start");

        primaryStage.setHeight(600);
        primaryStage.setWidth(600);
        EventHandler<MouseEvent> eventHandler = e -> {
            ClientModel model = new ClientModel();
            connectionHandler = new ConnectionHandler(startView.getIp().getText(), startView.getPort().getText(), startView.getUsername().getText(), model);
            try {
                if (connectionHandler.canConnect()) {
                    ClientController controller = new ClientController(model, new DataRetrieveHandler(startView.getIp().getText(), startView.getPort().getText()));
                    ClientView view = new ClientView(controller, primaryStage);
                    connectionHandler.connectToServer();
                    screenController.addScreen("client", view.getLayout());
                    screenController.activate("client");
                    primaryStage.setMaximized(true);
                } else {

                }
            } catch (URISyntaxException | IOException | InterruptedException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
            }
        };
        startView.getButton().addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        primaryStage.show();
    }
}