package com.demo.javademo.concurrency;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {
    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println(i);
        }, "interruptDemo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt(); //设置interrupt标识为true
        System.out.println(thread.isInterrupted());
    }

//    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(() -> {
//            while (true) {
//                if (Thread.currentThread().isInterrupted()) {
//                    System.out.println("before:" + Thread.currentThread().isInterrupted());
//                    Thread.interrupted(); //对线程进行复位，由 true 变成 false
//                    System.out.println("after:" + Thread.currentThread().isInterrupted());
//                }
//            }
//        }, "interruptDemo");
//        thread.start();
//        TimeUnit.SECONDS.sleep(1);
//        thread.interrupt();
//    }
}
