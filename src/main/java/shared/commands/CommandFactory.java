package shared.commands;

import shared.models.Movie;

public class CommandFactory {
    public static Command createCommand(CommandType type, Object... args) {
        return switch (type) {
            case ADD -> new AddCommand((Movie) args[0]);
            case UPDATE -> new UpdateCommand((long) args[0], (Movie) args[1]);
            case REMOVE_BY_ID -> new RemoveByIdCommand((long) args[0]);
            case CLEAR -> new ClearCommand();
            case SHOW -> new ShowCommand();
            case EXECUTE_SCRIPT -> new ExecuteScriptCommand((String) args[0]);
            // Остальные команды...
            default -> throw new IllegalArgumentException("Unknown command type");
        };
    }
}