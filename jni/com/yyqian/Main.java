package com.yyqian;
import java.lang.management.ManagementFactory;

public class Main {
    public static void main(String args[]) {
        System.loadLibrary("ocr");
        System.out.println("Java process.id: " + ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
        System.out.println("Java thread.id: " + Thread.currentThread().getId());
        System.out.println("Java thread.name: " + Thread.currentThread().getName());
        OCR ocr = new OCR();
        for (int i = 0; i < 10; ++i) {
            System.out.println(ocr.parse("/path/to/file" + i));
        }
        for (int i = 0; i < 10; ++i) {
            int fi = i;
            new Thread(() -> System.out.println(ocr.parse("/path/to/file" + fi))).start();
        }
        for (int i = 0; i < 10; ++i) {
            int fi = i;
            new Thread(() -> System.out.println(OCR.parseStatic("/path/to/file" + fi))).start();
        }
    }
}