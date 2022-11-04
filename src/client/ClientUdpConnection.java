package client;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientUdpConnection {
    public static void main(String[] args) throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket();
        Scanner sc = new Scanner(System.in);
        System.out.print("Message: ");
        String text;

        if (sc.hasNext()) {
            text = sc.nextLine();
        } else {
            throw new IllegalArgumentException("Wrong type, try again.");
        }

        String textWithParam = "\2" + text + "\3";
        byte[] byteArrayWithMessageAndPlusPosition = new byte[textWithParam.length() + 1];
        byte sumMessage = 0;

        byte[] byteArrayWithMessage = textWithParam.getBytes();

        for (byte b : byteArrayWithMessage) { //for each b byte in byteArray
            sumMessage ^= b;
        }

        System.out.println(sumMessage);

        System.arraycopy(byteArrayWithMessage, 0, byteArrayWithMessageAndPlusPosition, 0, byteArrayWithMessage.length); //o array fonte, tamanho inicial do array fonte, array destinário, tamanho do array dest, e a qtd de elementos a ser copiado 
        byteArrayWithMessageAndPlusPosition[textWithParam.length()] = sumMessage;

        InetAddress ip = InetAddress.getByName("127.0.0.1");
        DatagramPacket packetToSend = new DatagramPacket(byteArrayWithMessageAndPlusPosition, byteArrayWithMessageAndPlusPosition.length, ip, 6699);
        datagramSocket.send(packetToSend);

        byte[] messageReceivedFromServer = new byte[13];
        DatagramPacket packetReceived = new DatagramPacket(messageReceivedFromServer, messageReceivedFromServer.length);
        datagramSocket.receive(packetReceived);

        String messageIntoString = new String(packetReceived.getData());
        System.out.println("Server: " + messageIntoString);
        datagramSocket.close();
        sc.close();
    }
}