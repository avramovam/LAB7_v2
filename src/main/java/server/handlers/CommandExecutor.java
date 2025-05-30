package server.handlers;

import shared.commands.Command;
import server.auth.AuthService;
import java.util.concurrent.*;

public class CommandExecutor {
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final AuthService authService;

    public CommandExecutor(AuthService authService) {
        this.authService = authService;
    }

    public CompletableFuture<String> executeAsync(Command command, String authToken) {
        return CompletableFuture.supplyAsync(() -> {
            if (!authService.validateToken(authToken)) {
                return "Ошибка авторизации";
            }
            return command.execute(authService.getLoginFromToken(authToken));
        }, executor);
    }

    public void shutdown() {
        executor.shutdown();
    }
}