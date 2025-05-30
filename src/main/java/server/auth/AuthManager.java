package server.auth;

import server.database.DatabaseManager;
import server.utils.HashUtil;
import java.sql.*;

public class AuthManager {
    private final DatabaseManager dbManager;

    public AuthManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    // Регистрация нового пользователя
    public boolean register(String login, String password) throws SQLException {
        String hash = HashUtil.hashSHA384(password);
        String sql = "INSERT INTO users (login, password_hash) VALUES (?, ?)";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, hash);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) { // Ошибка уникальности (логин уже занят)
                return false;
            }
            throw e;
        }
    }

    // Проверка логина и пароля
    public boolean authenticate(String login, String password) throws SQLException {
        String sql = "SELECT password_hash FROM users WHERE login = ?";

        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password_hash");
                String inputHash = HashUtil.hashSHA384(password);
                return storedHash.equals(inputHash);
            }
            return false; // Пользователь не найден
        }
    }
}