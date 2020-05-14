package com.io;

public class StringBufferedInputStream {
    protected String s;
    byte[] buffer;
    int count;

    public StringBufferedInputStream(String s) {
        this.s = s;
        buffer = s.getBytes();
    }

    public int read(){
        return buffer[count++];
    }

    public int read(byte[] b, int off, int len){
        int validBytes = buffer.length - count;
        if (validBytes > len){
            for (int i = 0; i < len; i++) {
                b[off++] = buffer[count];
            }
            return len;
        }
        else {
            for (int i = 0; i < validBytes; i++) {
                b[off++] = buffer[count];
            }
            return validBytes;
        }
    }
}
