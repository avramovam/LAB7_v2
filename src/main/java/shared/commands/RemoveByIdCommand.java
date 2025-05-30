package shared.commands;

import java.io.Serializable;

public class RemoveByIdCommand implements Command, Serializable {
    private final long id;

    public RemoveByIdCommand(long id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive");
        this.id = id;
    }

    @Override
    public String execute(String userLogin) {
        try {
            // Логика удаления из MovieCollection
            boolean removed = /* collection.removeById(id, userLogin) != null */ true;
            return removed ? "Movie removed" : "No movie found with this ID or no permissions";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}