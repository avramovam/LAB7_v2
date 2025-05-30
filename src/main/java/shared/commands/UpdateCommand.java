package shared.commands;

import shared.models.Movie;
import java.io.Serializable;

public class UpdateCommand implements Command, Serializable {
    private final long id;
    private final Movie updatedMovie;

    public UpdateCommand(long id, Movie updatedMovie) {
        if (updatedMovie == null) throw new IllegalArgumentException("Movie cannot be null");
        this.id = id;
        this.updatedMovie = updatedMovie;
    }

    @Override
    public String execute(String userLogin) {
        try {
            // Логика обновления в MovieCollection
            boolean success = /* collection.update(id, updatedMovie, userLogin) */ true;
            return success ? "Movie updated successfully" : "Failed to update (not found or no permissions)";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}