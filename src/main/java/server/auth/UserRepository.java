package server.auth;

import java.util.*;

public interface UserRepository {
    Optional<User> findByLogin(String login);
    boolean saveUser(User user);
}