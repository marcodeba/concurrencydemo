package com.demo.javademo.binary;

public class BinaryTest {

    public static void main(String[] args) {
//        //二进制
//        int i = 0B101;
//        System.out.println(i); //5
//        System.out.println(Integer.toBinaryString(i));
//        //八进制
//        i = 0101;
//        System.out.println(i); //65
//        System.out.println(Integer.toBinaryString(i));
//        //十进制
//        i = 101;
//        System.out.println(i); //101
//        System.out.println(Integer.toBinaryString(i));
//        //十六进制
//        i = 0x101;
//        System.out.println(i); //257
//        System.out.println(Integer.toBinaryString(i));

//        int i = 192;
//        System.out.println("---------------------------------");
//        System.out.println("十进制转二进制：" + Integer.toBinaryString(i)); //11000000
//        System.out.println("十进制转八进制：" + Integer.toOctalString(i)); //300
//        System.out.println("十进制转十六进制：" + Integer.toHexString(i)); //c0
//        System.out.println("---------------------------------");
//        // 统一利用的为Integer的valueOf()方法,parseInt方法也是ok的
//        System.out.println("二进制转十进制：" + Integer.valueOf("11000000", 2).toString()); //192
//        System.out.println("八进制转十进制：" + Integer.valueOf("300", 8).toString()); //192
//        System.out.println("十六进制转十进制：" + Integer.valueOf("c0", 16).toString()); //192
//        System.out.println("---------------------------------");

//        Long l = Long.MAX_VALUE;
//        System.out.println(Long.toBinaryString(l));
//        System.out.println(Long.toBinaryString(l).length());
//
//        l = Long.MIN_VALUE;
//        System.out.println(Long.toBinaryString(l));
//        System.out.println(Long.toBinaryString(l).length());
//
//        Integer i = Integer.MAX_VALUE;
//        System.out.println(Integer.toBinaryString(i));
//        System.out.println(Integer.toBinaryString(i).length());
//
//        i = Integer.MIN_VALUE;
//        System.out.println(Integer.toBinaryString(i));
//        System.out.println(Integer.toBinaryString(i).length());
//        char a = '2';
//        System.out.println(a);
//        System.out.println(Integer.toBinaryString(a));
//        System.out.println(Integer.parseInt(Integer.toBinaryString(a), 2));

        char m = 'a' + 'b';
        System.out.println('a');
        System.out.println('b');
        System.out.println(m);
        System.out.println((char) (2 + '0'));
    }
}
