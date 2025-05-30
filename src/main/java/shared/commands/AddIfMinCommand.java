package shared.commands;

import shared.models.Movie;
import java.io.Serializable;

public class AddIfMinCommand implements Command, Serializable {
    private final Movie movie;

    public AddIfMinCommand(Movie movie) {
        if (movie == null) throw new IllegalArgumentException("Movie cannot be null");
        this.movie = movie;
    }

    @Override
    public String execute(String userLogin) {
        try {
            movie.setOwnerLogin(userLogin);
            // Логика проверки и добавления
            /*
            Movie minMovie = collection.getMinByCoordinates();
            if (minMovie == null || movie.compareTo(minMovie) < 0) {
                collection.addMovie(movie);
                return "Movie added (ID: " + movie.getId() + ")";
            }
            */
            return "Movie not added - not the smallest";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}