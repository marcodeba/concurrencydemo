package com.demo.javademo.io;

import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public class Employee implements Serializable {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee() {
    }

    public Employee(String n, double s, int year, int month, int day) {
        name = n;
        salary = s;
        hireDay = LocalDate.of(year, month, day);
    }


    public String toString() {
        return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay
                + "]";
    }

    /**
     * Writes employee data to a print writer
     *
     * @param out the print writer
     */
    public void writeData(PrintWriter out) {
        out.println(name + "|" + salary + "|" + hireDay.getYear() + "|"
                + hireDay.getMonthValue() + "|" + hireDay.getDayOfMonth());
    }

    /**
     * Reads employee data from a buffered reader
     *
     * @param in the scanner
     */
    public void readData(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        name = tokens[0];
        salary = Double.parseDouble(tokens[1]);
        hireDay = LocalDate.of(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
    }
}
