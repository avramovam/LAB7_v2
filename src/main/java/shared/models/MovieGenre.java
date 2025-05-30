package shared.models;

import java.io.Serializable;

/**
 * Жанры фильмов.
 */
public enum MovieGenre implements Serializable {
    DRAMA,
    COMEDY,
    ADVENTURE,
    HORROR,
    FANTASY;

    /**
     * Возвращает список всех жанров в строке.
     */
    public static String listGenres() {
        StringBuilder sb = new StringBuilder();
        for (MovieGenre genre : values()) {
            sb.append(genre.name()).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }
}