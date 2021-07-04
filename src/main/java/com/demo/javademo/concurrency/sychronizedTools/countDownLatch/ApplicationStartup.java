package com.demo.javademo.concurrency.sychronizedTools.countDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ApplicationStartup {
    private static List<BaseHealthChecker> services;
    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    static {
        services = new ArrayList<>();
        services.add(new CacheHealthChecker(countDownLatch));
        services.add(new DatabaseHealthChecker(countDownLatch));
    }

    private ApplicationStartup() {
    }

    public static boolean checkExternalServices() throws InterruptedException {
        for (BaseHealthChecker bh : services) {
            new Thread(bh).start();
        }
        countDownLatch.await();
        return true;
    }

    public static void main(String[] args) {
        try {
            ApplicationStartup.checkExternalServices();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("服务启动成功");
    }
}
