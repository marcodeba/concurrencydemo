package com.demo.javademo.concurrency.sychronizedTools.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class DatabaseHealthChecker extends BaseHealthChecker {
    private CountDownLatch countDownLatch;


    public DatabaseHealthChecker(CountDownLatch countDownLatch) {
        super("DatabaseHealthChecker");
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void verifyService() throws Exception {
        System.out.println("Checking:" + this.getServiceName());
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            throw e;
        }
        countDownLatch.countDown();
        System.out.println(this.getServiceName() + " 健康状态正常");
    }

}
