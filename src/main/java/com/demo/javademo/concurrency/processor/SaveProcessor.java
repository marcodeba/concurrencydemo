package com.demo.javademo.concurrency.processor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class SaveProcessor extends Thread implements RequestProcessor {
    RequestProcessor nextProcessor;
    //存储请求数据
    BlockingQueue<Request> requests = new LinkedBlockingDeque<>();

    volatile boolean finished = false;

    public SaveProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
        while (!finished) {
            try {
                Request request = requests.take();
                System.out.println("Save:" + request);
                nextProcessor.processRequest(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processRequest(Request request) {
        requests.add(request); //添加到阻塞队列
    }

    @Override
    public void shutdown() {
        System.out.println("SaveProcessor begin shutdown");
        finished = true;
        requests.clear();
        nextProcessor.shutdown();
    }
}
