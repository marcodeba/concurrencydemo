package com.demo.javademo.concurrency;

public class ThreadInterruptDemo {
    private volatile static boolean on = false;

    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(() -> {
            while (!on) {
                try {
                    // 中断一个处于阻塞状态的线程会抛出InterruptedException
                    Thread.sleep(10000000);
                } catch (InterruptedException e) {
                    System.out.println("caught exception right now: " + e);
                }
            }
        });
        //Thread testThread = new Thread(new ThreadInterruptDemo(), "InterruptionInJava");
        //start thread
        testThread.start();
        Thread.sleep(1000);
        ThreadInterruptDemo.on = true;
        testThread.interrupt();

        System.out.println("main end");
    }
}
