package com.example.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HelloController {

    @FXML private TextField inputField;
    @FXML private Label responseLabel;
    @FXML private Button sendButton;
    private Socket clientSocket;
    private ObjectOutputStream coos;
    private ObjectInputStream cois;

    public void initialize() {
        try {
            clientSocket = new Socket("127.0.0.1", 2525);
            coos = new ObjectOutputStream(clientSocket.getOutputStream());
            cois = new ObjectInputStream(clientSocket.getInputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML protected void onSendButtonClick() {
        try {
            String clientMessage = inputField.getText();
            SerializeData data = new SerializeData(clientMessage);
            coos.writeObject(data);
            coos.flush();
            SerializeData serverResponse = (SerializeData) cois.readObject();
            responseLabel.setText("Server: " + serverResponse.getResult());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnections() {
        try {
            coos.close();
            cois.close();
            clientSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}