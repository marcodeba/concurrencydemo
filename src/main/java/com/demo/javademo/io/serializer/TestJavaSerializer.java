package com.demo.javademo.io.serializer;

public class TestJavaSerializer {
    public static void main(String[] args) {
        User user = new User("Serializer", 18);
        ISerializer iSerializer = new JavaSerializer();
        byte[] bytes = iSerializer.serialize(user);
        System.out.println(bytes.length);

        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]);
        }
        System.out.println();
        User user1 = iSerializer.deserialize(bytes);
        System.out.println(user1);
    }
}
