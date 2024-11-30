package com.example.demo1;

public class Formula {
    private int id;
    private String name;
    private String expression;

    public Formula() { }

    public Formula(int id, String name, String expression) {
        this.id = id;
        this.name = name;
        this.expression = expression;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}


