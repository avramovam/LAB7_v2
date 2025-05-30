package server.core;

import server.collection.MovieCollection;
import server.database.DatabaseInitializer;
import server.database.DatabaseManager;
import server.database.MovieDAO;
import server.auth.AuthManager;
import server.handlers.ServerCommandHandler;

public class ApplicationContext {
    private final DatabaseManager dbManager;
    private final MovieDAO movieDAO;
    private final AuthManager authManager;
    private final MovieCollection collection;
    private final ServerCommandHandler commandHandler;

    public ApplicationContext() throws Exception {
        this.dbManager = new DatabaseManager();
        DatabaseInitializer.initialize(dbManager);

        this.movieDAO = new MovieDAO(dbManager);
        this.authManager = new AuthManager(dbManager);

        this.collection = new MovieCollection();
        this.collection.addAll(movieDAO.loadAllMovies());

        this.commandHandler = new ServerCommandHandler(collection, authManager);
    }

    // Геттеры для всех компонентов
    public ServerCommandHandler getCommandHandler() {
        return commandHandler;
    }

    // ... остальные геттеры
}