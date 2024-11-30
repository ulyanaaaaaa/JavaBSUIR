package com.example.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HelloController {

    @FXML private TextField numberField1;
    @FXML private TextField numberField2;
    @FXML private ComboBox<String> operationBox;
    @FXML private Label responseLabel;
    @FXML private Button sendButton;
    @FXML private ToggleButton toggleSendButton;

    private Socket clientSocket;
    private ObjectOutputStream coos;
    private ObjectInputStream cois;

    public void initialize() {
        try {
            clientSocket = new Socket("127.0.0.1", 2525);
            coos = new ObjectOutputStream(clientSocket.getOutputStream());
            cois = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML protected void onSendButtonClick() {
        try {
            String number1 = numberField1.getText();
            String number2 = numberField2.getText();
            String operation = operationBox.getValue();
            String clientMessage = number1 + operation + number2;
            SerializeData data = new SerializeData(clientMessage);
            coos.writeObject(data);
            coos.flush();
            SerializeData serverResponse = (SerializeData) cois.readObject();
            responseLabel.setText("Server: " + serverResponse.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML protected void onToggleSendButton() {
        sendButton.setVisible(!sendButton.isVisible());
    }

    public void closeConnections() {
        try {
            coos.close();
            cois.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
