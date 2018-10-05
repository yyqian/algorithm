g++ -std=c++11 -Wall \
-I/usr/lib/jvm/java-8-openjdk-amd64/include \
-I/usr/lib/jvm/java-8-openjdk-amd64/include/linux \
-shared -o libocr.so -fPIC cpp/com_yyqian_OCR.cpp
cp libocr.so /usr/lib