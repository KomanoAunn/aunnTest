package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TestMain3 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        AtomicCount count = new AtomicCount();
        for (int i = 0; i < 1000; i++) {
            service.execute(() -> count.increment());
        }
        service.shutdown();
        System.out.println(count.getCount());
    }
}


class AtomicCount {
    private AtomicInteger count = new AtomicInteger(0);

    public Integer getCount() {
        return count.intValue();
    }

    public void increment() {
        count.incrementAndGet();
    }
}