package server.network;

import server.auth.AuthManager;
import shared.commands.Command;
import java.io.*;
import java.net.*;
import server.collection.MovieCollection;

/**
 * Обрабатывает входящие запросы в отдельном потоке.
 */
public class RequestProcessor implements Runnable {
    private final DatagramPacket packet;
    private final MovieCollection collection;
    private final AuthManager authManager;

    public RequestProcessor(DatagramPacket packet, MovieCollection collection, AuthManager authManager) {
        this.packet = packet;
        this.collection = collection;
        this.authManager = authManager;
    }

    @Override
    public void run() {
        try {
            // Десериализация команды
            ByteArrayInputStream byteStream = new ByteArrayInputStream(packet.getData());
            ObjectInputStream objStream = new ObjectInputStream(byteStream);
            Command command = (Command) objStream.readObject();

            // Аутентификация (должна быть в каждом Command)
            String response = command.execute("userLogin"); // Реальный логин из пакета

            // Отправка ответа
            byte[] responseData = response.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(
                    responseData, responseData.length,
                    packet.getAddress(), packet.getPort()
            );
            ResponseSender.send(responsePacket);
        } catch (Exception e) {
            LoggerUtil.logError("Request processing failed", e);
        }
    }
}