package org.testTask.WebCrawler.utils;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.testTask.WebCrawler.utils.Sorting.sortByValue;

public class SortingTest {
    @Test
    public void testSortByValue() {
        Map<String, Integer> toSort = new LinkedHashMap<>();
        toSort.put("wikipedia.org", 30);
        toSort.put("mail.ru", 90);
        toSort.put("yandex.ru", 20);
        toSort.put("google.com", 10);

        Map<String, Integer> expected = new LinkedHashMap<>();
        expected.put("google.com", 10);
        expected.put("yandex.ru", 20);
        expected.put("wikipedia.org", 30);
        expected.put("mail.ru", 90);
        Map actual = sortByValue(toSort);
        assertEquals(actual, expected);
    }

}
