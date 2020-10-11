package org.testTask.WebCrawler.utils;

import au.com.bytecode.opencsv.CSVWriter;
import org.testTask.WebCrawler.domain.Level;
import org.testTask.WebCrawler.domain.Session;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import static org.testTask.WebCrawler.constants.Constants.COUNT_PAGES_WITH_BEST_OCCURRENCES;
import static org.testTask.WebCrawler.utils.Sorting.sortByValue;


public class SaveFile {
    /**
     * void createFile(String name) - creating new empty file for all results. For honest, its need to delete file
     * from another session and/or write header to file with all results.
     */
    public static void createFile(String name) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(name, false));
        writer.close();

    }

    /**
     * void writeInExistsFile(String name, Level level) write all from results from Level in file,
     * which created by createFile(String name), or just in file results
     * e.g.
     * link.com 0 3 5 7 15
     */
    public static synchronized void writeInCSVFile(String name, Level level) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(name, true));
        String record;
        Map<String, Map<String, Integer>> results = level.getLevelResults();
        System.out.println("Result for Level " + level.getId());
        if (!(results == null)) {
            for (Map.Entry<String, Map<String, Integer>> entry : results.entrySet()) {
                record = entry.getKey();
                int total = 0;

                for (Map.Entry<String, Integer> wordEntry : entry.getValue().entrySet()) {
                    total += wordEntry.getValue();
                    record = record + " " + wordEntry.getValue();
                }
                record = record + " " + total;
                System.out.println(record);
                String[] recordCSV = record.split(" ");
                writer.writeNext(recordCSV);
            }
        }
        writer.close();
    }

    /**
     * void savePagesLinksWithMostOccurrences(Level level, Session session)
     * runtime storage for pages with most X (COUNT_PAGES_WITH_BEST_OCCURRENCES) suggestion
     * Creates 3 Map -  Map<String, Map<String, Integer>> results with results of crawling of Level
     * Map<String, Integer> bestOccurrences for saving result from this methof
     * Map<String, Integer> resultWithTotal - "working" Map.
     * Put from previous Level result to  Map resultWithTotal
     * Takes results from results Map, count total and put link and total to resultWithTotal
     * Sorting resultWithTotal
     * Create List with entities from resultWithTotal
     * Add first X (COUNT_PAGES_WITH_BEST_OCCURRENCES) elements from List to bestOccurrences Map.
     * Set new result to current session
     */

    public static synchronized void savePagesLinksWithMostOccurrences(Level level, Session session) {
        Map<String, Map<String, Integer>> results = level.getLevelResults();
        Map<String, Integer> bestOccurrences = new ConcurrentSkipListMap<>();
        Map<String, Integer> resultWithTotal = new ConcurrentSkipListMap<>(session.getBestOccurrences());
        if (!(results == null)) {
            for (Map.Entry<String, Map<String, Integer>> entry : results.entrySet()) {
                int total = 0;
                String link = entry.getKey();
                for (Map.Entry<String, Integer> wordEntry : entry.getValue().entrySet()) {
                    total += wordEntry.getValue();
                }
                resultWithTotal.put(link, total);
            }
            resultWithTotal = sortByValue(resultWithTotal);
            List<Map.Entry<String, Integer>> tempListResults = new ArrayList(resultWithTotal.entrySet());
            if (!(tempListResults.size() == 0)) {
                for (int occurrence = 0; occurrence < COUNT_PAGES_WITH_BEST_OCCURRENCES; occurrence++) {
                    bestOccurrences.put(tempListResults.get(occurrence).getKey(), tempListResults.get(occurrence).getValue());
                    if (occurrence == tempListResults.size() - 1) {
                        break;
                    }
                }
            }
            session.setBestOccurrences(bestOccurrences);
        }
    }

    /**
     * void writePagesLinksWithMostOccurrences(String name, Session session) write links with best occurrences
     * and total result in file:
     * e.g.
     * link.com 15
     */
    public static synchronized void writePagesLinksWithMostOccurrences(String name, Session session) throws IOException {//
        CSVWriter writer = new CSVWriter(new FileWriter(name, false));
        System.out.println("Links with most occurrences: ");
        session.setBestOccurrences(sortByValue(session.getBestOccurrences()));
        for (Map.Entry<String, Integer> entry : session.getBestOccurrences().entrySet()) {
            String record = entry.getKey() + " " + entry.getValue();
            System.out.println(record);
            String[] recordToFile = record.split(" ");
            writer.writeNext(recordToFile);
        }
        writer.close();
    }
}
