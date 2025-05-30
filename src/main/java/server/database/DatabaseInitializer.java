package server.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize(DatabaseManager dbManager) throws SQLException {
        try (Connection conn = dbManager.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS users (
                    login TEXT PRIMARY KEY,
                    password_hash TEXT NOT NULL
                )""");

            stmt.executeUpdate("""
                CREATE SEQUENCE IF NOT EXISTS movies_id_seq START 1""");

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS movies (
                    id BIGINT PRIMARY KEY DEFAULT nextval('movies_id_seq'),
                    name TEXT NOT NULL,
                    coordinates_x INT NOT NULL,
                    coordinates_y FLOAT NOT NULL CHECK (coordinates_y > -177),
                    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
                    oscars_count INT CHECK (oscars_count > 0),
                    length INT CHECK (length > 0),
                    genre TEXT CHECK (genre IN ('DRAMA', 'COMEDY', 'ADVENTURE', 'HORROR', 'FANTASY')),
                    mpaa_rating TEXT CHECK (mpaa_rating IN ('G', 'R', 'NC_17')),
                    director_name TEXT NOT NULL,
                    director_passport_id TEXT NOT NULL UNIQUE,
                    location_x FLOAT NOT NULL,
                    location_y BIGINT NOT NULL,
                    location_name TEXT,
                    owner_login TEXT NOT NULL REFERENCES users(login)
                )""");
        }
    }
}