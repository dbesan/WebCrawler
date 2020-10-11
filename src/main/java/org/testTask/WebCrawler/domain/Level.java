package org.testTask.WebCrawler.domain;

import java.util.Map;
import java.util.Set;

public class Level {


    private int id;
    private Set<String> levelLinks;
    private Map<String, Map<String, Integer>> levelResults;

    public Level() {
    }

    public Level(int id) {

        this.id = id;
    }

    public Level(int id, Set<String> levelLinks, Map<String, Map<String, Integer>> levelResults) {
        this.id = id;
        this.levelLinks = levelLinks;
        this.levelResults = levelResults;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<String> getLevelLinks() {
        return levelLinks;
    }

    public void setLevelLinks(Set<String> levelLinks) {
        this.levelLinks = levelLinks;
    }

    public Map<String, Map<String, Integer>> getLevelResults() {
        return levelResults;
    }

    public synchronized void setLevelResults(Map<String, Map<String, Integer>> levelResults) {
        this.levelResults = levelResults;
    }
}
