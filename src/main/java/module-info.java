module irc {
    requires javafx.controls;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires static lombok;
    requires java.logging;
    opens com.github.harrisj09.irc.client;
    opens com.github.harrisj09.irc.client.data.handlers;
    exports com.github.harrisj09.irc.client.data.DTO;
}