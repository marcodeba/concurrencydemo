package com.demo.javademo.binary;

public class BinaryCalcTest {
    public static void main(String[] args) {
        int i = 0B100; // 十进制为4
        // 二进制结果：100000
        // 十进制结果：32 = 4 * (2的3次方)
        System.out.println("二进制结果：" + Integer.toBinaryString(i << 3));
        System.out.println("十进制结果：" + (i << 3));

        System.out.println("二进制结果：" + Integer.toBinaryString(i >> 1));
        System.out.println("十进制结果：" + (i >> 1));
    }
}
