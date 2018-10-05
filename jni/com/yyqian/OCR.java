package com.yyqian;
public class OCR {
    static {
        System.loadLibrary("ocr_jni");
    }
    public native String parse(String path);
    public native static String parseStatic(String path);
}