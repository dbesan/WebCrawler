package org.testTask.WebCrawler.utils;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.testTask.WebCrawler.utils.WebUtils.pingUrl;

public class WebUtilsTest {
    @Test
    public void ifURLIsRightReturnTrueInternetConnectionNeeded() {
        String url = "wikipedia.org";
        boolean result = pingUrl(url);
        assertTrue(result);
    }

    @Test
    public void returnFalseURLIsNotValid() {
        String url = "wikipedia";
        boolean result = pingUrl(url);
        assertFalse(result);
    }
}