package com.github.harrisj09.irc.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.harrisj09.irc.client.client.ClientController;
import com.github.harrisj09.irc.client.client.ClientModel;
import com.github.harrisj09.irc.client.client.ClientView;
import com.github.harrisj09.irc.client.screen.ScreenController;
import com.github.harrisj09.irc.client.start.StartController;
import com.github.harrisj09.irc.client.start.StartView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


/**
 * Entry point of application
 *
 * @author John Harris
 */
public class Main extends Application {
    /*
    Use this to create CSS Layout
    String css = this.getClass().getClassLoader().getResource("style.css").toExternalForm();
    scene.getStylesheets().add(css);
    */
    //Logger logger = LoggerFactory.getLogger("com.github.harrisj09.irc.client.Main");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new Pane());
        primaryStage.setScene(scene);

        ClientModel model = new ClientModel();
        ClientController controller = new ClientController(model);
        ClientView view = new ClientView(controller, primaryStage);

        StartView startView = new StartView(new StartController(), primaryStage);

        ScreenController screenController = new ScreenController(scene);
        screenController.addScreen("start", startView.createLayout());
        screenController.activate("start");

        primaryStage.setHeight(600);
        primaryStage.setWidth(600);
        EventHandler<MouseEvent> eventHandler = e -> {
            try {
                if (controller.canConnect(startView.getIp().getText(), startView.getPort().getText(), startView.getUsername().getText())) {
                    controller.connectToServer(startView.getIp().getText(), startView.getPort().getText(), startView.getUsername().getText());
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