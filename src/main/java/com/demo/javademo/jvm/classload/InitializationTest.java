package com.demo.javademo.jvm.classload;

public class InitializationTest {
    public static void main(String[] args) {
        //String a = SubInitClass.a;// 引用父类的静态字段，只会引起父类初始化，不会引起子类初始化
        String b = InitClass.b;// final常量不会引起类的初始化

    }
}

class InitClass {
    public static final String b = "b";
    public static String a = null;

    static {
        System.out.println("初始化InitClass");
    }

    public static void method() {
    }
}

class SubInitClass extends InitClass {
    static {
        System.out.println("初始化SubInitClass");
    }
}
