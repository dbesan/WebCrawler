package org.testTask.WebCrawler.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Parsing {
    /**
     * Document connectToPage(String url) - gets URL in string format, connecting to page,  return html page in document view
     */
    public static Document connectToPage(String url) throws IOException {
        Document document;
        try {
            document = Jsoup.connect(url)
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
        } catch (IllegalArgumentException | SocketTimeoutException | HttpStatusException | SSLException e) {
            document = null;
        }

        return document;
    }

    /**
     * String textFromPageString(Document document) gets Document with html-page parsing any document by Jsoup and
     * return String with all texts from page
     */
    public static String textFromPage(Document document) {
        List textList = new ArrayList();
        String text = "";
        try {
            text = document.body().text().toLowerCase();
        } catch (Exception e) {
            return text;
        }
        return text;
    }

    /**
     * Set hrefFromPage(Document document) gets Document with html-page parsing any document by Jsoup and return Set with all links from page
     * cuts off pieces of links with "#" to avoid adding not unique links with navigation implemented using "#"
     * e.g.
     * https://en.wikipedia.org/wiki/Elon_Musk#Education
     * and
     * https://en.wikipedia.org/wiki/Elon_Musk
     */
    public static Set hrefFromPage(Document document) {
        Set<String> hrefs = new LinkedHashSet();
        Elements links;
        try {
            links = document.select("a");
            links.stream().forEach(r -> {
                if (String.valueOf(r.attr("abs:href")) != "") {
                    hrefs.add(StringUtils.substringBefore(String.valueOf(r.attr("abs:href")), "#"));
                }
            });


        } catch (NullPointerException e) {
            return hrefs;
        }
        return hrefs;
    }
}
