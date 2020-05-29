package com.demo.javademo.concurrency.condition;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionProducer implements Runnable {
    private Queue<String> msg;
    private int maxSize;
    private Lock lock;
    private Condition condition;

    public ConditionProducer(Queue<String> msg, int maxSize, Lock lock, Condition condition) {
        this.msg = msg;
        this.maxSize = maxSize;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            i++;
            lock.lock();
            try {
                if (msg.size() == maxSize) {
                    System.out.println("生产者队列满了，请等待");
                    condition.await();
                }
                System.out.println("生产者生产消息:" + i);
                msg.add("消息：" + i);

                Thread.sleep(1000);
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
