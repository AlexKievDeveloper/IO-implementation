package com.io;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        byte[] bytes = new byte[]{10, 20, 50, 40, 60, 90, 70};
        byte[] bytesOut = new byte[10];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(bytes), 10);
       // bufferedInputStream.read();
        bufferedInputStream.read(bytesOut, 2, 7);
        for (int i = 0; i < bytesOut.length; i++) {
            System.out.println(bytesOut[i]);
        }
    }
}

/*
1) BufferedOutputStream++++
2) BufferedInputStream   1
3) ByteArrayOutputStream 0+
4) ByteArrayInputStream+++++
5) StringBufferedInputStream 2
 */