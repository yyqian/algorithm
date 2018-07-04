package com.yyqian.algorithm.string.substring;

/**
 * Created by yyqian on 6/23/16.
 */
public class LRS {
  public static void main(String[] args) {
    String text = "to be or not to be.";
    SuffixArray sa = new SuffixArray(text);
    String lrs = "";
    for (int i = 1; i < sa.length(); i++) {
      int len = sa.lcp(i);
      if (len > lrs.length()) {
        lrs = sa.select(i).substring(0, len);
      }
    }
    System.out.println(lrs);
  }
}
