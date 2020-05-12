package com.io;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        String s = "Hello champ you my new friend  ";
        byte [] bytes = s.getBytes();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(bytes);
        byteArrayOutputStream.write(10);
        System.out.println(byteArrayOutputStream.toString());
        System.out.println(s.length());
        System.out.println(byteArrayOutputStream.size());

    }
}
/*
1) BufferedOutputStream++++
2) BufferedInputStream   1
3) ByteArrayOutputStream 0
4) ByteArrayInputStream+++++
5) StringBufferedInputStream 2
 */