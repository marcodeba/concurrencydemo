package com.demo.javademo.concurrency.cas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CasDemo {
    private static final Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
    private static final long stateOffset;
    private static Logger log = LoggerFactory.getLogger(CasDemo.class);
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    private static CasDemo cas = new CasDemo();

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(CasDemo.class.getDeclaredField("state"));
        } catch (Exception e) {
            throw new Error();
        }
    }

    /**
     * 当前加锁状态,记录加锁的次数
     */
    private volatile int state = 0;

    public static void main(String[] args) {
        new Thread(new Worker(), "t-0").start();
        new Thread(new Worker(), "t-1").start();
        new Thread(new Worker(), "t-2").start();
        new Thread(new Worker(), "t-3").start();
        new Thread(new Worker(), "t-4").start();
    }

    /**
     * 原子操作
     *
     * @param oldValue        oldvalue:线程工作内存当中的值
     * @param newValue:要替换的新值
     * @return
     */
    public final boolean compareAndSwapState(int oldValue, int newValue) {
        return unsafe.compareAndSwapInt(this, stateOffset, oldValue, newValue);
    }

    static class Worker implements Runnable {

        @Override
        public void run() {
            log.info("请求:{}到达预定点,准备开始抢state:)", Thread.currentThread().getName());
            try {
                cyclicBarrier.await();
                if (cas.compareAndSwapState(0, 1)) {
                    log.info("当前请求:{},抢到锁!", Thread.currentThread().getName());
                } else {
                    log.info("当前请求:{},抢锁失败!", Thread.currentThread().getName());
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
