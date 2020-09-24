package com.demo.javademo.algorithm.fenzhi;

import java.util.Arrays;

public class BigNumberMultiply {

    /**
     * 把大数拆分成高位和低位
     * @param src 待分解的数
     * @param st  stc中取数的开始位置
     * @param length
     */
    public static Node divideNumber(Node src, int st, int length) {
        // 分解后得到的数
        Node des = new Node(new int[length], length, st + src.power);
        for (int i = st, j = 0; i < st + length; i++, j++) {
            des.s[j] = src.s[i];
        }
        return des;
    }

    public static void main(String[] args) {
        int num1 = 43579;
        Node a = new Node(num1);
        System.out.println(a);
        // 分治法，把数字一拆为二
        int ma = a.length / 2;
        System.out.println("大数的低位" + divideNumber(a, 0, ma));
        System.out.println("大数的高位" + divideNumber(a, ma, a.length - ma));
    }
}

class Node {
    // 数组倒序存储的最大数
    int[] s;
    // 代表数的位数
    int length;
    // 代表数的次幂
    int power = 0;

    public Node(int[] s, int length, int power) {
        this.s = s;
        this.length = length;
        this.power = power;
    }

    public Node(int number) {
        this.length = String.valueOf(number).length();
        this.s = new int[this.length];
        int i = 0;
        while (number > 0 && i < this.length - 1) {
            this.s[i++] = number % 10;
            number = number / 10;
            if (number < 10) {
                this.s[i] = number;
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "length=" + length +
                ", s=" + Arrays.toString(s) +
                ", power=" + power +
                '}';
    }
}
