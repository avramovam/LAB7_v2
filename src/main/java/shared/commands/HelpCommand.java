package shared.commands;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;

public class HelpCommand implements Command, Serializable {
    @Override
    public String execute(String userLogin) {
        return Arrays.stream(CommandType.values())
                .map(type -> String.format("%-20s - %s",
                        type.name().toLowerCase(),
                        getDescription(type)))
                .collect(Collectors.joining("\n"));
    }

    private String getDescription(CommandType type) {
        switch (type) {
            case HELP: return "вывести справку по командам";
            case INFO: return "вывести информацию о коллекции";
            case SHOW: return "показать все элементы";
            // ... остальные описания
            default: return "нет описания";
        }
    }
}