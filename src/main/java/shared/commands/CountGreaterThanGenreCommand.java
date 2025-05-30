package shared.commands;

import shared.models.MovieGenre;
import java.io.Serializable;

public class CountGreaterThanGenreCommand implements Command, Serializable {
    private final MovieGenre genre;

    public CountGreaterThanGenreCommand(MovieGenre genre) {
        if (genre == null) throw new IllegalArgumentException("Genre cannot be null");
        this.genre = genre;
    }

    @Override
    public String execute(String userLogin) {
        try {
            // Логика подсчета
            /*
            int count = collection.countGreaterThanGenre(genre);
            */
            return "Count: " + /*count*/ 0;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}