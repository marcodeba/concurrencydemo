package com.demo.javademo.concurrency.condition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        int maxSize = 5;
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        ConditionProducer producer = new ConditionProducer(queue, maxSize, lock, condition);
        ConditionConsumer consumer = new ConditionConsumer(queue, lock, condition);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start();
        t2.start();
    }
}
