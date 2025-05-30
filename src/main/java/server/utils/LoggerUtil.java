package server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
    private static final Logger logger = LoggerFactory.getLogger("Server");

    public static void logError(String message, Throwable e) {
        logger.error(message, e);
    }

    public static void logInfo(String message) {
        logger.info(message);
    }
}