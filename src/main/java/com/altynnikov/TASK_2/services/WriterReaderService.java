package com.altynnikov.TASK_2.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class WriterReaderService {
    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    static {
        System.setOut(new PrintStream(outContent));
    }

    public static void sendIn(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static String readOut() {
        return outContent.toString();
    }

    public static void clearOut() {
        outContent.reset();
    }
}
