package com.yyqian.lang;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyArrayListTest {

    @Test
    public void test() {
        MyArrayList<Integer> list = new MyArrayList<>(1);
        for (int i = 0; i < 20; ++i) {
            list.add(i);
        }
        for (int i = 0; i < 20; ++i) {
            System.out.println(list.get(i));
        }
    }

}