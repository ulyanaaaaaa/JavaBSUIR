package TCP;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("server connecting....");
            Socket clientSocket = new Socket("127.0.0.1", 2525);
            System.out.println("connection established....");

            ObjectOutputStream coos = new ObjectOutputStream(clientSocket.getOutputStream()); //для отправки объектов на сервер
            ObjectInputStream cois = new ObjectInputStream(clientSocket.getInputStream());

            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter a mathematical expression to send to server \n\t('quit' − programme terminate)");

            String clientMessage = stdin.readLine();
            SerializeData data = new SerializeData(clientMessage);
            System.out.println("you've entered: " + data.getResult());

            while (!data.getResult().equals("quit")) {
                coos.writeObject(data);
                coos.flush();

                SerializeData serverResponse = (SerializeData) cois.readObject();
                System.out.println("~server~: " + serverResponse.getResult());
                System.out.println("---------------------------");

                data = new SerializeData(stdin.readLine());
                System.out.println("you've entered: " + data.getResult());
            }

            coos.close();
            cois.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

