package com.github.harrisj09.irc.client;

import com.github.harrisj09.irc.client.client.ClientController;
import com.github.harrisj09.irc.client.client.ClientModel;
import com.github.harrisj09.irc.client.client.ClientView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
/*        StartModel model = new StartModel();
        StartController controller = new StartController(model);
        StartView view = new StartView(controller, primaryStage);*/
        TextField ip = new TextField();
        TextField port = new TextField();
        TextField userName = new TextField();
        HBox ipBox = new HBox(new Text("IP"), ip);
        HBox portBox = new HBox(new Text("Port"), port);
        HBox userNameBox = new HBox(new Text("UserName"), userName);
        Button button = new Button("Submit");
        EventHandler<MouseEvent> eventHandler = e -> {
            Scene scene = null;
            try {
                scene = canConnect(ip.getText(), port.getText(), userName.getText(), primaryStage);
            } catch (URISyntaxException | IOException | InterruptedException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
            }
            if (scene != null) {
                primaryStage.setScene(scene);
                primaryStage.show();
                primaryStage.setMaximized(true);
            }
        };
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        Scene scene = new Scene(new VBox(ipBox, portBox, userNameBox, button), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Scene canConnect(String ip, String port, String username, Stage primaryStage) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest build = HttpRequest.newBuilder().GET().uri(new URI("http://localhost:8080/serverconnect/" + username)).build();
        HttpResponse<String> send = HttpClient.newBuilder()
                .build()
                .send(build, HttpResponse.BodyHandlers.ofString());
        System.out.println(send.body());
        System.out.print("IP: " + ip.length() + "\nPort: " + port.length() + "\nUsername: " + username.length());
        if (ip.length() > 0 && port.length() > 0 && username.length() > 0) {
            ClientModel model = new ClientModel();
            ClientController controller = new ClientController(model);
            ClientView view = new ClientView(controller, primaryStage);
            return new Scene(view.getLayout(), 700, 700);
        }
        return null;
    }
}