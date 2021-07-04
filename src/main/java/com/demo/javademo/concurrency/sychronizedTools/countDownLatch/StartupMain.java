package com.demo.javademo.concurrency.sychronizedTools.countDownLatch;

public class StartupMain {
    public static void main(String[] args) {
        try {
            ApplicationStartup.checkExternalServices();
        } catch (InterruptedException e) {
            //有问题了.
        }
        System.out.println("服务启动成功");
    }
}
