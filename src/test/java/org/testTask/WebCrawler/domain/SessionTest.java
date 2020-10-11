package org.testTask.WebCrawler.domain;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class SessionTest {
    Session session = new Session();

    @Test
    public void constructorTest() {
        String URL = "wikipedia.org";
        int linkDeep = 3;
        int maximumPagesForVisit = 999;
        List<String> wordsForSearch = new ArrayList<>();
        wordsForSearch.add("one");
        wordsForSearch.add("two");
        wordsForSearch.add("three four");
        Session expected = new Session(URL, linkDeep, maximumPagesForVisit, wordsForSearch);
        assertEquals(URL, expected.getURL());
        assertEquals(linkDeep, expected.getLinkDeep());
        assertEquals(maximumPagesForVisit, expected.getMaximumPagesForVisit());
        assertEquals(wordsForSearch, expected.getWordsForSearch());
    }

    @Test
    public void testSetAndGetURL() {
        String expected = "wikipedia.org";
        session.setURL(expected);
        String actual = session.getURL();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetAndGetLinkDeep() {
        int expected = 3;
        session.setLinkDeep(expected);
        int actual = session.getLinkDeep();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetAndGetMaximumPagesForVisit() {
        int expected = 9999;
        session.setMaximumPagesForVisit(expected);
        int actual = session.getMaximumPagesForVisit();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetAndGetAllCrawledLinks() {
        Set<String> expected = new LinkedHashSet<>();
        expected.add("wikipedia.org");
        expected.add("mail.ru");
        expected.add("yandex.ru");
        expected.add("google.com");
        session.setAllCrawledLinks(expected);
        Set actual = session.getAllCrawledLinks();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetAndGetWordsForSearch() {
        List<String> expected = new ArrayList<>();
        expected.add("one");
        expected.add("two");
        expected.add("three four");
        session.setWordsForSearch(expected);
        List actual = session.getWordsForSearch();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetAndGetBestOccurances() {
        Map<String, Integer> expected = new LinkedHashMap<>();
        expected.put("wikipedia.org", 30);
        expected.put("mail.ru", 90);
        expected.put("yandex.ru", 20);
        expected.put("google.com", 10);
        session.setBestOccurrences(expected);
        Map actual = session.getBestOccurrences();
        assertEquals(expected, actual);
    }
}