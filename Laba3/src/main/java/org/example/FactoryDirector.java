package org.example;

class FactoryDirector extends Staff {
    FactoryDirector(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Factory Director{name='" + name + "'}";
    }
}
