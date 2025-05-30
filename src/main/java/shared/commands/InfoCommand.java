package shared.commands;

import server.collection.MovieCollection;
import java.io.Serializable;
import java.time.LocalDateTime;

public class InfoCommand implements Command, Serializable {
    @Override
    public String execute(String userLogin) {
        try {
            MovieCollection collection = MovieCollection.getInstance();
            return String.format(
                    "Тип коллекции: %s\n" +
                            "Дата инициализации: %s\n" +
                            "Количество элементов: %d",
                    collection.getClass().getSimpleName(),
                    LocalDateTime.now(),
                    collection.size()
            );
        } catch (Exception e) {
            return "Ошибка: " + e.getMessage();
        }
    }
}