module irc {
    requires javafx.controls;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    //requires ch.qos.logback.classic;
    //requires org.slf4j;
    requires static lombok;
    opens com.github.harrisj09.irc.client;
}