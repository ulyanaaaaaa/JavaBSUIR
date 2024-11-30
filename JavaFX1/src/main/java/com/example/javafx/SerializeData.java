package com.example.javafx;

import java.io.Serializable;

public class SerializeData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String result;

    public SerializeData(String result){
        this.result = result;
    }

    public String getResult(){
        return result;
    }
}
