import server.core.ApplicationContext;
import server.network.ServerNetworkManager;
import server.utils.LoggerUtil;

public class ServerMain {
    public static void main(String[] args) {
        try {
            ApplicationContext context = new ApplicationContext();
            ServerNetworkManager server = new ServerNetworkManager(
                    12345,
                    context.getCollection(),
                    context.getAuthManager()
            );
            server.start();
        } catch (Exception e) {
            LoggerUtil.logError("Server failed", e);
        }
    }
}