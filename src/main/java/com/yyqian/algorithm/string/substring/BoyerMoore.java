package com.yyqian.algorithm.string.substring;

/**
 * Created by yyqian on 6/16/16.
 *
 * 平均复杂度是 N/M
 * 最差时间复杂度是 MN
 *
 * 空间复杂度是 R
 */
public class BoyerMoore {
  private int[] right;
  private String pat;

  BoyerMoore(String pat) {
    this.pat = pat;
    int M = pat.length();
    int R = 256;
    // right 数组用来存 pattern 中某个字符出现在最右侧的index, 如果pattern 没有这个字符,则为 -1
    // 例如, 假设 pattern 是 aabachck, 那么:
    // right['x'] = -1, 因为 x 不在 pattern 中
    // right['a'] = 3
    // right['c'] = 6
    // right['k'] = 7
    // right['h'] = 5
    // right['b'] = 2
    // 利用这个 right 数组, 下面我们就能来判断可以 skip 多少字符
    right = new int[R];
    for (int c = 0; c < R; c++) {
      right[c] = -1; // -1 for chars not in pattern
    }
    for (int j = 0; j < M; j++) {
      right[pat.charAt(j)] = j; // 更新出现在最右侧的位置
    }
  }

  public int search(String txt) {
    int N = txt.length();
    int M = pat.length();
    int skip;
    for (int i = 0; i <= N-M; i += skip) {
      skip = 0;
      for (int j = M - 1; j >=0 ; j--) {
        if (pat.charAt(j) != txt.charAt(i+j)) {
          skip = j - right[txt.charAt(i+j)]; // 直接把 i 指向匹配的位置
          // 下一步 skip = 1 貌似是无奈之举, 我们要求 i 保持增长, 因为之前的序列都检查过没有匹配了, 所以不应该回去;
          // 但 right 只记录了最右侧的 char, 倒数第二第三没有记录, 否则我们可以有比 skip = 1 更好的选择;
          // 这里更好的办法是使用 KMP 类似的表格
          if (skip < 1) skip = 1;
          break;
        }
      }
      if (skip == 0) return i; // found
    }
    return -1;
  }

  public static void main(String[] args) {
    BoyerMoore boyerMoore = new BoyerMoore("stark");
    System.out.println(boyerMoore.search("tony stark is ironman"));
    System.out.println(boyerMoore.search("tony is not ironman"));
  }
}
