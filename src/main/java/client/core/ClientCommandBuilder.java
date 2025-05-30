package client.core;

import shared.commands.*;
import shared.models.Movie;
import shared.models.MovieGenre;

public class ClientCommandBuilder {
    public static Command buildAddCommand(Movie movie) {
        return new AddCommand(movie);
    }

    public static Command buildUpdateCommand(long id, Movie movie) {
        return new UpdateCommand(id, movie);
    }

    // ... аналогичные методы для всех команд

    public static Command buildFromInput(CommandType type, Object... args) {
        return switch (type) {
            case ADD -> buildAddCommand((Movie) args[0]);
            case UPDATE -> buildUpdateCommand((Long) args[0], (Movie) args[1]);
            // ... остальные команды
            default -> throw new IllegalArgumentException("Unknown command type");
        };
    }
}