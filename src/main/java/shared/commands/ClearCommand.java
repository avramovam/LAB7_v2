package shared.commands;

import java.io.Serializable;

public class ClearCommand implements Command, Serializable {
    @Override
    public String execute(String userLogin) {
        try {
            // Логика очистки коллекции
            /* collection.clear(userLogin); */
            return "Collection cleared for user " + userLogin;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}