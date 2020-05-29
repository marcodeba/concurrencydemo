package com.demo.javademo.concurrency.waitnotify;

import java.util.Queue;

public class Consumer implements Runnable {
    private Queue<String> msg;
    private int maxSize;

    public Consumer(Queue<String> msg, int maxSize) {
        this.msg = msg;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (msg) {
                while (msg.isEmpty()) {
                    //如果消息队列为空了
                    try {
                        msg.wait(); //阻塞当前线程
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者消费消息：" + msg.remove());
                msg.notify(); //唤醒处于阻塞状态下的生产者
            }
        }
    }
}
