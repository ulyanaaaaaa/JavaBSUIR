package UDP;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(9876);
            System.out.println("Server started...");
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length); // Пакет для приема данных
                socket.receive(packet);
                new Thread(new ClientHandler(socket, packet)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}

class ClientHandler implements Runnable {
    private DatagramSocket socket;
    private DatagramPacket packet;

    public ClientHandler(DatagramSocket socket, DatagramPacket packet) {
        this.socket = socket;
        this.packet = packet;
    }

    @Override
    public void run() {
        try {
            String message = new String(packet.getData(), 0, packet.getLength()); // Преобразуем данные в строку
            System.out.println("Received message: " + message);
            String[] values = message.split(";");
            double x = Double.parseDouble(values[0]);
            double y = Double.parseDouble(values[1]);
            double z = Double.parseDouble(values[2]);
            double result = calculateFunction(x, y, z);
            String resultStr = String.valueOf(result);
            byte[] buffer = resultStr.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort()); // Создаем пакет для отправки ответа
            socket.send(responsePacket); // Отправляем ответный пакет
            System.out.println("Sent result: " + resultStr);
            saveToFile(x, y, z, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double calculateFunction(double x, double y, double z) {
        return Math.log(Math.pow(y, -Math.sqrt(Math.abs(x)))) * (x - y / 2) + Math.pow(Math.sin(Math.atan(z)), 2) + Math.exp(x + y);
    }

    private void saveToFile(double x, double y, double z, double result) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("results.txt", true)))) {
            out.printf("x: %f, y: %f, z: %f, result: %f%n", x, y, z, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
