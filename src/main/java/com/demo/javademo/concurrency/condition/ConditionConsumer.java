package com.demo.javademo.concurrency.condition;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionConsumer implements Runnable {
    private Queue<String> msg;
    private int maxSize;
    private Lock lock;
    private Condition condition;

    public ConditionConsumer(Queue<String> msg, int maxSize, Lock lock, Condition condition) {
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
                if (msg.isEmpty()) {
                    System.out.println("消费者队列空了，请等待");
                    condition.await();
                }
                System.out.println("消费者消费消息:" + msg.remove());

                Thread.sleep(1000);
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
