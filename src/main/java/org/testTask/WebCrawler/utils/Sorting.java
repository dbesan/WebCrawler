package org.testTask.WebCrawler.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Sorting {
    /**
     * Map<K, V> sortByValue(Map<K, V> map) sorting map with most occurrences in reverse order(3,2,1) and return LinkedHashMap
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> stream = map.entrySet().stream();

        stream.sorted(Comparator.comparing((e -> e.getValue()), Collections.reverseOrder()))
                .forEach(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }

}
