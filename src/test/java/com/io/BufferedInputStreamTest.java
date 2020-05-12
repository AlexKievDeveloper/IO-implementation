package com.io;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
//import java.io.BufferedInputStream;

import org.junit.Before;
import org.junit.Test;

public class BufferedInputStreamTest {
    BufferedInputStream bufferedInputStream;
    byte[] bytes;
    @Before
    public void setUp(){
        bytes = new byte[]{10, 20, 50, 40, 60, 90, 70};
        bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(bytes));
    }

    @Test
    public void read() throws IOException {
        for (int i = 0; i < bytes.length; i++) {
            assertEquals(bytes[i], bufferedInputStream.read());
        }
        assertEquals(-1, bufferedInputStream.read());
    }
}