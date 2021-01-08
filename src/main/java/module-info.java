module irc {
    requires javafx.controls;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires static lombok;
    opens com.github.harrisj09.irc.client;
}