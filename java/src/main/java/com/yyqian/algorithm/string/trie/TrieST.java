package com.yyqian.algorithm.string.trie;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yyqian on 6/15/16.
 *
 * 查找和插入的时间复杂度正比于 key 的长度
 * 删除的时间复杂度与 key 的长度无关
 * 空间复杂度介于 RN 和 RNw
 */
public class TrieST<V> {
  private static int R = 256;
  private Node root;

  private static class Node {
    private Object val; // val 保存的是词条的 id
    private Node[] next = new Node[R];
  }

  @SuppressWarnings("unchecked")
  public V get(String key) {
    Node x = get(root, key, 0);
    if (x == null) return null;
    return (V)x.val;
  }

  private Node get(Node x, String key, int d) {
    if (x == null) return null;
    if (d == key.length()) return x;
    char c = key.charAt(d);
    return get(x.next[c], key, d + 1);
  }

  public void put(String key, V val) {
    root = put(root, key, val, 0);
  }

  private Node put(Node x, String key, V val, int d) {
    if (x == null) x = new Node();
    if (d == key.length()) {
      x.val = val;
      return x;
    }
    char c = key.charAt(d);
    x.next[c] = put(x.next[c], key, val, d + 1);
    return x;
  }

  // don't use this lazy version in production
  private int size(Node x) {
    if (x == null) return 0;
    int sum = 0;
    if (x.val != null) sum++;
    for (char c = 0; c < R; c++) {
      sum += size(x.next[c]);
    }
    return sum;
  }

  public Iterable<String> keys() {
    return keysWithPrefix("");
  }

  public Iterable<String> keysWithPrefix(String pre) {
    Queue<String> q = new LinkedList<>();
    collect(get(root, pre, 0), pre, q);
    return q;
  }

  private void collect(Node x, String pre, Queue<String> q) {
    if (x == null) return;
    if (x.val != null) q.add(pre); // val 保存的是词条的 id
    for (char c = 0; c < R; c++) {
      collect(x.next[c], pre + c, q);
    }
  }

  public Iterable<String> keysThatMatch(String pat) {
    Queue<String> q = new LinkedList<>();
    collect(root, "", pat, q);
    return q;
  }

  // 搜索, 用 . 来表示任意一个字符
  private void collect(Node x, String pre, String pat, Queue<String> q) {
    if (x == null) return;
    int d = pre.length();
    if (d == pat.length() && x.val != null) q.add(pre);
    if (d == pat.length()) return;
    char next = pat.charAt(d);
    for (char c = 0; c < R; c++) {
      if (next == '.' || next == c) {
        collect(x.next[c], pre + c, pat, q);
      }
    }
  }

  // 返回 trie 树中匹配 s 的最长的 prefix
  public String longestPrefixOf(String s) {
    int length = search(root, s, 0, 0);
    return s.substring(0, length);
  }

  // length 保存当前 prefix 最长的长度
  private int search(Node x, String s, int d, int length) {
    // 如果 trie 树已经结束, 就返回之前的 length
    if (x == null) return length;
    // 如果当前的 node 指向一个记录, 则更新当前的长度
    if (x.val != null) length = d;
    // 如果 s 的长度已经达到, 说明 s 本身就是最长的 prefix
    if (d == s.length()) return length;
    // 继续探索下一个字符
    char c = s.charAt(d);
    return search(x.next[c], s, d+1, length);
  }

  public void delete(String key) {
    root = delete(root, key, 0);
  }

  private Node delete(Node x, String key, int d) {
    if (x == null) return null;
    if (d == key.length()) {
      x.val = null;
    } else {
      char c = key.charAt(d);
      x.next[c] = delete(x.next[c], key, d+1);
    }
    if (x.val != null) return x;
    for (char c = 0; c < R; c++) {
      if (x.next[c] != null) return x;
    }
    return null;
  }
}
