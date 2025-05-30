package shared.commands;

import shared.models.Movie;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ShowCommand implements Command, Serializable {
    @Override
    public String execute(String userLogin) {
        try {
            // Получение всех фильмов
            List<Movie> movies = /* collection.getAllMovies() */ List.of();
            if (movies.isEmpty()) return "Collection is empty";

            return movies.stream()
                    .map(Movie::toString)
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}