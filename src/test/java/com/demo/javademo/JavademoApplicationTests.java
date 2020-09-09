package com.demo.javademo;

import com.demo.javademo.reflection.SmallPineapple;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@SpringBootTest
class JavademoApplicationTests {

    @Test
    void testClass() throws ClassNotFoundException {
        Class clazz1 = Class.forName("com.demo.javademo.reflection.SmallPineapple");
        Class clazz2 = SmallPineapple.class;
        SmallPineapple instance = new SmallPineapple();
        Class clazz3 = instance.getClass();
        System.out.println("Class.forName() == SmallPineapple.class:" + (clazz1 == clazz2));
        System.out.println("Class.forName() == instance.getClass():" + (clazz1 == clazz3));
        System.out.println("instance.getClass() == SmallPineapple.class:" + (clazz2 == clazz3));
    }

    @Test
    void testCreateInstance() throws Exception {
        Class clazz = Class.forName("com.demo.javademo.reflection.SmallPineapple");
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        constructor.setAccessible(true);
        SmallPineapple smallPineapple2 = (SmallPineapple) constructor.newInstance("小菠萝", 21);
        smallPineapple2.getInfo();
    }

    @Test
    void testCallMethod() throws Exception {
        Class clazz = Class.forName("com.demo.javademo.reflection.SmallPineapple");
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        constructor.setAccessible(true);
        SmallPineapple smallPineapple = (SmallPineapple) constructor.newInstance("小菠萝", 21);
        Method method = clazz.getMethod("getInfo");
        if (method != null) {
            method.invoke(smallPineapple, null);
        }
    }
}
