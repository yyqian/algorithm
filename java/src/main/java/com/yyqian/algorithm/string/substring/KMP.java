package com.yyqian.algorithm.string.substring;

/**
 * 著名的 KMP 算法, 有限自动机的典型应用
 *
 * 时间复杂度是: 1.1N ~ 2N
 * 空间复杂度是: MR
 */
public class KMP {

  private String pat;
  private int[][] dfa;

  public KMP(String pat) {
    this.pat = pat;
    buildDFA(); // 从 pattern 构造有限自动机
  }

  public int search(String txt) {
    int M = pat.length();
    int N = txt.length();
    int i, j;
    for (i = 0, j = 0; i < N && j < M; i++) {
      j = dfa[txt.charAt(i)][j]; // 使用有限自动机进行状态转换
    }
    if (j == M) return i - M;
    else return -1;
  }

  private void buildDFA() {
    int M = pat.length();
    int R = 256;
    dfa = new int[R][M]; // dfa[r][m] = val 中的 m 表示当前状态, r 表示输入, val 表示转换后的状态
    dfa[pat.charAt(0)][0] = 1;
    for (int X = 0, j = 1; j < M; j++) {
      for (int c = 0; c < R; c++) {
        dfa[c][j] = dfa[c][X]; // Copy mismatch cases. 因为 X 是 restart state, 如果不 match 就要回到 X, 这个时候将 c 作为 X 状态的输入, 得到最终的状态
      }
      dfa[pat.charAt(j)][j] = j + 1; // Set match case
      X = dfa[pat.charAt(j)][X]; // Update restart state
    }
  }

}
