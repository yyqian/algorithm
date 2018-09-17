package com.yyqian.algorithm.string.substring;

import java.util.Arrays;

/**
 * Created by yyqian on 6/23/16.
 * 初级实现
 */
public class SuffixArray {
  private final String[] suffixes;
  private final int N;

  public SuffixArray(String str) {
    N = str.length();
    suffixes = new String[N];
    for (int i = 0; i < N; i++) {
      suffixes[i] = str.substring(i);
    }
    Arrays.sort(suffixes);
  }

  public int length() {
    return N;
  }

  public String select(int i) {
    return suffixes[i];
  }

  public int index(int i) {
    return N - suffixes[i].length();
  }

  public int lcp(int idx) {
    return lcp(suffixes[idx], suffixes[idx - 1]);
  }

  public int rank(String key) {
    int lo = 0;
    int hi = N - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      int cmp = key.compareTo(suffixes[mid]);
      if (cmp < 0) {
        hi = mid - 1;
      } else if (cmp > 0) {
        lo = mid + 1;
      } else {
        return mid;
      }
    }
    return lo;
  }

  private static int lcp(String strA, String strB) {
    int len = Math.min(strA.length(), strB.length());
    for (int i = 0; i < len; i++) {
      if (strA.charAt(i) != strB.charAt(i)) {
        return i;
      }
    }
    return len;
  }

}
