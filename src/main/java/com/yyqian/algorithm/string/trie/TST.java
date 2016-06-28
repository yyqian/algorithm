package com.yyqian.algorithm.string.trie;

/**
 * Created by yyqian on 6/15/16.
 *
 * TST 的优势在于空间复杂度正比于 N 至 Nw, 与字典大小无关, 适用于类似 Unicode 这种大字符集
 */
public class TST<V> {

  private Node root;

  private class Node {
    char c;
    Node left, mid, right;
    V val;
  }

  public V get(String key) {
    Node node = get(root, key, 0);
    return node == null ? null : node.val;
  }

  private Node get(Node x, String key, int d) {
    if (x == null) return null;
    char c = key.charAt(d);
    if (c < x.c) {
      return get(x.left, key, d);
    } else if (c > x.c) {
      return get(x.right, key, d);
    } else if (d < key.length() - 1) {
      return get(x.mid, key, d+1);
    } else {
      return x;
    }
  }

  public void put(String key, V val) {
    put(root, key, val, 0);
  }

  private Node put(Node x, String key, V val, int d) {
    char c = key.charAt(d);
    if (x == null) {
      x = new Node();
      x.c = c;
    }
    if (c < x.c) {
      x.left = put(x.left, key, val, d);
    } else if (c > x.c) {
      x.right = put(x.right, key, val, d);
    } else if (d < key.length() -1) {
      x.mid = put(x.mid, key, val, d+1);
    } else {
      x.val = val;
    }
    return x;
  }
}
