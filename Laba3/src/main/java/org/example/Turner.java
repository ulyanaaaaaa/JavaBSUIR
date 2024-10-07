package org.example;

class Turner extends Worker {
    Turner(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Turner{name='" + name + "'}";
    }
}
