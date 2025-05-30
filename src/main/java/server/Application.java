package server;

import server.core.*;
import server.network.*;

public class Application {
    public static void main(String[] args) {
        try {
            ApplicationContext context = new ApplicationContext();
            Server server = new Server(12345, context);
            server.start();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                server.stop();
                context.shutdown();
            }));
        } catch (Exception e) {
            System.err.println("Failed to start server: " + e.getMessage());
        }
    }
}