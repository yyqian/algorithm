package com.yyqian.algorithm.string.compress;

import java.util.PriorityQueue;

/**
 * Created by yyqian on 6/19/16.
 *
 * <p>Huffman coding, 以下只完成了该算法的数据压缩的一部分</p>
 */
public class Huffman {
  private static int R = 256;

  private static class Node implements Comparable<Node> {
    private char ch;
    private int freq;
    private final Node left;
    private final Node right;

    public Node(char ch, int freq, Node left, Node right) {
      this.ch = ch;
      this.freq = freq;
      this.left = left;
      this.right = right;
    }

    public boolean isLeaf() {
      return left == null && right == null;
    }

    @Override
    public int compareTo(Node that) {
      return this.freq - that.freq;
    }
  }

  public static void main(String[] args) {
    String toCompress = "it was the best of times it was the worst of times";
    String compressed = compress(toCompress);
    System.out.println(compressed);
    System.out.println(compressed.length() + "");
  }

  public static String compress(String str) {
    char[] input = str.toCharArray();
    // 统计频率, Huffman coding 需要遍历两边输入的字符串, 第一遍用来统计词频
    int[] freq = new int[R];
    for (char c : input) {
      freq[c]++;
    }
    // 根据 freq 我们就能构造 Huffman 编码树
    Node root = buildTrie(freq);
    // 根据编码树构造编码表
    String[] st = buildCode(root);
    // 根据编码表处理输入的数据
    return process(st, input);
  }

  private static Node buildTrie(int[] freq) {
    // 我们用 PQ 优先合并 freq 较小的树, 这样 freq 小的树离根部更远, freq 大的离根部更近（所用的 code 的长度越短）
    PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
    for (char c = 0; c < R; c++) {
      if (freq[c] > 0) {
        // 每个 char 都构造成一棵树
        priorityQueue.add(new Node(c, freq[c], null, null));
      }
    }
    // 如果 PQ 中只剩一棵树, 说明构造完成
    while (priorityQueue.size() > 1) {
      // 从 PQ 取出 freq 最小的两棵树
      Node smallNode1 = priorityQueue.remove();
      Node smallNode2 = priorityQueue.remove();
      // 把两棵树合并成一棵树
      Node parent = new Node('\0', smallNode1.freq + smallNode2.freq, smallNode1, smallNode2);
      // 把合并之后的新的树加入 PQ
      priorityQueue.add(parent);
    }
    // 返回的是最后一棵树的 root
    return priorityQueue.remove();
  }

  // 压缩的时候用, 目的是根据 tree 获得一个 table, 可以将 char 转换为对应的 code
  private static String[] buildCode(Node root) {
    String[] st = new String[R];
    buildCode(st, root, "");
    return st;
  }

  // st 存的是每个 char 的 code, 例如 st['C'] = "1011"
  private static void buildCode(String[] st, Node node, String code) {
    if (node.isLeaf()) {
      // 如果当前是个叶子, 读取当前的 char, 结合当前的 code, 加到 st 中
      st[node.ch] = code;
      return;
    }
    // 递归地找下一层 node, 并且计算下一层 node 的 code
    buildCode(st, node.left, code + '0');
    buildCode(st, node.right, code + '1');
  }

  private static String process(String[] st, char[] input) {
    // 这里应该先要把 Trie 转换为二进制流, 写到输出流头部
    // 然后再输出字符串的长度
    // 下面的输出只是演示用的, 真实环境应该输出到二进制流
    String showInString = "";
    for (char c : input) {
      // 获得当前位置字符的 Huffman 编码
      String code = st[c];
      showInString += code;
    }
    return showInString;
  }
}
