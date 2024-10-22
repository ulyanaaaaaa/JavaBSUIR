package UDP;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите значение x:");
            String x = reader.readLine();
            System.out.println("Введите значение y:");
            String y = reader.readLine();
            System.out.println("Введите значение z:");
            String z = reader.readLine();
            String message = x + ";" + y + ";" + z;
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 9876);
            socket.send(packet);
            buffer = new byte[1024];
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String result = new String(packet.getData(), 0, packet.getLength()); // Преобразуем данные пакета в строку
            System.out.println("Результат: " + result); // Выводим результат
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}

