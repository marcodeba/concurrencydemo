package com.demo.javademo.concurrency.sychronizedTools.countDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ApplicationStartup {
    private final static ApplicationStartup INSTANCE = new ApplicationStartup();
    private static List<BaseHealthChecker> services;
    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    static {
        services = new ArrayList<>();
        services.add(new CacheHealthChecker(countDownLatch));
        services.add(new DatabaseHealthChecker(countDownLatch));
    }

    private ApplicationStartup() {
    }

    public static ApplicationStartup getInstance() {
        return INSTANCE;
    }

    public static boolean checkExternalServices() throws InterruptedException {
        for (BaseHealthChecker bh : services) {
            new Thread(bh).start(); //针对每个服务采用线程来执行
        }
        countDownLatch.await();
        return true;
    }
}
