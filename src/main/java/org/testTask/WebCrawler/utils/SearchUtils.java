package org.testTask.WebCrawler.utils;

import org.testTask.WebCrawler.constants.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.testTask.WebCrawler.constants.Constants.CONSOLE_DELIMITER;
import static org.testTask.WebCrawler.utils.SessionParams.readingFromConsole;

public class SearchUtils {
    /**
     * List setOfWordsInStrings() gets words from console and return List, which contains word or phrases on String
     */
    public static List getListOfWordsInStrings(BufferedReader reader) throws IOException {
        System.out.println(CONSOLE_DELIMITER);
        System.out.println("Please, set words for search. Use \"" + Constants.WORD_DELIMITER + "\" as delimeter.");
        List<String> words = Arrays.stream(readingFromConsole(reader).split(", ")).collect(Collectors.toList());
        return words;
    }

    /**
     * Map searchWords(List<String> wordsForSearch, String text) gets text and List with word or phrases for search  in text and
     * return Map <String, IntegerA></> with results
     */
    public static Map searchWords(List<String> wordsForSearch, String text) {
        Map<String, Integer> resultMap = new LinkedHashMap();
        for (String word : wordsForSearch) {
            resultMap.put(word, 0);
            String wordLower = word.toLowerCase();
            int index;
            int last = 0;
            int occurrences = 0;
            do {
                index = text.indexOf(wordLower, last);
                if (index != -1) occurrences++;
                last = index + wordLower.length();
            } while (index != -1);
            resultMap.put(word, occurrences);
        }
        return resultMap;
    }
}
