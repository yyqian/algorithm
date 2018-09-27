package com.yyqian.lang;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SystemTest {
    @Test
    public void playground() {
        long startNs = System.nanoTime();
        long startMs = System.currentTimeMillis();
        System.getenv().forEach((k, v) -> System.out.println(k + ":" + v));
        System.out.println("Path List:");
        System.out.println(Arrays.asList(System.getenv("PATH").split(";")).stream().collect(Collectors.joining("\n")));
        System.getProperties().list(System.out);
        int[] original = new int[10];
        int[] copy = new int[15];
        for (int i = 0; i < 10; ++i) {
            original[i] = i;
        }
        System.arraycopy(original, 1, copy, 3, 5);
        for (int i = 0; i < 10; ++i) {
            System.out.println(copy[i]);
        }
        System.out.println("elapsed: " + (double) (System.nanoTime() - startNs) / 1E9 + "s");
        System.out.println("elapsed: " + (double) (System.currentTimeMillis() - startMs) / 1E3 + "s");
    }
}
