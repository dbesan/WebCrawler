package org.testTask.WebCrawler.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Session {
    private String URL;
    private int linkDeep;
    private int maximumPagesForVisit;
    private List<String> wordsForSearch;
    private Set<String> allCrawledLinks;
    private Map<String, Integer> bestOccurrences;

    public Session() {
    }

    public Session(String URL, int linkDeep, int maximumPagesForVisit, List<String> wordsForSearch) {
        this.URL = URL;
        this.linkDeep = linkDeep;
        this.maximumPagesForVisit = maximumPagesForVisit;
        this.wordsForSearch = wordsForSearch;
    }

    public synchronized Set<String> getAllCrawledLinks() {
        return allCrawledLinks;
    }

    public synchronized void setAllCrawledLinks(Set<String> allCrawledLinks) {
        this.allCrawledLinks = allCrawledLinks;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getLinkDeep() {
        return linkDeep;
    }

    public void setLinkDeep(int linkDeep) {
        this.linkDeep = linkDeep;
    }

    public int getMaximumPagesForVisit() {
        return maximumPagesForVisit;
    }

    public void setMaximumPagesForVisit(int maximumPagesForVisit) {
        this.maximumPagesForVisit = maximumPagesForVisit;
    }

    public List<String> getWordsForSearch() {
        return wordsForSearch;
    }

    public void setWordsForSearch(List<String> wordsForSearch) {
        this.wordsForSearch = wordsForSearch;
    }

    public Map<String, Integer> getBestOccurrences() {
        return bestOccurrences;
    }

    public void setBestOccurrences(Map<String, Integer> bestOccurrences) {
        this.bestOccurrences = bestOccurrences;
    }
}
