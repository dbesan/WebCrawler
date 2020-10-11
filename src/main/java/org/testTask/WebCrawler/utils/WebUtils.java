package org.testTask.WebCrawler.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class WebUtils {
    /**
     * boolean pingUrl(String address) gets URL and validate it and checking for reachable, return boolean
     */
    public static boolean pingUrl(String address) {
        String temp = address;
        try {
            URL url = null;
            if (address.contains("https://") || address.contains("http://")) {
                url = new URL(address);
            } else if (!address.equals(null)) {
                url = new URL("https://" + address);
            } else {
                return false;
            }
            final HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(1000 * 10); // mTimeout is in seconds
            urlConn.connect();
            if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("Ping to " + address + " was success");
                return true;
            }
        } catch (final MalformedURLException ex) {
            System.out.println(ex.toString() + ex.getMessage());
            return false;
        } catch (UnknownHostException ex) {
            System.out.println("URL is not valid. URL:  " + ex.getMessage());
            return false;
        } catch (IllegalArgumentException ex) {
            System.out.println("URL is null");
            return false;
        } catch (final IOException e) {
            System.out.println(e.toString() + e.getMessage());
            return false;
        }
        return false;
    }
}
