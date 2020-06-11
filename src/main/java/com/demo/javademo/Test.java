package com.demo.javademo;

class Base {
    private int i = 2;

    public Base() {
        this.display();
    }

    public void display() {
        System.out.println(i);
    }
}

class Derived extends Base {
    private int i = 22;

    public Derived() {
        i = 222;
    }

    public Derived(int i) {
        this.i = i;
    }

    public void display() {
        System.out.println(i);
    }
}

public class Test {
    public static void main(String[] args) {
        new Derived();
    }
}
