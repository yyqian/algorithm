package com.yyqian.algorithm.search;

/**
 * Created by yyqian on 5/27/16.
 *
 * BST 的复杂度依赖于树的形状, 如果是完美平衡的, 那么复杂度是 O(lgN), 如果完全偏向一侧, 则为 O(N)
 * 所以它的效率严重依赖于 keys 插入的顺序, 这点跟 quicksort 类似
 *
 * Algorithms 版本的 BST 用的是递归版的, 主要是教学目的, 并且这个版本是针对 Symbol Table, 不允许有重复 key 的
 * 还有一些其他操作也是针对 Symbol Table 特有的, 所以和 CLRS 版本的有所不同（CLRS 的更简单）
 */
public class BST<K extends Comparable<K>, V> {

  private Node root;

  private class Node {
    private K key;
    private V value;
    private Node left;
    private Node right;
    private int N; // # nodes in subtree rooted here

    public Node(K key, V value, int n) {
      this.key = key;
      this.value = value;
      N = n;
    }
  }

  public int size() {
    return size(root);
  }

  public int size(Node x) {
    if (x == null) {
      return 0;
    } else {
      return x.N;
    }
  }

  // 迭代法
  public V get(K key) {
    Node node = root;
    while (node != null) {
      int cmp = key.compareTo(root.key);
      if (cmp < 0) node = node.left;
      else if (cmp > 0) node = node.right;
      else return node.value;
    }
    return null;
  }

  // 递归法
  public void put(K key, V val) {
    root = put(root, key, val);
  }

  // 如果不是新增的话, 还是返回节点参数本身
  // 如果是新增的, 则返回新增的节点
  private Node put(Node x, K key, V val) {
    if (x == null) return new Node(key, val, 1);
    int cmp = key.compareTo(root.key);
    if (cmp < 0) x.left = put(x.left, key, val);
    else if (cmp > 0) x.right = put(x.right, key, val);
    else x.value = val;
    x.N = size(x.left) + size(x.right) + 1;
    return x;
  }

  public K min() {
    return min(root).key;
  }

  private Node min(Node x) {
    if (x.left == null) return x;
    return min(x);
  }


}
