package org.testTask.WebCrawler.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    /**
     * Thread-safe counter
     */
    private static final AtomicInteger counter = new AtomicInteger(0);

    /**
     * int getCurrenCounter() return current counter
     */
    public static int getCurrentCounter() {
        return counter.get();
    }

    /**
     * void incrementCounter() increments counter
     */
    public static void incrementCounter() {
        while (true) {
            int existingValue = getCurrentCounter();
            int newValue = existingValue + 1;
            if (counter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }
}
