package shared.commands;

import shared.models.Movie;
import java.io.Serializable;

public class RemoveGreaterCommand implements Command, Serializable {
    private final Movie movie;

    public RemoveGreaterCommand(Movie movie) {
        if (movie == null) throw new IllegalArgumentException("Movie cannot be null");
        this.movie = movie;
    }

    @Override
    public String execute(String userLogin) {
        try {
            // Логика удаления
            /*
            List<Movie> toRemove = collection.getAllMovies().stream()
                .filter(m -> m.compareTo(movie) > 0 && m.getOwnerLogin().equals(userLogin))
                .collect(Collectors.toList());
            toRemove.forEach(collection::removeMovie);
            */
            return "Removed " + /*toRemove.size()*/ 0 + " elements";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}