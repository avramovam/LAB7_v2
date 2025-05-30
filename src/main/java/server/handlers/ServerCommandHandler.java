package server.handlers;

import shared.commands.Command;
import server.collection.MovieCollection;
import server.auth.AuthManager;

public class ServerCommandHandler {
    private final MovieCollection collection;
    private final AuthManager authManager;

    public ServerCommandHandler(MovieCollection collection, AuthManager authManager) {
        this.collection = collection;
        this.authManager = authManager;
    }

    public String handleCommand(Command command, String userLogin) {
        if (!authManager.isAuthenticated(userLogin)) {
            return "Authentication required";
        }
        return command.execute(userLogin);
    }
}