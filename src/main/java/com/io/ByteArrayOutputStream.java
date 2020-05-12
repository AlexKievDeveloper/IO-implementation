package com.io;

import java.io.IOException;
import java.io.OutputStream;

public class ByteArrayOutputStream extends OutputStream {

    private final static int DEFAULT_BUFFER_SIZE = 32;
    private byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
    private int index;

    public ByteArrayOutputStream() {
    }

    public ByteArrayOutputStream(int size) {
        if (size != 0) {
            buffer = new byte[size];
        }
    }

    @Override
    public void write(int b) throws IOException {
        buffer[index++] = (byte) b;
        if (index == buffer.length){
            grow();
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        int freeSpace = buffer.length - index; //сколько осталось в буфере мест
        if (freeSpace <= len){ // если количество мест в буфере меньше чем предстоит вставить
            grow(len);
        }
        System.arraycopy(b, off, buffer, index, len);
        index += len;
    }

    public void writeTo(OutputStream out) throws IOException {
        out.write(buffer, 0, index);
    }

    private void grow() {
        byte[] increasedArray = new byte[buffer.length * 2];
        System.arraycopy(buffer, 0, increasedArray, 0, buffer.length);
        buffer = increasedArray;
    }

    private void grow(int lenOfInputArray) {
        byte[] increasedArray = new byte[lenOfInputArray * 2];
        System.arraycopy(buffer, 0, increasedArray, 0, buffer.length);
        buffer = increasedArray;
    }

    @Override
    public String toString(){
        return new String(buffer, 0, index);
    }

    public int size(){
        return buffer.length;
    }

    public int getIndex() {
        return index;
    }
}
