package org.testTask.WebCrawler.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.testTask.WebCrawler.utils.Counter.getCurrentCounter;
import static org.testTask.WebCrawler.utils.Counter.incrementCounter;

public class CounterTest {
    @Test
    public void testGetCurrentCounter() {
        incrementCounter();
        int expected = 1;
        int actual = getCurrentCounter();
        assertEquals(expected, actual);
    }

}