package com.demo.javademo.concurrency.FutureDemo;

import java.util.concurrent.*;

public class SquareCalculator {
    private ExecutorService es = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Future<Integer> future = new SquareCalculator().calculate(10);

        while (!future.isDone()) {
            System.out.println("Calculating...");
            Thread.sleep(300);
        }

        Integer result = future.get();
        System.out.println(result);
    }

    public Future<Integer> calculate(Integer input) {
        return es.submit(() -> {
            Thread.sleep(1000);
            return input * input;
        });
    }
}
