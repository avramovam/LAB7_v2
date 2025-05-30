package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/studs";
    private static final String USER = "postgres";
    private static final String PASSWORD = "your_password"; // Замените на ваш пароль

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    // Проверка, что таблицы существуют (вызывать при старте сервера)
    public void initializeDatabase() throws SQLException {
        try (Connection conn = getConnection()) {
            conn.createStatement().executeUpdate(
                    "CREATE SEQUENCE IF NOT EXISTS movies_id_seq START 1;"
            );
            conn.createStatement().executeUpdate(
                    "CREATE TABLE IF NOT EXISTS users (" +
                            "login TEXT PRIMARY KEY, " +
                            "password_hash TEXT NOT NULL);"
            );
            conn.createStatement().executeUpdate(
                    "CREATE TABLE IF NOT EXISTS movies (" +
                            "id BIGINT PRIMARY KEY DEFAULT nextval('movies_id_seq'), " +
                            "name TEXT NOT NULL, " +
                            "coordinates_x INT NOT NULL, " +
                            "coordinates_y FLOAT NOT NULL CHECK (coordinates_y > -177), " +
                            "creation_date TIMESTAMP WITH TIME ZONE NOT NULL, " +
                            "oscars_count INT CHECK (oscars_count > 0), " +
                            "length INT CHECK (length > 0), " +
                            "genre TEXT CHECK (genre IN ('DRAMA', 'COMEDY', 'ADVENTURE', 'HORROR', 'FANTASY')), " +
                            "mpaa_rating TEXT CHECK (mpaa_rating IN ('G', 'R', 'NC_17')), " +
                            "director_name TEXT NOT NULL, " +
                            "director_passport_id TEXT NOT NULL UNIQUE, " +
                            "location_x FLOAT NOT NULL, " +
                            "location_y BIGINT NOT NULL, " +
                            "location_name TEXT, " +
                            "owner_login TEXT NOT NULL REFERENCES users(login));"
            );
        }
    }
}