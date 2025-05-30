package server.utils;

import java.util.Properties;
import java.io.FileInputStream;

public class Config {
    private static final Properties props = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        } catch (Exception e) {
            LoggerUtil.logError("Failed to load config", e);
        }
    }

    public static int getServerPort() {
        return Integer.parseInt(props.getProperty("server.port", "12345"));
    }
}