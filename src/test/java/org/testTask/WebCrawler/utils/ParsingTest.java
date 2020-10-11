package org.testTask.WebCrawler.utils;

import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.testTask.WebCrawler.utils.Parsing.hrefFromPage;
import static org.testTask.WebCrawler.utils.Parsing.textFromPage;

public class ParsingTest {

    @Test
    public void testTextFromPageStringReturnTrueIfNotNullNeedsInternetConnection() {
        String url = "wikipedia.org";
        Document document = null;
        try {
            document = Parsing.connectToPage(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String actual = textFromPage(document);
        assertNotNull(actual);

    }

    @Test
    public void testHrefFromPageReturnTrueIfNotNullNeedsInternetConnection() {
        String url = "wikipedia.org";
        Document document = null;
        try {
            document = Parsing.connectToPage(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<String> hrefs = hrefFromPage(document);
        assertNotNull(hrefs);

    }
}