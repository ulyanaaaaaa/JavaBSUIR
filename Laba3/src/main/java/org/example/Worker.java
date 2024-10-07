package org.example;

import java.util.Objects;

abstract class Worker implements Employee {
    String name;

    Worker(String name) {
        this.name = name;
    }

    @Override
    public void print() {
        System.out.println("Name: " + name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Worker worker = (Worker) obj;
        return Objects.equals(name, worker.name);
    }

    @Override
    public String toString() {
        return "Worker{name='" + name + "'}";
    }
}
