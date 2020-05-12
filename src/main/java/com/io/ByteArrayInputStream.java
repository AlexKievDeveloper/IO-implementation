package com.io;

import java.io.IOException;
import java.io.InputStream;

public class ByteArrayInputStream extends InputStream {

    private byte[] array;
    private int index;

    public ByteArrayInputStream(byte[] array) {//
        this.array = array;
    }

    @Override
    public int read() throws IOException {
        if (index == array.length){
            return -1;
        }
        return array[index++];
    }

    @Override
    public int read(byte[] buffer) throws IOException {
        return read(buffer, 0, buffer.length);
    }
    //ЧИТАЕМ ИЗ ВХОДНОГО АРРЕЯ И ЗАПИСЫВАЕМ В БУФЕР С ПОЗИЦИИ OFF, LEN ЭЛЕМЕНТОВ()
    @Override
    public int read(byte[] buffer, int off, int len) throws IOException {
        int unreadedCount = array.length - index;
        if (array.length == index) {
            return -1; //массив байтов закончился НАМ НЕЧЕГО ЗАПИСАТЬ В БУФЕР
        }
        else if (len >= unreadedCount){ //количество элементов которое нужно считать больше или равно количеству
            // свободных мест во внутреннем буфере
        System.arraycopy(array, index, buffer, off, unreadedCount);
        index += unreadedCount;
        return unreadedCount;
        }
        else {
            System.arraycopy(array, index, buffer, off, len);
            index+= len;
            return len;
        }
    }
}

/*
    int unreadedCount = array.length - index;
        if (array.length == index) {
                return -1;
                }
                if (buffer.length > unreadedCount){
                System.arraycopy(array, index, buffer, 0, unreadedCount);
                index += unreadedCount;
                return unreadedCount;
                }
                else {
                System.arraycopy(array, index, buffer, 0, buffer.length);
                index += buffer.length;
                return buffer.length;
                }*/
