package com.yyqian.lang;

public class MyArrayList<T> {
    private Object[] data;
    /**
     * the number of elements it contains
     */
    private int size;

    public MyArrayList(int cap) {
        this.data = new Object[cap];
        this.size = 0;
    }

    public boolean add(T e) {
        if (size == data.length) {
            System.out.println("grow from " + size + " to " + size * 2);
            Object[] copyData = new Object[size * 2];
            System.arraycopy(data, 0, copyData, 0, size);
            data = copyData;
        }
        data[size++] = e;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        T old = (T)data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
        return old;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T)data[index];
    }

    public int size() {
        return this.size;
    }
}
