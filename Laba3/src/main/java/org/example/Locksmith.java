package org.example;

public class Locksmith extends Worker {
    Locksmith(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Locksmith{name='" + name + "'}";
    }
}
