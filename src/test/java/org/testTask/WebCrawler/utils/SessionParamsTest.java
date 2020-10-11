package org.testTask.WebCrawler.utils;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.testTask.WebCrawler.utils.SessionParams.*;

public class SessionParamsTest {
    @Test
    public void returnTrueIfURLisExpectedInternetConnectionNeeded() throws IOException {
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        Mockito.when(reader.readLine()).thenReturn("wikipedia.com");
        String actual = getURLParam(reader);
        String expected = "https://wikipedia.com";
        assertEquals(expected, actual);
    }

    @Test
    public void returnTrueIfLinkDeepParamIsExpected() throws IOException {
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        Mockito.when(reader.readLine()).thenReturn("3");
        int expected = 3;
        int actual = getLinkDeepParam(reader);
        assertEquals(actual, expected);
    }

    @Test
    public void returnTrueIfMaxVisitedPagesisExpected() throws IOException {
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        Mockito.when(reader.readLine()).thenReturn("99999");
        int expected = 99999;
        int actual = getMaximumLinksLimitParam(reader);
        assertEquals(actual, expected);
    }

    @Test
    public void testReadingFromConsole() throws IOException {
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        Mockito.when(reader.readLine()).thenReturn("wikipedia.com");
        String actual = readingFromConsole(reader);
        String expected = "wikipedia.com";
        assertEquals(actual, expected);
    }
}