package com.demo.javademo.concurrency.sychronizedTools.countDownLatch;

public abstract class BaseHealthChecker implements Runnable {
    //服务名称
    private String serviceName;
    private boolean serviceUp;

    public BaseHealthChecker(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void run() {
        try {
            verifyService();
            serviceUp = true;
        } catch (Exception e) {
            serviceUp = false;
        } finally {

        }
    }

    /**
     * 检查服务的健康情况
     */
    public abstract void verifyService() throws Exception;

    public String getServiceName() {
        return serviceName;
    }

    public boolean isServiceUp() {
        return serviceUp;
    }
}
