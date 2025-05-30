package shared.commands;

import java.io.Serializable;

public class MinByCoordinatesCommand implements Command, Serializable {
    @Override
    public String execute(String userLogin) {
        try {
            // Логика поиска
            /*
            Movie minMovie = collection.getMinByCoordinates();
            return minMovie != null ? minMovie.toString() : "Collection is empty";
            */
            return "Not implemented yet";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}