package server.network;

import server.utils.LoggerUtil;

import java.net.*;
import java.util.concurrent.*;

/**
 * Отправляет ответы клиентам через пул потоков.
 */
public class ResponseSender {
    private static final ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void send(DatagramPacket packet) {
        pool.submit(() -> {
            try (DatagramSocket socket = new DatagramSocket()) {
                socket.send(packet);
            } catch (Exception e) {
                LoggerUtil.logError("Failed to send response", e);
            }
        });
    }

    public static void shutdown() {
        pool.shutdown();
    }
}