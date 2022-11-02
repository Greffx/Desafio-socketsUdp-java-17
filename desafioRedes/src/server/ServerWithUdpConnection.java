package server;

import java.net.*;
import java.util.*;

public class ServerWithUdpConnection {
    public static void main(String[] args) throws Exception {

        DatagramSocket serverSocket = new DatagramSocket(6969);
        System.out.println("Server online.");

        byte[] messageReceived = new byte[1024];

        DatagramPacket packetReceived = new DatagramPacket(messageReceived, messageReceived.length);
        serverSocket.receive(packetReceived);

        String textReceived = new String(packetReceived.getData());
        byte[] byteArrayTextReceived = textReceived.getBytes();

        byte checksum = 0;
        int finalChecksum = 0;

        for (int i = 0; i < byteArrayTextReceived.length; i++) {
            if (byteArrayTextReceived[i] != 0x03) {
                checksum ^= byteArrayTextReceived[i];
            }

            if (byteArrayTextReceived[i] == 0x03) {
                finalChecksum = checksum ^ 0x03;
            }
        }

        for (int i = 0; i < byteArrayTextReceived.length; i++) {

            textReceived = textReceived.replace("\0", ""); //usei isto para trocar todos os bytes que forem 0, ou 2, ou 3 q seria o stx e etx, para nada, assim deixando a mensagem mais clean
            textReceived = textReceived.replace("\2", "");
            textReceived = textReceived.replace("\3", "");

            if (byteArrayTextReceived[i] == finalChecksum) {
                System.out.println("test ok");
            } else {
                System.out.println("Message's wrong, try again.");
            }
        }

        byte[] messageOfChecksumOk;
        String message = "Data received";
        messageOfChecksumOk = message.getBytes();
        InetAddress ip = packetReceived.getAddress();
        int clientPort = packetReceived.getPort();

        DatagramPacket packetToSend = new DatagramPacket(messageOfChecksumOk, messageOfChecksumOk.length, ip, clientPort);
        serverSocket.send(packetToSend);

        serverSocket.close();
    }
}
