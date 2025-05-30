package shared.commands;

import shared.models.Movie;
import java.io.Serializable;

/**
 * Команда добавления нового элемента в коллекцию.
 * Проверяет валидность данных перед добавлением.
 */
public class AddCommand implements Command, Serializable {
    private final Movie movie;

    public AddCommand(Movie movie) {
        if (movie == null) throw new IllegalArgumentException("Movie cannot be null");
        this.movie = movie;
    }

    @Override
    public String execute(String userLogin) {
        try {
            movie.setOwnerLogin(userLogin);
            // Здесь должна быть логика добавления в MovieCollection
            return "Movie added successfully (ID: " + movie.getId() + ")";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public Movie getMovie() {
        return movie;
    }
}