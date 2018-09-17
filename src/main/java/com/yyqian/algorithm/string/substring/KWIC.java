package com.yyqian.algorithm.string.substring;

/**
 * Created by yyqian on 6/23/16.
 */
public class KWIC {
  public static void main(String[] args) {
    String text = "huiasf fhe fe search sdf wejoi thing fwejoi search sjiof we thing joifew search fe thing";
    SuffixArray sa = new SuffixArray(text);
    String query = "search";
    int context = 5;
    for (int i = sa.rank(query); i < text.length() && sa.select(i).startsWith(query); i++) {
      int from = Math.max(0, sa.index(i) - context);
      int to = Math.min(text.length(), from + query.length() + 2 * context);
      System.out.println(text.substring(from, to));
    }
  }
}
