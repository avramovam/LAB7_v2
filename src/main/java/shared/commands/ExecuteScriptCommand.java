package shared.commands;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ExecuteScriptCommand implements Command, Serializable {
    private final String scriptPath;

    public ExecuteScriptCommand(String scriptPath) {
        if (scriptPath == null || scriptPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Script path cannot be empty");
        }
        this.scriptPath = scriptPath;
    }

    @Override
    public String execute(String userLogin) {
        try {
            List<String> lines = Files.readAllLines(Path.of(scriptPath));
            // Здесь должна быть логика выполнения команд из файла
            return "Executed " + lines.size() + " commands from script";
        } catch (Exception e) {
            return "Script error: " + e.getMessage();
        }
    }
}