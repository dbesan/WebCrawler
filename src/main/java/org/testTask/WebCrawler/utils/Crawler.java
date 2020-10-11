package org.testTask.WebCrawler.utils;


import org.testTask.WebCrawler.domain.Level;
import org.testTask.WebCrawler.domain.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

import static org.testTask.WebCrawler.constants.Constants.*;
import static org.testTask.WebCrawler.utils.Counter.getCurrentCounter;
import static org.testTask.WebCrawler.utils.SaveFile.*;
import static org.testTask.WebCrawler.utils.SearchUtils.getListOfWordsInStrings;
import static org.testTask.WebCrawler.utils.SessionParams.*;


public class Crawler {
    /**
     * void createSession() - create session, with params from console, call prepareToCrawling()
     */
    public static void createSession() throws IOException {
        String os = System.getProperty("os.name");
        String consoleEncoding = System.getProperty("console.encoding",
                os.startsWith("Windows") ? "cp866" : "utf-8");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, consoleEncoding));
        Session session = new Session(getURLParam(reader), getLinkDeepParam(reader), getMaximumLinksLimitParam(reader), getListOfWordsInStrings(reader));
        prepareToCrawling(session);
    }

    /**
     * void prepareToCrawling(Session session) - takes session, creates runtime storages for session fields:
     * Set<String> allCrawledLinks,
     * Map<String, Integer> bestOccurrences.
     * Preparing Set with links, with initial link to crawling.
     * Re-writing file with all results with empty file
     * Call void createLevel(int previousLevel, Set<String> previousLevelStrings, Session session)
     */
    public static void prepareToCrawling(Session session) throws IOException {
        int startLevelId = START_ID;

        Set<String> allCrawledLinks = new LinkedHashSet();//storage for links that already crawled
        session.setAllCrawledLinks(allCrawledLinks);

        Map<String, Integer> bestOccurrences = new ConcurrentSkipListMap<>();//storage for links with most occurrences
        session.setBestOccurrences(bestOccurrences);

        Set<String> firstLink = new HashSet<>();//initial Set with links
        firstLink.add(session.getURL());

        createFile(NAME_FILE_FOR_ALL_RESULT);//rewrite old file

        createLevel(startLevelId, firstLink, session);
    }

    /**
     * createLevel(int previousLevel, Set<String> previousLevelStrings, Session session) - creatу level and transmits to
     * void crawl(int previousLevel, Set<String> previousLevelStrings, Session session, Level level)
     */
    public static void createLevel(int previousLevel, Set<String> previousLevelStrings, Session session) throws IOException {
        Level level = new Level();
        crawl(previousLevel, previousLevelStrings, session, level);
    }

    /**
     * void crawl(int previousLevel, Set<String> previousLevelStrings, Session session, Level level)
     * Checking link deep - is reached.
     * Create storage for links of current level  Set<String> levelLinks
     * Create storage for results of current level Map<String, Map<String, Integer>> levelResult,
     * result saves as: Map<Link, Map<Word, Result of this word>>
     * Takes links  from previous level and, in cycle, crawling it in different threads,
     * calling taskForThread(Session session, Level level, Set<String> levelLinks, Map<String, Map<String, Integer>> levelResult, String link),
     * which return Runnable for use it in Thread.
     * After level ready - writing in file.
     * Save links with most occurrences to runtime storage.
     * Checking is link deep reached and  is is maximum crawled links reached, if not - create new level,
     * if yes -  write most occurrences in file.
     */
    public static void crawl(int previousLevel, Set<String> previousLevelStrings, Session session, Level level) throws IOException {

        if (!isDeepReached(session, level)) {//checking is deep reached

            level.setId(++previousLevel);//creating id of new level
            System.out.println("Created Level " + level.getId());

            Set<String> levelLinks = new LinkedHashSet<>();//storage for links of this level
            level.setLevelLinks(levelLinks);

            Map<String, Map<String, Integer>> levelResult = new LinkedHashMap<>();//storage for result of this level
            level.setLevelResults(levelResult);

            for (String link : previousLevelStrings) { //starting crawling each link
                CrawlerThread crawlerThread = new CrawlerThread(session, level, link);
                new Thread(crawlerThread).start();
            }

            writeInCSVFile(NAME_FILE_FOR_ALL_RESULT, level);//save result of this level

            System.out.println("Level " + level.getId() + " done.");

            savePagesLinksWithMostOccurrences(level, session);//save in runtime best links

            if (!isDeepReached(session, level) && isMaximumCrawledLinksNotReached(session)) {//checking deep and count crawled links
                createLevel(level.getId(), level.getLevelLinks(), session);
            } else {
                writePagesLinksWithMostOccurrences(NAME_FILE_FOR_BEST_RESULT, session);//if reached level or pages write in file most occurrences
                System.out.println("All done.");
            }

        }
    }

    /**
     * void addLinkToAllCrawledLink(Session session, String link) adds link to all crawled links
     */
    public synchronized static void addLinkToAllCrawledLink(Session session, String link) {
        session.getAllCrawledLinks().add(link);
    }

    /**
     * boolean isDeepReached(Session session, Level level) return true if deep is reached.
     */
    public synchronized static boolean isDeepReached(Session session, Level level) {
        return level.getId() == session.getLinkDeep();
    }

    /**
     * boolean isLinkCrawled(Session session, String link) return true if link already crawled
     */
    public synchronized static boolean isLinkCrawled(Session session, String link) {
        return session.getAllCrawledLinks().contains(link);
    }

    /**
     * boolean isMaximumCrawledLinksNotReached(Session session) true if maximum number of crawled links is not reached
     */
    public synchronized static boolean isMaximumCrawledLinksNotReached(Session session) {
        return getCurrentCounter() < session.getMaximumPagesForVisit();
    }

}
