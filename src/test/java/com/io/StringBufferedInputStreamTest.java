package com.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StringBufferedInputStreamTest {
    String s = "Hello Java";
    char[] chars = s.toCharArray();
    byte[] bytes = s.getBytes();
    BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(bytes));

    @BeforeEach
    void setUp() throws IOException {

    }

    @Test
    void read() throws IOException {
        for (int i = 0; i < s.length(); i++) {
            assertEquals((char)bufferedInputStream.read(), chars[i]);
        }
    }

    @Test
    void testRead() throws IOException {
        byte[] bytesOut = new byte[10];
        bufferedInputStream.read(bytesOut, 5, 5);
        for (int i = 0; i < 5; i++) {
            assertEquals(bytesOut[i + 5], chars[i]);
        }

    }
}