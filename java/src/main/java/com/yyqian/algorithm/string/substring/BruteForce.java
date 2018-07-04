package com.yyqian.algorithm.string.substring;

/**
 * Created by yyqian on 6/15/16.
 *
 * 最坏情况的时间复杂度: NM
 */
public class BruteForce {
  public static int search(String pat, String txt) {
    int M = pat.length();
    int N = txt.length();
    for (int i = 0; i <= N - M; i++) {
      int j;
      for (j = 0; j < M; j++) {
        if (pat.charAt(j) != txt.charAt(i + j)) break;
      }
      if (j == M) return i; // found
    }
    return N; //not found
  }

  public static int alternateSearch(String pat, String txt)
  {
    int M = pat.length();
    int N = txt.length();
    int i = 0;
    int j = 0;
    while (i < N && j < M) {
      if (txt.charAt(i) == pat.charAt(j)) {
        i++;
        j++;
      }
      else {
        // 如果 j 个字符之后发现不符合, 则回退
        i = i + 1 - j;
        j = 0;
      }
    }
    if (j == M) {
      return i - M; // found
    } else {
      return -1; // not found
    }
  }

  public static void main(String[] args) {
    String pat = "ironman";
    String txt = "stark is ironman";
    System.out.println(alternateSearch(pat, txt));
    System.out.println(alternateSearch(pat, "hiauhsd iuhiuqhwdn uiqwd"));
  }
}
