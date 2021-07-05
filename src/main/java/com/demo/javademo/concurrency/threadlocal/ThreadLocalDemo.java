package com.demo.javademo.concurrency.threadlocal;

public class ThreadLocalDemo {
    static ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> 0);
    private static int num = 0;

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
//                num += 5;
//                System.out.println(Thread.currentThread().getName() + ", " + num);

                int num = local.get();
                local.set(num += 5);
                System.out.println(Thread.currentThread().getName() + "-" + num);
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}
