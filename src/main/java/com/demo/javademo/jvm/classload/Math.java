package com.demo.javademo.jvm.classload;

public class Math {
    public static final int initData = 1;
    public int compute() {
        int a = 1, b = 2;
        int c = (a + b) * 10;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        math.compute();
    }
}
