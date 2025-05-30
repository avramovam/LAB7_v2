package server.utils;

import shared.models.Movie;

public class MovieValidator {
    public static void validate(Movie movie) throws IllegalArgumentException {
        if (movie == null) throw new IllegalArgumentException("Movie cannot be null");
        if (movie.getName() == null || movie.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Movie name cannot be empty");
        }
        if (movie.getCoordinates() == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }
        if (movie.getDirector() == null) {
            throw new IllegalArgumentException("Director cannot be null");
        }
        // Дополнительные проверки...
    }
}