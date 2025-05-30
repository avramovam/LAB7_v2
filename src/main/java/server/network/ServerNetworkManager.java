package server.network;

import server.auth.AuthManager;
import server.collection.MovieCollection;
import server.utils.LoggerUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerNetworkManager implements AutoCloseable {
    private final DatagramSocket socket;
    private final ExecutorService requestPool;
    private final MovieCollection collection;
    private final AuthManager authManager;

    public ServerNetworkManager(int port, MovieCollection collection, AuthManager authManager)
            throws IOException {
        this.socket = new DatagramSocket(port);
        this.requestPool = Executors.newCachedThreadPool();
        this.collection = collection;
        this.authManager = authManager;
    }

    public void start() {
        while (!socket.isClosed()) {
            try {
                byte[] buffer = new byte[4096];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                requestPool.submit(new RequestProcessor(packet, collection, authManager));
            } catch (IOException e) {
                if (!socket.isClosed()) {
                    LoggerUtil.logError("Network error", e);
                }
            }
        }
    }

    @Override
    public void close() {
        socket.close();
        requestPool.shutdown();
    }
}