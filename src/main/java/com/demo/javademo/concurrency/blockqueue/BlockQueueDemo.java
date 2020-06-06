package com.demo.javademo.concurrency.blockqueue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueueDemo<E> {
    private int size;
    private LinkedList<E> linkedList = new LinkedList<E>();
    private Lock lock = new ReentrantLock();
    Condition fullCondition = lock.newCondition();
    Condition emptyCondition = lock.newCondition();

    // 初始化BlockQueue
    public BlockQueueDemo(int size) {
        this.size = size;
    }

    public static void main(String[] args) {
        BlockQueueDemo<Integer> blockQueue = new BlockQueueDemo<>(20);
        new Thread(() -> {
            for (int index = 0; index < 100; index++) {
                blockQueue.enQueue(index);
            }
        }, "enQueue").start();

        new Thread(() -> {
            for (int index = 0; index < 100; index++) {
                blockQueue.deQueue();
            }
        }, "deQueue").start();
    }

    /**
     * 入队操作，队列满了,入队要阻塞(condition的await)
     *
     * @param e
     */
    public void enQueue(E e) {
        //非空判断
        if (e == null) throw new NullPointerException();

        // 支持多线程，得要加锁
        lock.lock();
        try {
            // 队列满了，入队阻塞
            while (linkedList.size() == size) {
                fullCondition.await();
            }
            // 否则将元素入队
            linkedList.add(e);
            System.out.println(e + "元素入队了");

            // 通知可以取元素了
            emptyCondition.signal();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 出列操作，如果队列空了，出队要阻塞(condition的await)
     *
     * @return
     */
    public E deQueue() {
        E element = null;
        lock.lock();
        try {
            // 队列空，出队阻塞
            while (linkedList.isEmpty()) {
                emptyCondition.await();
            }
            // 否则将元素出队
            element = linkedList.removeFirst();
            System.out.println(element + "元素出列了");

            // 通知可以取元素了
            fullCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return element;
    }
}
