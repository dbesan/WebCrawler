package org.testTask.WebCrawler.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.IOException;

import static org.testTask.WebCrawler.constants.Constants.*;

public class SessionParams {
    /**
     * String getURLParam(BufferedReader reader) - request url from console, check it and return String url.
     */
    public static String getURLParam(BufferedReader reader) throws IOException {
        boolean isValid = false;
        String url = "";
        while (!isValid) {
            System.out.println("Please, set seed url's:");
            url = readingFromConsole(reader);
            isValid = WebUtils.pingUrl(url);
        }
        if (!url.equals("") && !(url.contains("http://") || url.contains("https://"))) {
            url = "https://" + url;
        }
        return url;
    }

    /**
     * int getLinkDeepParam() - request link deep from console and return int.
     */
    public static int getLinkDeepParam(BufferedReader reader) throws IOException {
        System.out.println(CONSOLE_DELIMITER);
        System.out.println("Please, set link deep or press ENTER for default. Default is: " + MAX_DEPTH_DEFAULT);
        int linkDeep;
        String temp = readingFromConsole(reader);
        boolean isValid = NumberUtils.isParsable(temp);
        if (isValid) {
            linkDeep = Integer.parseInt(temp);
            return linkDeep;
        } else if (temp.equals("")) {
            linkDeep = MAX_DEPTH_DEFAULT;
            return linkDeep;
        } else {
            System.out.println("Is not valid.");
            linkDeep = getLinkDeepParam(reader);
        }

        return linkDeep;
    }

    /**
     * int getMaximumLinksLimitParam() - max visited pages from console and return int.
     */
    public static int getMaximumLinksLimitParam(BufferedReader reader) throws IOException {
        System.out.println(CONSOLE_DELIMITER);
        System.out.println("Please, set maximum visited pages or press ENTER for default. Default is: " + MAX_VISITED_PAGES);
        int maximumLinksLimitParam;
        String temp = readingFromConsole(reader);
        boolean isValid = NumberUtils.isParsable(temp);
        if (isValid) {
            maximumLinksLimitParam = Integer.parseInt(temp);
            return maximumLinksLimitParam;
        } else if (temp.equals("")) {
            maximumLinksLimitParam = MAX_VISITED_PAGES;
            return maximumLinksLimitParam;
        } else {
            System.out.println("Is not valid.");
            maximumLinksLimitParam = getLinkDeepParam(reader);
        }
        return maximumLinksLimitParam;
    }

    /**
     * readingFromConsole(BufferedReader reader) - return param in string, extracted for convenience when testing
     */
    public static String readingFromConsole(BufferedReader reader) throws IOException {
        return reader.readLine();
    }
}
