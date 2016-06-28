package com.yyqian.algorithm.search;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by yyqian on 5/27/16.
 *
 * 只存储了非 0 的值:
 * a[i][j] = val  =>  a[i].put(j, val)
 * a[i][j]  =>   a[i].get(j)
 *
 * 稀疏矩阵乘法实现:
 * ..
 * SparseVector[] a = new SparseVector[N];
 * double[] x = new double[N];
 * double[] b = new double[N];
 * ...
 * // Initialize a[] and x[].
 * ...
 * for (int i = 0; i < N; i++)
 *    b[i] = a[i].dot(x);
 */
public class SparseVector {
  private Map<Integer, Double> st;

  public SparseVector() {
    st = new HashMap<>();
  }

  // 把它放在 Hash Table 中
  public void put(int i, double x) {
    st.put(i, x);
  }

  public double get(int j) {
    return st.get(j);
  }

  public Set<Integer> keySet() {
    return st.keySet();
  }

  public boolean containsKey(int key) {
    return st.containsKey(key);
  }

  // 只计算非零值的乘法
  public double dot(SparseVector that) {
    double sum = 0.0;
    for (int i : st.keySet()) {
      if (that.containsKey(i)) sum += this.get(i) * that.get(i);
    }
    return sum;
  }

  public void show() {
    Set<Integer> keys = st.keySet();
    int max = Collections.max(keys);
    String result = "";
    for (int i = 0; i <= max; i++) {
      if (st.containsKey(i)) result += st.get(i) + " ";
      else result += "0 ";
    }
    System.out.println(result);
  }
}
