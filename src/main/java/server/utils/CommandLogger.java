package server.utils;

import shared.commands.Command;
import org.slf4j.*;

public class CommandLogger {
    private static final Logger logger = LoggerFactory.getLogger(CommandLogger.class);

    public static void logCommandExecution(Command command, String user, String result) {
        logger.info("User '{}' executed '{}' with result: {}",
                user,
                command.getClass().getSimpleName(),
                result.length() > 100 ? result.substring(0, 100) + "..." : result);
    }

    public static void logError(String user, String commandType, Exception e) {
        logger.error("User '{}' failed to execute '{}': {}", user, commandType, e.getMessage());
    }
}