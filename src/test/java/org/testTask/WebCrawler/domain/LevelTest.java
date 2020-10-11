package org.testTask.WebCrawler.domain;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LevelTest {
    Level level = new Level(0);

    @Test
    public void testSetAndGetId() {
        int expected = 5;
        level.setId(expected);
        int actual = level.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetAndGetLevelLinks() {
        Set<String> expected = new LinkedHashSet<>();
        expected.add("wikipedia.org");
        expected.add("mail.ru");
        expected.add("yandex.ru");
        expected.add("google.com");
        level.setLevelLinks(expected);
        Set actual = level.getLevelLinks();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetAndGetLevelResults() {
        Map<String, Map<String, Integer>> expected = new LinkedHashMap<>();
        Map<String, Integer> results = new LinkedHashMap<>();
        results.put("word", 1);
        results.put("is", 2);
        results.put("lie", 3);
        Map<String, Integer> results2 = new LinkedHashMap<>();
        results.put("word", 3);
        results.put("is", 5);
        results.put("lie", 7);
        expected.put("google.com", results);
        expected.put("yandex.ru", results2);
        level.setLevelResults(expected);
        Map actual = level.getLevelResults();
        assertEquals(expected, actual);
    }

}