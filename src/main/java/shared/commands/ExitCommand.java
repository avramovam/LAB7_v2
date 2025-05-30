package shared.commands;

import java.io.Serializable;

public class ExitCommand implements Command, Serializable {
    @Override
    public String execute(String userLogin) {
        System.exit(0);
        return "Завершение работы";
    }
}