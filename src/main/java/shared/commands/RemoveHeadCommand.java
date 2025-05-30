package shared.commands;

import server.collection.MovieCollection;
import shared.models.Movie;

import java.io.Serializable;

public class RemoveHeadCommand implements Command, Serializable {
    @Override
    public String execute(String userLogin) {
        try {
            MovieCollection collection = MovieCollection.getInstance();
            Movie movie = collection.removeHead();
            return movie != null ?
                    "Первый элемент:\n" + movie :
                    "Коллекция пуста";
        } catch (Exception e) {
            return "Ошибка: " + e.getMessage();
        }
    }
}