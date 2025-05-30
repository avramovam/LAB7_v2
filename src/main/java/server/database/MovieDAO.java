package server.database;

import shared.models.Movie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private final DatabaseManager dbManager;

    public MovieDAO(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    // Добавление фильма в БД
    public void saveMovie(Movie movie, String ownerLogin) throws SQLException {
        String sql = "INSERT INTO movies (name, coordinates_x, coordinates_y, creation_date, oscars_count, " +
                "length, genre, mpaa_rating, director_name, director_passport_id, location_x, location_y, " +
                "location_name, owner_login) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, movie.getName());
            stmt.setInt(2, movie.getCoordinates().getX());
            stmt.setFloat(3, movie.getCoordinates().getY());
            stmt.setTimestamp(4, Timestamp.valueOf(movie.getCreationDate().toLocalDateTime()));
            stmt.setObject(5, movie.getOscarsCount(), Types.INTEGER);
            stmt.setInt(6, movie.getLength());
            stmt.setString(7, movie.getGenre() != null ? movie.getGenre().name() : null);
            stmt.setString(8, movie.getMpaaRating() != null ? movie.getMpaaRating().name() : null);
            stmt.setString(9, movie.getDirector().getName());
            stmt.setString(10, movie.getDirector().getPassportID());
            stmt.setFloat(11, movie.getDirector().getLocation().getX());
            stmt.setLong(12, movie.getDirector().getLocation().getY());
            stmt.setString(13, movie.getDirector().getLocation().getName());
            stmt.setString(14, ownerLogin);

            stmt.executeUpdate();

            // Получаем сгенерированный ID
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    movie.setId(rs.getLong(1));
                }
            }
        }
    }

    // Загрузка всех фильмов из БД при старте сервера
    public List<Movie> loadAllMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies";

        try (Connection conn = dbManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Movie movie = new Movie();
                // Заполняем поля movie из ResultSet
                movie.setId(rs.getLong("id"));
                movie.setName(rs.getString("name"));
                // ... остальные поля
                movies.add(movie);
            }
        }
        return movies;
    }
}