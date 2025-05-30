package shared.models;

import java.io.Serializable;

/**
 * Возрастные рейтинги MPAA.
 */
public enum MpaaRating implements Serializable {
    G,      // Для всех возрастов
    R,      // До 17 с родителями
    NC_17;  // Только с 18

    /**
     * Возвращает список всех рейтингов в строке.
     */
    public static String listRatings() {
        StringBuilder sb = new StringBuilder();
        for (MpaaRating rating : values()) {
            sb.append(rating.name()).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }
}