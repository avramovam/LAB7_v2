package client.network;

import shared.commands.Command;
import java.io.*;
import java.net.*;

/**
 * Управляет сетевым взаимодействием клиента с сервером по UDP.
 */
public class ClientNetworkManager implements AutoCloseable {
    private static final int TIMEOUT_MS = 5000;
    private final DatagramSocket socket;
    private final InetAddress serverAddress;
    private final int serverPort;

    public ClientNetworkManager(String host, int port) throws SocketException, UnknownHostException {
        this.socket = new DatagramSocket();
        this.socket.setSoTimeout(TIMEOUT_MS);
        this.serverAddress = InetAddress.getByName(host);
        this.serverPort = port;
    }

    public String sendCommand(Command command) throws IOException {
        // Сериализация команды
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objStream = new ObjectOutputStream(byteStream);
        objStream.writeObject(command);
        byte[] sendData = byteStream.toByteArray();

        // Отправка
        DatagramPacket sendPacket = new DatagramPacket(
                sendData, sendData.length, serverAddress, serverPort
        );
        socket.send(sendPacket);

        // Получение ответа
        byte[] receiveData = new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);
        return new String(receivePacket.getData(), 0, receivePacket.getLength());
    }

    @Override
    public void close() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}