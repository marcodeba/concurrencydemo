package com.demo.javademo.concurrency.blockqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo implements Delayed {
    private String orderId;
    private long delayTime;
    private long start = System.currentTimeMillis();
    private static DelayQueue<DelayQueueDemo> delayQueue = new DelayQueue();

    public DelayQueueDemo(String orderId, long delayTime) {
        this.orderId = orderId;
        this.delayTime = delayTime;
    }

    public static void main(String[] args) throws InterruptedException {
        delayQueue.offer(new DelayQueueDemo("1001", 1000));
        delayQueue.offer(new DelayQueueDemo("1002", 5000));
        delayQueue.offer(new DelayQueueDemo("1003", 3000));
        delayQueue.offer(new DelayQueueDemo("1004", 7000));
        delayQueue.offer(new DelayQueueDemo("1005", 2000));

        while (true) {
            DelayQueueDemo demo = delayQueue.take();
            System.out.println(demo);
        }
    }

    // 下次延迟执行的时间
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((start + delayTime) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "DelayQueueDemo{" +
                "orderId='" + orderId + '\'' +
                ", delayTime=" + delayTime +
                ", start=" + start +
                '}';
    }
}
