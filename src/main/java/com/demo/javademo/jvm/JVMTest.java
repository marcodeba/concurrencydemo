package com.demo.javademo.jvm;

import sun.misc.Launcher;

import java.net.URL;

public class JVMTest {
    public static void main(String[] args) {
        String property = System.getProperty("java.ext.dirs");
        System.out.println(property);

        String[] urls = System.getProperty("sun.boot.class.path").split(":");
        for (String url : urls) {
            System.out.println(url);
        }

        URL[] urls1 = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls1) {
            System.out.println(url);
        }
    }
}
