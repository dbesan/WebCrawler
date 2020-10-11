package org.testTask.WebCrawler.utils;

import org.jsoup.nodes.Document;
import org.testTask.WebCrawler.domain.Level;
import org.testTask.WebCrawler.domain.Session;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static org.testTask.WebCrawler.utils.Counter.getCurrentCounter;
import static org.testTask.WebCrawler.utils.Counter.incrementCounter;
import static org.testTask.WebCrawler.utils.Crawler.*;
import static org.testTask.WebCrawler.utils.Parsing.*;
import static org.testTask.WebCrawler.utils.SearchUtils.searchWords;

public class CrawlerThread extends Thread {

    private Session session;
    private Level level;
    private String link;

    public CrawlerThread(Session session, Level level, String link) {
        this.session = session;
        this.level = level;
        this.link = link;
        taskForThread(session, level, link);
    }

    /**
     * void taskForThread(Session session, Level level, String link)
     * returns Runnable with parsing algorithm.
     * Algorithm:
     * - is maximum crawled links reached, if not - next step
     * - is link already crawled - if not - next step
     * - add link to list with all crawled links
     * - connecting to page with link and take Document with page content.
     * - if document not null:
     * - increment counter of all crawled links;
     * - parsing and save links from page;
     * - parsing and save text from page;
     * - search words occurrences from text;
     * - saving results
     */

    public void taskForThread(Session session, Level level, String link) {
        if (isMaximumCrawledLinksNotReached(session)) {//check count of links for each link
            if (!isLinkCrawled(session, link)) {//check is link crawled already
                Document document = null;
                try {
                    document = connectToPage(link); //retrieving html
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Set<String> levelLinks = new LinkedHashSet<>();//storage for links of this thread
                Map<String, Map<String, Integer>> levelResult = new LinkedHashMap<>();//storage for result of this thread

                if (!(document == null)) {
                    incrementCounter();//increment counter crawled links
                    addLinkToAllCrawledLink(session, link); //save link to all crawled

                    String text = textFromPage(document);//crawl text

                    Set<String> links = hrefFromPage(document);//crawl links
                    levelLinks.addAll(links);

                    Map<String, Integer> result = searchWords(session.getWordsForSearch(), text); //search words
                    levelResult.put(link, result);

                    level.getLevelLinks().addAll(levelLinks);

                    synchronized (level) {
                        level.getLevelResults().putAll(levelResult);
                    }

                    System.out.println(link + " " + "Crawled:" + getCurrentCounter() + " Level " + level.getId());
                }
            }
        }
    }
}