package UDP;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите значение x:");
            double x = Double.parseDouble(reader.readLine());
            System.out.println("Введите значение y:");
            double y = Double.parseDouble(reader.readLine());
            System.out.println("Введите значение z:");
            double z = Double.parseDouble(reader.readLine());

            DataPacket dataPacket = new DataPacket(x, y, z);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(dataPacket);
            byte[] buffer = baos.toByteArray();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 9876);
            socket.send(packet);

            buffer = new byte[1024];
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String result = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}


