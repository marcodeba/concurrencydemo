package com.demo.javademo.concurrency.lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            Thread current = Thread.currentThread();
            for (;;) {
                System.out.println("准备park当前线程" + current.getName());
                LockSupport.park();
                System.out.println("当前线程被唤醒" + current.getName());
            }
        });
        thread1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("准备唤醒线程" + thread1.getName());
            LockSupport.unpark(thread1);
            TimeUnit.SECONDS.sleep(1);
            thread1.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
