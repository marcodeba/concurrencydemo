package com.demo.javademo.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    static Map<String, Object> cacheMap = new HashMap<>();
    static ReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock readLock = lock.readLock();
    static Lock writeLock = lock.writeLock();

    public static final Object get(String key) {
        readLock.lock(); //读锁 ThreadA 阻塞
        try {
            return cacheMap.get(key);
        } finally {
            readLock.unlock(); //释放读锁
        }
    }

    public static final Object write(String key, Object value) {
        writeLock.lock(); //Other Thread 获得了写锁
        try {
            return cacheMap.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public static final void clear() {
        writeLock.lock();
        try {
            cacheMap.clear();
        } finally {
            writeLock.unlock();
        }
    }
}
