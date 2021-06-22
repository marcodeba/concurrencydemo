package com.demo.javademo.jvm.classload;

public class Test_1 {
    public static void main(String[] args) {
//        int[] arr = new int[3];
//        while (true);
        System.out.println(Test_1_B.str);
    }
}

class Test_1_A {
    public static final String str = "a str";

    static {
        System.out.println("a static block");
    }
}

class Test_1_B extends Test_1_A {
    static {
        System.out.println("b static block");
    }
}
