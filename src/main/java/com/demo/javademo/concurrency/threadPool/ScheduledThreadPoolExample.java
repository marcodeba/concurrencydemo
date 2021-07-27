package com.demo.javademo.concurrency.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

@Slf4j
public class ScheduledThreadPoolExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        // 延迟3秒后执行
//        ScheduledFuture<Integer> future = executorService.schedule(() -> {
//            log.warn("schedule run");
//            return 1;
//        }, 3, TimeUnit.SECONDS);
//
//        System.out.println("business logic");
//        System.out.println(future.get());

        executorService.scheduleWithFixedDelay(() -> {
            log.info("send heart beat start");
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("task over");
        }, 1000, 8000, TimeUnit.MILLISECONDS);
    }
}
