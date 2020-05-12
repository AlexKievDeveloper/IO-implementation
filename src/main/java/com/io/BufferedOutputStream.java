package com.io;

import java.io.IOException;
import java.io.OutputStream;

public class BufferedOutputStream extends OutputStream {
    private final static int DEFAULT_BUFFER_SIZE = 5;

    OutputStream outputStream;
    private byte[] buffer;
    private int count; //количество элементов в буфере


    public BufferedOutputStream(OutputStream outputStream) {
        this (outputStream, DEFAULT_BUFFER_SIZE);
    }

    public BufferedOutputStream(OutputStream outputStream, int bufferedSize) {
        if (bufferedSize <= 0) {
            throw new IllegalArgumentException("Incorrect buffer size: " + bufferedSize);
        }
        this.outputStream = outputStream;
        this.buffer = new byte[bufferedSize];
    }


    @Override
    public void write(int value) throws IOException { // в буфер записывается переданное значение. Если буфер
        // переполнен то в исходящий поток записываются данные в буфер с нуля по количество данных в буфере

        buffer[count++] = (byte) value;
        if (count == buffer.length){
           flush();
        }
    }

    @Override
    public void write(byte[] array) throws IOException {
        write(array, 0, array.length);
    }

    @Override
    public void write(byte[] array, int off, int len) throws IOException {//ЗАПИСЫВАЕМ ДАНЫЕ ИЗ МАССИВА ARRAY В НАШ OUTPUT STREAM
        int emptySpace = array.length - count; //если длинна массива который нужно записать в поток -
        //количество элементов находящихся в буфере = количество свободных мест в буфере
        if (emptySpace <= len){ // если количество элементов которые необходимо записать больше или равно свободному
            //месту в буфере то мы
            flush(); // отправляем все элементы из буфера в исходящий поток и записываем в поток напрямую без
            outputStream.write(array, off, len); //буфера весь указанный диапазон чисел из входящего массива или
        }
        else {//копируем из входящего массива указанный диапазон числел в наш буфер с позиции каунт
            System.arraycopy(array, off, buffer, count, len);
            count += len;
        }
    }

    @Override
    public void flush() throws IOException {
        outputStream.write(buffer, 0, count);
        count = 0;
    }

    @Override
    public void close() throws IOException {
        flush();
        outputStream.close();
    }
}
