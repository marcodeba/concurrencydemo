package com.demo.javademo.concurrency.concurrencyTools;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 10; i++) {
            Toilet toilet = new Toilet("第" + i + "个人,", semaphore);
            new Thread(toilet).start();
        }
    }
}

class Toilet implements Runnable {
    private String name;
    private Semaphore wc;

    public Toilet(String name, Semaphore wc) {
        this.name = name;
        this.wc = wc;
    }

    @Override
    public void run() {
        try {
            // 剩下的资源：即剩下的茅坑
            int availablePermits = wc.availablePermits();
            if (availablePermits > 0) {
                System.out.println(name + "天助我也,终于有茅坑了...");
            } else {
                System.out.println(name + "怎么没有茅坑了...");
            }
            //申请茅坑，如果资源达到3次，就等待
            wc.acquire();
            System.out.println(name + "终于轮我上厕所了..爽啊");
            Thread.sleep(new Random().nextInt(1000)); // 模拟上厕所时间。
            System.out.println(name + "厕所上完了...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            wc.release();
        }
    }
}