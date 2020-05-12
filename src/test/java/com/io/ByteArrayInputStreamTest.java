package com.io;

import org.junit.Test;

//import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ByteArrayInputStreamTest {
    @Test
    public void testReadWithBuffer() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[] {3, 4, 5, 6, 7});

        byte[] buffer = new byte[3];
        int count = inputStream.read(buffer);
        assertEquals(3, count);
        assertEquals(3, buffer[0]);
        assertEquals(4, buffer[1]);
        assertEquals(5, buffer[2]);

        count = inputStream.read(buffer);
        assertEquals(2, count);
        assertEquals(6, buffer[0]);
        assertEquals(7, buffer[1]);

        count = inputStream.read(buffer);
        assertEquals(-1, count);
    }

    @Test
    public void testRead() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[] {3, 4, 5, 6, 7});
        for (int expectedValue = 3; expectedValue <= 7; expectedValue++) {
            assertEquals(expectedValue, inputStream.read());
        }
        assertEquals(-1, inputStream.read());
    }

    @Test
    public void testReadWithBufferAndOffset() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[] {3, 4, 5, 6, 7});

        byte[] buffer = new byte[3];
        int count = inputStream.read(buffer, 2, 1);
        assertEquals(1, count);
        assertEquals(3, buffer[2]);

        count = inputStream.read(buffer, 0, 3);
        assertEquals(3, count);
        assertEquals(4, buffer[0]);
        assertEquals(5, buffer[1]);
        assertEquals(6, buffer[2]);

        count = inputStream.read(buffer, 1, 2);
        assertEquals(1, count);
        assertEquals(7, buffer[1]);

        count = inputStream.read(buffer, 1, 2);
        assertEquals(-1, count);
    }
}