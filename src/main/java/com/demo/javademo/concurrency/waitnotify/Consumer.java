package com.demo.javademo.concurrency.waitnotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
    Logger logger = LoggerFactory.getLogger(Consumer.class);
    private Queue<String> msg;

    public Consumer(Queue<String> msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (msg) {
                //如果消息队列为空了，阻塞等待
                while (msg.isEmpty()) {
                    try {
                        msg.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                    logger.info("消费者消费消息：" + msg.remove());
                    //唤醒处于阻塞状态下的生产者
                    msg.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
