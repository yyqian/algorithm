package com.yyqian.algorithm;

import com.yyqian.algorithm.search.RedBlackTreeCLRS;
import org.junit.Test;

import static com.yyqian.algorithm.search.RedBlackTreeCLRS.RED;

/**
 * Created by yyqian on 5/21/16.
 */
public class RedBlackTreeCLRSTest {

  @Test
  public void insert() throws Exception {
    RedBlackTreeCLRS<Integer> tree = new RedBlackTreeCLRS<>();
    prepareTestData(tree);
    tree.inorderTreeWalkPretty(tree.root, "");
  }

  private void prepareTestData(RedBlackTreeCLRS<Integer> tree) {
    tree.insert(new RedBlackTreeCLRS.TreeNode<>(15, tree.NIL, tree.NIL, tree.NIL, RED));
    tree.insert(new RedBlackTreeCLRS.TreeNode<>(6, tree.NIL, tree.NIL, tree.NIL, RED));
    tree.insert(new RedBlackTreeCLRS.TreeNode<>(18, tree.NIL, tree.NIL, tree.NIL, RED));
    tree.insert(new RedBlackTreeCLRS.TreeNode<>(3, tree.NIL, tree.NIL, tree.NIL, RED));
    tree.insert(new RedBlackTreeCLRS.TreeNode<>(7, tree.NIL, tree.NIL, tree.NIL, RED));
    tree.insert(new RedBlackTreeCLRS.TreeNode<>(17, tree.NIL, tree.NIL, tree.NIL, RED));
    tree.insert(new RedBlackTreeCLRS.TreeNode<>(20, tree.NIL, tree.NIL, tree.NIL, RED));
    tree.insert(new RedBlackTreeCLRS.TreeNode<>(2, tree.NIL, tree.NIL, tree.NIL, RED));
    tree.insert(new RedBlackTreeCLRS.TreeNode<>(4, tree.NIL, tree.NIL, tree.NIL, RED));
    tree.insert(new RedBlackTreeCLRS.TreeNode<>(13, tree.NIL, tree.NIL, tree.NIL, RED));
    tree.insert(new RedBlackTreeCLRS.TreeNode<>(9, tree.NIL, tree.NIL, tree.NIL, RED));
  }
}