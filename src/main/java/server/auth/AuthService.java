package server.auth;

import java.util.*;
import java.security.*;
import java.time.*;

public class AuthService {
    private final Map<String, AuthSession> sessions = new ConcurrentHashMap<>();
    private final UserRepository userRepository;
    private final SecureRandom random = new SecureRandom();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<String> authenticate(String login, String password) {
        return userRepository.findByLogin(login)
                .filter(user -> checkPassword(password, user.getPasswordHash()))
                .map(user -> {
                    String token = generateToken();
                    sessions.put(token, new AuthSession(login, Instant.now().plus(Duration.ofHours(1))));
                    return token;
                });
    }

    public boolean validateToken(String token) {
        return Optional.ofNullable(sessions.get(token))
                .filter(session -> session.getExpires().isAfter(Instant.now()))
                .isPresent();
    }

    // ... другие методы
}