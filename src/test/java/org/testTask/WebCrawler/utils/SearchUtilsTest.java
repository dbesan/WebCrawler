package org.testTask.WebCrawler.utils;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.testTask.WebCrawler.utils.SearchUtils.getListOfWordsInStrings;
import static org.testTask.WebCrawler.utils.SearchUtils.searchWords;

public class SearchUtilsTest {
    @Test
    public void ifSearchIsValidReturnTRue() {
        List<String> wordForSearch = new ArrayList<>();
        wordForSearch.add("One");
        wordForSearch.add("Two");
        wordForSearch.add("Tree");
        wordForSearch.add("One Two");
        String str = "One Two Tree Four Five Six Seven Eight Nine Ten " +
                "One Two Tree Four Five Six Seven Eight Nine Ten Tree" +
                "One Two Tree Four Five Six Seven Eight Nine Ten Two Tree";
        String text = str.toLowerCase();
        Map expected = new LinkedHashMap();
        expected.put("One", 3);
        expected.put("Two", 4);
        expected.put("Tree", 5);
        expected.put("One Two", 3);
        assertEquals(expected, searchWords(wordForSearch, text));
    }

    @Test
    public void returnExpectedList() throws IOException {
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        List<String> expected = new ArrayList<>();
        expected.add("one");
        expected.add("two");
        expected.add("three");
        Mockito.when(reader.readLine()).thenReturn("one, two, three");
        List<String> actual = getListOfWordsInStrings(reader);
        assertEquals(actual, expected);
    }
}