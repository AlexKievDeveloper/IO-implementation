package com.io;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ByteArrayOutputStreamTest {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    byte [] bytes;

    @Before
    public void setUp() throws IOException {
        String s = "Hello champ";
        bytes = s.getBytes();
    }

    @Test
    public void writeArray() throws IOException {
        byteArrayOutputStream.write(bytes);
        assertEquals(byteArrayOutputStream.toString(), "Hello champ");
    }

    @Test
    public void writeInt() throws IOException {
        assertEquals(byteArrayOutputStream.getIndex(), 0);
        byteArrayOutputStream.write(1);
        assertEquals(byteArrayOutputStream.getIndex(), 1);
    }
}