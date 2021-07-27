package com.demo.javademo.jvm.classload;

import java.io.FileInputStream;
import java.lang.reflect.Method;

public class TestMyClassLoader {
    public static void main(String[] args) throws Exception {
        //初始化自定义类加载器，会先初始化父类ClassLoader，其中会把自定义类加载器的父加载器设置为应用程序类加载器AppClassLoader
        MyClassLoader classLoader = new MyClassLoader("/Users/marcopan/Desktop/test");
        // classLoader.loadClass->URLClassLoader.findClass->URLClassLoader.defineClass
        Class clazz = classLoader.loadClass("com.demo.javademo.jvm.classload.Person");
        Object instance = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("toString", null);
        System.out.println(method.invoke(instance, null));
        System.out.println(clazz.getClassLoader().getClass().getName());
    }

    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = this.loadByte(name);
                // defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组。
                return super.defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First,check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t1 = System.nanoTime();
                    if (!name.startsWith("com.demo.javademo.jvm.classload")) {
                        c = this.getParent().loadClass(name);
                    } else {
                        c = findClass(name);
                    }
                    // this is the defining class loader;record the stats
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }
    }
}
