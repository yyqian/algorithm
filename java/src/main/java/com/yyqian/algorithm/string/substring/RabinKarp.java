package com.yyqian.algorithm.string.substring;

/**
 * Created by yyqian on 6/16/16.
 *
 * Rabin-Karp 算法原理跟 hashtable 类似:
 * 它用一个 hash 函数计算 pattern 字符串的 hash 值,
 * 然后每次右移一格地去计算 txt 相同长度的字符串的 hash 值, 跟 pattern hash 值比较
 * 这里关键的地方是 txt 每一段的 hash 值计算可以优化, 通过利用已计算的左邻 hash 值来大大减少 hash 计算的工作量
 * 并且这个算法没有额外的空间占用（因为我们不需要整个 hashtable, 只要保存上一次计算的 hash 值就可以了）
 *
 * 时间复杂度是 7N
 * 空间复杂度是 1
 */
public class RabinKarp {
  private String pat;
  private long patHash; // pattern hash
  private int M; // pattern length
  private long Q;
  private int R = 256;
  private long RM;

  public RabinKarp(String pat) {
    this.pat = pat;
    this.M = pat.length();
    Q = 997; // 注意: 如果下面的 check 用 Monte Carlo 算法, 这个 Q 必须是一个很大的素数, 大于 10^20
    RM = 1;
    // compute R^(M - 1) % Q
    for (int i = 1; i < M; i++) {
      RM = (R * RM) % Q;
    }
    patHash = hash(pat, M);
  }

  private long hash(String key, int M) {
    long h = 0;
    for (int j = 0; j < M; j++) {
      h = (R * h + key.charAt(j)) % Q;
    }
    return h;
  }

  private int search(String txt) {
    int N = txt.length();
    long txtHash = hash(txt, M);
    if (patHash == txtHash) return 0;
    // 这里 i 标的是截断的尾巴
    // 这一步是关键, 参照书本 775 页
    for (int i = M; i < N; i++) {
      txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q; // + Q 是为了防止出现负数, 这里计算的是 x_i - t_i * RM
      txtHash = (txtHash * R + txt.charAt(i)) % Q;
      if (patHash == txtHash)
        if (check(i - M + 1)) return i - M + 1;
    }
    return -1;
  }

  // 这里用的是 Monte Carlo 算法
  // Monte Carlo 实际上不检验文本中的字符串是否和 pattern 匹配, 原因是这里的 Q 会选择一个很大的素数（大于 10^20）
  // 不同字符串发生 hash collision 的概率非常非常小（小于 10^-40）
  private boolean check(int i) {
    return true;
  }
}
