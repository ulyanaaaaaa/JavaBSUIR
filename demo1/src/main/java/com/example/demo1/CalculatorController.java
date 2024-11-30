package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class CalculatorController {

    @FXML
    private TextField expressionField;

    @FXML
    private ListView<String> historyListView;

    private CalculationDao calculationDao;

    public CalculatorController() {
        try {
            calculationDao = new CalculationDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCalculate() {
        String expression = expressionField.getText();
        String result = calculateOnServer(expression);
        Calculation calculation = new Calculation();
        calculation.setExpression(expression);
        calculation.setResult(result);

        try {
            calculationDao.addCalculation(calculation);
            updateHistory();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        expressionField.setText(result);
    }

    private String calculateOnServer(String expression) {
        try (Socket socket = new Socket("localhost", 12345);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            output.println(expression);
            return input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    @FXML
    private void initialize() {
        updateHistory();
    }

    private void updateHistory() {
        historyListView.getItems().clear();
        try {
            for (Calculation calc : calculationDao.getAllCalculations()) {
                historyListView.getItems().add(calc.getExpression() + " = " + calc.getResult());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
