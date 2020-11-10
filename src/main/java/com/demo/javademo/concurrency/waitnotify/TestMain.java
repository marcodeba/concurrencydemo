package com.demo.javademo.concurrency.waitnotify;

import java.util.LinkedList;
import java.util.Queue;

public class TestMain {

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        int maxSize = 5;
        Producer producer = new Producer(queue, maxSize);
        Consumer consumer = new Consumer(queue);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start();
        t2.start();
    }
}
