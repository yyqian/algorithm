package com.yyqian.algorithm.search;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by yyqian on 5/27/16.
 */
public class SparseMatrix {
  private Map<Integer, SparseVector> st;

  public SparseMatrix() {
    st = new HashMap<>();
  }

  // a[i][j] = x;
  public void put(int i, int j, double x) {
    if (st.containsKey(i)) {
      st.get(i).put(j, x);
    } else {
      SparseVector sv = new SparseVector();
      sv.put(j, x);
      st.put(i, sv);
    }
  }

  public double get(int i, int j) {
    return st.get(i).get(j);
  }

  public SparseVector dot(SparseVector that) {
    SparseVector sv = new SparseVector();
    for (int i : st.keySet()) {
      sv.put(i, st.get(i).dot(that));
    }
    return sv;
  }

  public void show() {
    Set<Integer> keys = st.keySet();
    int max = Collections.max(keys);
    String result = "";
    for (int i = 0; i <= max; i++) {
      if (st.containsKey(i)) st.get(i).show();
      else System.out.println("0");
    }
  }

  public static void main(String[] args) {
    SparseMatrix matrix = new SparseMatrix();
    SparseVector vector = new SparseVector();
    matrix.put(0, 1, 1.0);
    matrix.put(1, 0, 1.0);
    vector.put(0, 1.0);
    SparseVector result = matrix.dot(vector);
    matrix.show();
    vector.show();
    result.show();
  }
}
