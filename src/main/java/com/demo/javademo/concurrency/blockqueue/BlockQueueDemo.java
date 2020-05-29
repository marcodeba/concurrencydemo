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

    /**
     * 入队操作，队列满了,入队要阻塞(condition的await)
     * @param e
     */
    public void enQueue(E e) {
        // 支持多线程，得要加锁
        lock.lock();
        try {
            // 队列满了，入队阻塞
            if (linkedList.size() == size) {
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
     * @return
     */
    public E deQueue() {
        lock.lock();
        while (true) {
            try {
                // 队列空，出队阻塞
                if (linkedList.isEmpty()) {
                    emptyCondition.await();
                }
                // 否则将元素出队
                E e = linkedList.removeFirst();
                System.out.println(e + "元素出列了");

                // 通知可以取元素了
                fullCondition.signal();
                return e;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        BlockQueueDemo<Integer> queue = new BlockQueueDemo<>(20);
        new Thread(() -> {
            for (int index = 0; index < 100; index++) {
                try {
                    queue.enQueue(index);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int index = 0; index < 100; index++) {
                try {
                    queue.deQueue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
