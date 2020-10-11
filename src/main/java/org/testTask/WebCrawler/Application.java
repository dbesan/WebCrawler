package org.testTask.WebCrawler;

import java.io.IOException;

import static org.testTask.WebCrawler.utils.Crawler.createSession;


public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {

        createSession();
    }
}
