package server.network;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPConnection {
    private final DatagramSocket socket;

    public UDPConnection(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void close() {
        socket.close();
    }
}