package shared.commands;

import java.io.Serializable;

/**
 * Интерфейс для всех команд.
 */
public interface Command extends Serializable {
    /**
     * Выполняет команду.
     * @return Результат выполнения (для вывода пользователю).
     */
    String execute(String userLogin);
}