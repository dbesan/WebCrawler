package org.testTask.WebCrawler.utils;

import org.junit.Test;
import org.testTask.WebCrawler.domain.Level;
import org.testTask.WebCrawler.domain.Session;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.testTask.WebCrawler.utils.Crawler.*;

public class CrawlerTest {
    @Test
    public void testIsDeepReached() {
        Session session = new Session();
        Level level = new Level();
        int expected = 5;
        session.setLinkDeep(expected);
        level.setId(expected);
        boolean actual = isDeepReached(session, level);
        assertEquals(true, actual);
    }

    @Test
    public void testIsLinkCrawled() {
        Session session = new Session();
        String link = "wikipedia.org";
        Set links = new LinkedHashSet();
        links.add(link);
        session.setAllCrawledLinks(links);
        boolean actual = isLinkCrawled(session, link);
        assertEquals(true, actual);
    }

    @Test
    public void testIsMaximumCrawledLinksNotReached() {
        Session session = new Session();
        String link = "wikipedia.org";
        Set links = new LinkedHashSet();
        links.add(link);
        session.setAllCrawledLinks(links);
        int maximumPagesForVisit = 1;
        boolean actual = isMaximumCrawledLinksNotReached(session);
        assertEquals(false, actual);
    }
}