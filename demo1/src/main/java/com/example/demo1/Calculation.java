package com.example.demo1;

import java.sql.Timestamp;

public class Calculation {
    private int id;
    private String expression;
    private String result;
    private Timestamp timestamp;

    public Calculation() { }

    public Calculation(int id, String expression, String result, Timestamp timestamp) {
        this.id = id;
        this.expression = expression;
        this.result = result;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}

