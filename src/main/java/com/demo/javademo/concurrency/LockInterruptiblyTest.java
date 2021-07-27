package com.demo.javademo.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyTest {
    public static void main(String[] args) {
        Thread i1 = new Thread(new MyThread());
        Thread i2 = new Thread(new MyThread());
        i1.start();
        i2.start();
        i2.interrupt();
    }

    static class MyThread implements Runnable {
        private static Lock lock = new ReentrantLock();

        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " running");
//                lock.lock();
                lock.lockInterruptibly();
                TimeUnit.SECONDS.sleep(5);
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " finished");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
        }
    }
}
