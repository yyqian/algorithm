package com.yyqian.algorithm.search;

/**
 * Created by yyqian on 6/25/16.
 * Algorithms 版本的 RBT, 经典!!!
 */
public class RedBlackTree<K extends Comparable<K>, V> {

  private Node root;

  private static final boolean RED = true;
  private static final boolean BLACK = false;

  public void put(K key, V val) {
    root = put(root, key, val);
    root.color = BLACK;
  }

  private Node put(Node node, K key, V val) {
    if (node == null) {
      return new Node(key, val, RED);
    }
    int cmp = key.compareTo(node.key);
    if (cmp < 0) {
      node.left = put(node.left, key, val);
    } else if (cmp > 0) {
      node.right = put(node.right, key, val);
    } else {
      node.val = val;
    }
    if (isRed(node.right) && !isRed(node.left)) {
      node = rotateLeft(node);
    }
    if (isRed(node.left) && isRed(node.left.left)) {
      node = rotateRight(node);
    }
    if (isRed(node.left) && isRed(node.right)) {
      flipColors(node);
    }
    return node;
  }

  private Node rotateLeft(Node parentNode) {
    Node rightNode = parentNode.right;
    parentNode.right = rightNode.left;
    rightNode.left = parentNode;
    rightNode.color = parentNode.color;
    parentNode.color = RED;
    return rightNode;
  }

  private Node rotateRight(Node parentNode) {
    Node leftNode = parentNode.left;
    parentNode.left = leftNode.right;
    leftNode.right = parentNode;
    leftNode.color = parentNode.color;
    parentNode.color = RED;
    return leftNode;
  }

  private void flipColors(Node node) {
    node.color = RED;
    node.left.color = BLACK;
    node.right.color = BLACK;
  }

  private boolean isRed(Node node) {
    return (null != node) && (RED == node.color);
  }

  private class Node {
    K key;
    V val;
    Node left;
    Node right;
    boolean color;

    Node(K key, V val, boolean color) {
      this.key = key;
      this.val = val;
      this.color = color;
    }
  }

}
