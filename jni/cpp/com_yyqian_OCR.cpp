#include "com_yyqian_OCR.h"
#include <iostream>
#include <sstream>
#include <string>
#include <pthread.h>
#include "unistd.h"

using namespace std;

void ocr(const char* path, string &res) {
    ostringstream oss;
    oss << '[' << getpid() << ']';
    oss << '[' << pthread_self() << ']';
    oss << '[' << path << ']';
    oss << "{\"status\":0,\"apno\":\"CN201338923.X\"}";
    res = oss.str();
}

JNIEXPORT jstring JNICALL Java_com_yyqian_OCR_parse (JNIEnv *env, jobject obj, jstring path_id) {
    const char* path = env->GetStringUTFChars(path_id, 0);
    string res;
    ocr(path, res);
    return env->NewStringUTF(res.c_str());
}

JNIEXPORT jstring JNICALL Java_com_yyqian_OCR_parseStatic (JNIEnv *env, jclass obj, jstring path_id) {
    const char* path = env->GetStringUTFChars(path_id, 0);
    string res;
    ocr(path, res);
    return env->NewStringUTF(res.c_str());
}
