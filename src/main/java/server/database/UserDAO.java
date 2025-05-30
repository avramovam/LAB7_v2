package server.database;

import server.auth.User;
import java.sql.*;

/**
 * DAO для работы с таблицей пользователей.
 */
public class UserDAO {
    private final DatabaseManager dbManager;

    public UserDAO(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean registerUser(String login, String passwordHash) throws SQLException {
        String sql = "INSERT INTO users (login, password_hash) VALUES (?, ?)";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, passwordHash);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean validateUser(String login, String passwordHash) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE login = ? AND password_hash = ?";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, passwordHash);
            return stmt.executeQuery().next();
        }
    }
}