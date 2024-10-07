package org.example;

import java.util.Objects;

abstract class Staff implements Employee {
    String name;

    Staff(String name) {
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
        Staff staff = (Staff) obj;
        return Objects.equals(name, staff.name);
    }

    @Override
    public String toString() {
        return "Staff{name='" + name + "'}";
    }
}
