package com.demo.javademo.concurrency;

public class ThreadDemo extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finish run");
        }
    }

    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();
        demo.start();
        System.out.println("finish main");
    }
}
