package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new PlantManager("Manager Ivan"));
        employees.add(new FactoryDirector("Director Peter"));
        employees.add(new Turner("Turner Sergey"));
        employees.add(new Locksmith("Locksmith Alexey"));

        for (Employee employee : employees) {
            employee.print();
            System.out.println(employee);
        }
    }
}