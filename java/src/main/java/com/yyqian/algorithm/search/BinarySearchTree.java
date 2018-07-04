package com.yyqian.algorithm.search;


/**
 * Created by yyqian on 5/20/16.
 *
 * From CLRS
 *
 * 除了遍历, 其他操作的复杂度都是 O(h)
 */
public class BinarySearchTree<T extends Comparable<T>> {

  public Node<T> root;

  // 遍历整颗二叉搜索树, O(n)
  public void inorderTreeWalk(Node<T> node) {
    if (node != null) {
      inorderTreeWalk(node.left);
      System.out.println(node.key);
      inorderTreeWalk(node.right);
    }
  }

  // 遍历整颗二叉搜索树, 输出漂亮, O(n)
  public void inorderTreeWalkPretty(Node<T> node, String padding) {
    if (node != null) {
      inorderTreeWalkPretty(node.right, padding + "   ");
      System.out.println(padding + node.key);
      inorderTreeWalkPretty(node.left, padding + "   ");
    }
  }

  // 二分法查找, 递归, O(h)
  public Node<T> treeSearch(Node<T> node, T key) {
    if (node == null || node.key.equals(key)) {
      return node;
    }
    if (key.compareTo(node.key) > 0) {
      return treeSearch(node.right, key);
    } else {
      return treeSearch(node.left, key);
    }
  }

  // 二分法查找, 迭代, O(h)
  public Node<T> iterativeTreeSearch(Node<T> node, T key) {
    while (node != null && !node.key.equals(key)) {
      if (key.compareTo(node.key) > 0) {
        node = node.right;
      } else {
        node = node.left;
      }
    }
    return node;
  }

  // 查找最小值, O(h), 找到最左的节点
  public Node<T> treeMinimum(Node<T> node) {
    while (node.left != null) {
      node = node.left;
    }
    return node;
  }

  // 查找最大值, O(h), 找到最右的节点
  public Node<T> treeMaximum(Node<T> node) {
    while (node.right != null) {
      node = node.right;
    }
    return node;
  }

  // 获得当前 treeNode 的下一个 treeNode（按值来排序）
  public Node<T> treeSuccessor(Node<T> node) {
    // 找到右侧分支的最小值
    if (node.right != null) {
      return treeMinimum(node.right);
    }
    // 往上找, 直到找到向右的拐点
    Node<T> parent = node.p;
    while (parent != null && parent.right == node) {
      node = parent;
      parent = parent.p;
    }
    return parent;
  }

  // 迭代法插入, O(h)
  public void treeInsert(BinarySearchTree<T> tree, Node<T> node) {
    if (node == null) {
      return;
    }
    if (tree.root == null) {
      tree.root = node;
      return;
    }
    Node<T> current = tree.root;
    Node<T> parent = null;
    // 遍历,直到找到合适的、空缺的位置,同时要记录上一个父节点
    while (current != null) {
      parent = current;
      if (node.key.compareTo(current.key) > 0) {
        current = current.right;
      } else {
        current = current.left;
      }
    }
    // 插入到父节点后面
    node.p = parent;
    if (node.key.compareTo(parent.key) > 0) {
      parent.right = node;
    } else {
      parent.left = node;
    }
  }

  // 删除一个 node, 这个操作比较繁琐, O(h)
  // 总的思路是用节点右侧最小值, 或者左侧最大值来替代删除节点的位置
  public void treeDelete(BinarySearchTree<T> tree, Node<T> node) {
    if (node == null) {
      return;
    }
    // 见 CLRS 297 页的图
    if (node.left == null) {
      transplant(tree, node, node.right);
    } else if (node.right == null) {
      transplant(tree, node, node.left);
    } else {
      Node<T> newNode = tree.treeMinimum(node.right);
      if (newNode.p != node) {
        transplant(tree, newNode, newNode.right);
        newNode.right = node.right;
        newNode.right.p = newNode;
      }
      transplant(tree, node, newNode);
      newNode.left = node.left;
      newNode.left.p = newNode;
    }
  }

  // 这一个思路是只替换值, 不替换对象本身
  public void treeDelete2(BinarySearchTree<T> tree, Node<T> node) {
    if (node == null) {
      return;
    }
    Node<T> newNode = tree.treeMaximum(node.left);
    node.key = newNode.key;
    if (node.p.left == node) {
      node.p.left = null;
    } else {
      node.p.right = null;
    }
  }

  // 用新的节点替代旧的节点
  private void transplant(BinarySearchTree<T> tree, Node<T> oldNode, Node<T> newNode) {
    if (oldNode.p == null) {
      tree.root = newNode;
    } else {
      if (oldNode == oldNode.p.left) {
        oldNode.p.left = newNode;
      } else {
        oldNode.p.right = newNode;
      }
    }
    if (newNode != null) {
      newNode.p = oldNode.p;
    }
  }

  public static final class Node<T> {
    public T key;
    public Node<T> left;
    public Node<T> right;
    public Node<T> p;

    public Node(T key, Node<T> left, Node<T> right, Node<T> p) {
      this.key = key;
      this.left = left;
      this.right = right;
      this.p = p;
    }

    @Override
    public String toString() {
      return "TreeNode{" +
          "key=" + key +
          '}';
    }
  }
}
