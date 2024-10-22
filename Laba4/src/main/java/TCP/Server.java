package TCP;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(2525);
            System.out.println("Server started...");

            while (true) {
                Socket clientAccepted = serverSocket.accept(); // Принимаем клиентское подключение
                new Thread(new ClientHandler(clientAccepted)).start(); // Запускаем новый поток для обработки клиента
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        ObjectInputStream sois = null;
        ObjectOutputStream soos = null;
        try {
            System.out.println("Connection established with client: " + clientSocket.getInetAddress());

            soos = new ObjectOutputStream(clientSocket.getOutputStream());
            sois = new ObjectInputStream(clientSocket.getInputStream());

            SerializeData data = (SerializeData) sois.readObject();
            while (!data.getResult().equals("quit")) {
                System.out.println("Message received: '" + data.getResult() + "'");

                String result = evaluateExpression(data.getResult());
                SerializeData responseData = new SerializeData(result);
                soos.writeObject(responseData);

                data = (SerializeData) sois.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (sois != null) sois.close();
                if (soos != null) soos.close();
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String evaluateExpression(String expression) {
        try {
            String trimmed = expression.replaceAll("\\s", "");
            if (trimmed.matches("-?\\d+(\\.\\d+)?[+\\-*/]-?\\d+(\\.\\d+)?")) {
                char operator = ' ';
                for (char c : trimmed.toCharArray()) {
                    if (c == '+' || c == '-' || c == '*' || c == '/') {
                        operator = c;
                        break;
                    }
                }
                String[] operands = trimmed.split("[+\\-*/]");
                if (operands.length != 2) {
                    return "Invalid expression";
                }
                double operand1 = Double.parseDouble(operands[0]);
                double operand2 = Double.parseDouble(operands[1]);
                double result;
                switch (operator) {
                    case '+':
                        result = operand1 + operand2;
                        break;
                    case '-':
                        result = operand1 - operand2;
                        break;
                    case '*':
                        result = operand1 * operand2;
                        break;
                    case '/':
                        if (operand2 == 0) {
                            return "Division by zero";
                        }
                        result = operand1 / operand2;
                        break;
                    default:
                        return "Invalid operator";
                }
                return String.valueOf(result);
            } else {
                return "Invalid expression";
            }
        } catch (Exception e) {
            return "Invalid expression";
        }
    }
}

