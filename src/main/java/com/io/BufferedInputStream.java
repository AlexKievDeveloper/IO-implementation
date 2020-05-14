package com.io;

import java.io.IOException;
import java.io.InputStream;


public class BufferedInputStream extends InputStream {
    private final static int DEFAULT_BUFFER_SIZE = 10;
    private InputStream inputStream;

    private byte[] buffer;
    protected int position;//текущая позиция в буфере позиция байта который будет прочитан следующим

    public BufferedInputStream(InputStream inputStream){ //откуда будем читать байты
        buffer = new byte[DEFAULT_BUFFER_SIZE];
        this.inputStream = inputStream;
    }
    public BufferedInputStream(InputStream inputStream, int size){
        if (size <= 0) {
            throw new IllegalArgumentException("Incorrect buffer size: " + size);
        }
        this.inputStream = inputStream;
        buffer = new byte[size];
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }

    //разделим на те что не требуют очищения буфера (emptySpace > len) и на те что
    // требуют очищения буфера (emptySpace <= len)/создания нового/записи напрямую:

    public int read(byte[] b, int off, int len) throws IOException {
        //количество не заполненых позиций в буфере:
        int emptySpace = buffer.length - position;
        //ПРИ НАЛИЧИИ НЕОБХОДИМОГО МЕСТА В БУФЕРЕ ПИШЕМ В БУФЕР:
        if (emptySpace > len) {
            int index = position;
            int numberOfReadBytes = 0;
            for (int i = 0; i < len; i++) {
                int currentValue = inputStream.read();
                if (currentValue != -1) {
                    buffer[position++] = (byte) currentValue;
                    numberOfReadBytes++;
                }
            }
            System.arraycopy(buffer, index, b, off, len);
            return numberOfReadBytes;
        }
        //ЕСЛИ В БУФЕРЕ НЕДОСТАТОЧНО МЕСТА ТО ПИШЕМ НАПРЯМУЮ ИЗ ПОТОКА В МАCСИВ???
        else {
            int numberOfReadBytes = 0;
            for (int i = 0; i < len; i++) {
                int currentValue = (byte) inputStream.read();
                if (currentValue != -1) {
                    b[off++] = (byte) currentValue;
                    numberOfReadBytes++;
                }
                else return numberOfReadBytes;
            }
            return numberOfReadBytes;
        }
    }
}


/*
        int current = inputStream.read();//ИСПОЛЬЗУЕТСЯ МЕТОД ИЗ КЛАССА BYTEARRAY!!!!!
        //проверяем если считаный байт был равен -1 то значит входящий поток закончился и нам не нужно записывать
        // значение в буфер
        if (current == -1){
            return -1;
        }
        buffer[position] = (byte) current;
        return buffer[position++]; //возвращаем значение считаного байта из нашего массива байтов*/

/* if (emptySpace > len){
         for (int i = 0; i < len; i++) {
        buffer[count++] = (byte) inputStream.read();
        }
        System.arraycopy(buffer, position+1, b, off, len);
        return len;
        }
        else { //создать новый буфер или напрямую записать в массив b из потока?????
        buffer = new byte[len];
        for (int i = 0; i < len; i++) {
        buffer[i] = (byte) inputStream.read();
        }
        System.arraycopy(buffer, position+1, b, off, len);
        return len;
        }



        for (int i = 0; i < len; i++) {
        inputStream.read();
        }
        System.arraycopy(buffer, position, b, off, available());
        return len;
        }
        else { //создать новый буфер или напрямую записать в массив b из потока?????
        buffer = new byte[available()];
        for (int i = 0; i < available(); i++) {
        buffer[i] = (byte) inputStream.read();
        }
        System.arraycopy(buffer, position+1, b, off, available());
        return available();
        }*/

/*        public int read(byte[] buffer, int off, int len) throws IOException {
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

 // должен возвращать не количество считанных байтов, а значение
        // сначала считываем все байты, а потом записываем в буфер считываем из входящего потока и записываем в себя (массив buffer)
        // Читает по одному байту из входящего масива также как InputStream
        = inputStream.read(buffer);
        position++;*/ //cчитываем байт из потока в наш массив байтов

        //если количество элементов которые нужно записать во входящий массив равно или больше количества свободных мест
        //в нашем буфере то мы сбросим (запишем) все элементы находящиеся в буфере во входящий массив а потом напрямую
        //прочитаем из входящего потока оставшееся количество элементов?
        // position - количество заполненных позиций в буфере

/*
        if (emptySpace > len){  // если количество свободных мест в буфере больше чем количество элементов которые
            //необходимо записать то записываем элементы в буфер из потока
            for (int i = 0; i < len; i++) {
                buffer[count++] = (byte) inputStream.read();
            }
            System.arraycopy(buffer, position+1, b, off, len);
            return len;
        }

        else if (emptySpace <= len && available() >= len) {
            buffer = new byte[len];
            for (int i = 0; i < len; i++) {
                buffer[i] = (byte) inputStream.read();
            }
            System.arraycopy(buffer, position+1, b, off, available());
            return available();
        }


        else if (len >= available()){
            System.arraycopy(buffer, position+1, b, off, available());
            return available();
        }
        else {
            System.arraycopy(buffer, position+1, b, off, len);
            return len;


        //длинна массива из аргумента равна 0 или нет доступных байтов в потоке
        if (arg.length <= 0 || available() == 0) {
            return 0;
        }
        //внешний буфер меньше чем количество байтов в нашем потоке
        else if (arg.length < available()) {
            for (int i = 0; i < arg.length; i++) {
                arg[i] = (byte) inputStream.read();
            }
            return arg.length;
        }
        //внешний буфер больше чем количество байтов в нашем потоке
        else if (arg.length > available()){
            for (int i = 0; i < available(); i++) {
                arg[i] = (byte) inputStream.read();
            }
            return available();
        }
        return 0;


        public int read(byte[] arg) throws IOException {
        return read(arg, 0, arg.length);
    }
        */
