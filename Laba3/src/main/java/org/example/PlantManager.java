package org.example;

public class PlantManager extends Staff {
    PlantManager(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Plant Manager{name='" + name + "'}";
    }
}
