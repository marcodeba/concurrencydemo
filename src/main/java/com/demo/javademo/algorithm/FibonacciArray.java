package com.demo.javademo.algorithm;

public class FibonacciArray {
    public static void main(String[] args) {
        System.out.println(getFibonacci2(10));
    }

    //生成斐波那契数
    public static int getFibonacci2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int previous = 1, result = 1;
        for (int i = 3; i <= n; i++) {
            result = previous + result;
            previous = result - previous;
        }
        return result;
    }
}
