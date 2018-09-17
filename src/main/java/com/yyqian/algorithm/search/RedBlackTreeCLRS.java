package com.yyqian.algorithm.search;

/**
 * Created by yyqian on 5/21/16.
 *
 * From CLRS
 *
 * https://zh.wikipedia.org/wiki/%E7%BA%A2%E9%BB%91%E6%A0%91
 * 各种操作的复杂度都是 O(lg(n))
 * 1. 节点是红色或黑色
 * 2. 根是黑色
 * 3. 所有叶子都是黑色（叶子是 NIL 节点）
 * 4. 每个红色节点必须有两个黑色的子节点
 * 5. 从任一节点到其每个叶子的所有简单路径都包含相等数目的黑色节点
 */
public class RedBlackTreeCLRS<T extends Comparable<T>> {

  public TreeNode<T> root;
  public final TreeNode<T> NIL = new TreeNode<>(null, null, null, null, BLACK);
  public static final boolean RED = false;
  public static final boolean BLACK = true;

  public RedBlackTreeCLRS() {
    this.root = NIL;
  }

  public void leftRotate(TreeNode<T> p) {
    if (p != null) {
      TreeNode<T> r = p.right;
      // 把 p.right 指向 r.left
      p.right = r.left;
      if (r.left != null) {
        r.left.parent = p;
      }
      // 把 r.parent 指向 p.parent
      r.parent = p.parent;
      if (p.parent == null) {
        root = r;
      } else if (p.parent.left == p) {
        p.parent.left = r;
      } else {
        p.parent.right = r;
      }
      // y.left 指向 x
      r.left = p;
      p.parent = r;
    }
  }

  public void rightRotate(TreeNode<T> p) {
    if (p != null) {
      TreeNode<T> l = p.left;
      p.left = l.right;
      if (l.right != null) {
        l.right.parent = p;
      }
      l.parent = p.parent;
      if (p.parent == null) {
        root = l;
      } else if (p.parent.left == p) {
        p.parent.left = l;
      } else {
        p.parent.right = l;
      }
      l.right = p;
      p.parent = l;
    }
  }

  public void insert(TreeNode<T> z) {
    TreeNode<T> current = root;
    TreeNode<T> prev = NIL;
    while (current != NIL) {
      prev = current;
      if (z.key.compareTo(current.key) > 0) {
        current = current.right;
      } else {
        current = current.left;
      }
    }
    z.parent = prev;
    if (prev == NIL) {
      root = z;
    } else if (z.key.compareTo(prev.key) < 0) {
      prev.left = z;
    } else {
      prev.right = z;
    }
    z.left = NIL;
    z.right = NIL;
    z.color = RED;
    insertFixup(z);
  }

  public void insertFixup(TreeNode<T> n) {
    n.color = RED;
    while (n != NIL && n != root && n.parent.color == RED) {
      if (n.parent.parent.left == n.parent) {
        // P 在 G 的左侧
        TreeNode<T> u = n.parent.parent.right;
        if (u.color == RED) {
          // Case 3
          n.parent.color = BLACK; // P 染色
          u.color = BLACK; // U 染色
          n.parent.parent.color = RED; // G 染色
          n = n.parent.parent; // G 节点是红色的, 可能会破坏结构, 所以要把 n 指向 G 节点, 继续处理
        } else {
          if (n == n.parent.right) {
            // Case 4
            n = n.parent; // 下一步旋转后的结构还是不满足要求, 所以要把 n 指到正确的位置, 继续处理
            leftRotate(n);
          }
          // Case 5
          n.parent.color = BLACK; // 先染上正确的颜色
          n.parent.parent.color = RED;
          rightRotate(n.parent.parent); // 然后旋转, 旋转之后满足所有条件, 不需要继续处理
        }
      } else {
        // P 在 G 的右侧, 处理跟上面相同, 就方向相反
        TreeNode<T> u = n.parent.parent.left;
        if (u.color == RED) {
          n.parent.color = BLACK; // P
          u.color = BLACK; // U
          n.parent.parent.color = RED; // G
          n = n.parent.parent;
        } else {
          if (n == n.parent.left) {
            n = n.parent;
            rightRotate(n);
          }
          n.parent.color = BLACK;
          n.parent.parent.color = RED;
          leftRotate(n.parent.parent);
        }
      }
    }
    root.color = BLACK; // Case 1 和 2, 并且以上操作可能会将 root 染成红色
  }

  // 还没写, 太难了!!!!!!!!!
  public void delete(TreeNode<T> z) {
    if (z.left == NIL) {
      transplant(z, z.right);
    } else if (z.right == NIL) {
      transplant(z, z.left);
    } else {
      TreeNode<T> replaceNode = maximum(z.left);
      if (replaceNode.parent != z) {
        transplant(replaceNode, replaceNode.left);
        replaceNode.left = z.left;
        replaceNode.left.parent = replaceNode;
      }
      transplant(z, replaceNode);
      replaceNode.right = z.right;
      z.right.parent = replaceNode;
    }
  }

  // 用新的节点替代旧的节点
  private void transplant(TreeNode<T> oldNode, TreeNode<T> newNode) {
    if (oldNode.parent == NIL) {
      root = newNode;
    } else if (oldNode == oldNode.parent.left) {
      oldNode.parent.left = newNode;
    } else {
      oldNode.parent.right = newNode;
    }
    newNode.parent = oldNode.parent;
  }

  // 查找最大值, O(h), 找到最右的节点
  public TreeNode<T> maximum(TreeNode<T> node) {
    while (node.right != NIL) {
      node = node.right;
    }
    return node;
  }

  // 遍历整颗二叉搜索树, 输出漂亮, O(n)
  public void inorderTreeWalkPretty(TreeNode<T> node, String padding) {
    if (node != null) {
      inorderTreeWalkPretty(node.right, padding + "    ");
      String color = node.color == RED ? "R" : "B";
      System.out.println(padding + (node.key == null ? "NIL" : node.key) + "[" + color + "]");
      inorderTreeWalkPretty(node.left, padding + "    ");
    }
  }

  public static final class TreeNode<T> {
    public T key;
    public TreeNode<T> left;
    public TreeNode<T> right;
    public TreeNode<T> parent;
    public boolean color;

    public TreeNode(T key, TreeNode<T> left, TreeNode<T> right, TreeNode<T> parent, boolean color) {
      this.key = key;
      this.left = left;
      this.right = right;
      this.parent = parent;
      this.color = color;
    }

    @Override
    public String toString() {
      return "TreeNode{" +
          "key=" + key +
          '}';
    }
  }
}
