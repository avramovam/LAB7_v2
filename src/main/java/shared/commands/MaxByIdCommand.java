package shared.commands;

import java.io.Serializable;

public class MaxByIdCommand implements Command, Serializable {
    @Override
    public String execute(String userLogin) {
        try {
            // Логика поиска
            /*
            Movie maxMovie = collection.getMaxById();
            return maxMovie != null ? maxMovie.toString() : "Collection is empty";
            */
            return "Not implemented yet";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}