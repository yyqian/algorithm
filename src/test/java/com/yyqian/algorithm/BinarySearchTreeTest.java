package com.yyqian.algorithm;

import com.yyqian.algorithm.search.BinarySearchTree;
import com.yyqian.algorithm.search.BinarySearchTree.Node;
import org.junit.Test;

/**
 * Created by yyqian on 5/20/16.
 */
public class BinarySearchTreeTest {

  @Test
  public void treeMinimum() throws Exception {
    BinarySearchTree<Integer> tree = new BinarySearchTree<>();
    prepareTestData(tree);
    System.out.println("Minimum: " + tree.treeMinimum(tree.root));
  }

  @Test
  public void treeMaximum() throws Exception {
    BinarySearchTree<Integer> tree = new BinarySearchTree<>();
    prepareTestData(tree);
    System.out.println("Maximum: " + tree.treeMaximum(tree.root));
  }

  @Test
  public void treeSuccessor() throws Exception {
    BinarySearchTree<Integer> tree = new BinarySearchTree<>();
    prepareTestData(tree);
    Node<Integer> node = tree.root.left.right;
    System.out.println("Successor of " + node + ": " + tree.treeSuccessor(node));
  }

  @Test
  public void treeInsert() throws Exception {
    BinarySearchTree<Integer> tree = new BinarySearchTree<>();
    prepareTestData(tree);
    Node<Integer> newNode = new Node<>(11, null, null, null);
    tree.treeInsert(tree, newNode);
    System.out.println("Insert node: " + newNode);
    tree.inorderTreeWalkPretty(tree.root, "");
  }

  @Test
  public void treeDelete() throws Exception {
    BinarySearchTree<Integer> tree = new BinarySearchTree<>();
    prepareTestData(tree);
    Node<Integer> toDeleteNode = tree.iterativeTreeSearch(tree.root, 13);
    System.out.println("Delete node: " + toDeleteNode);
    tree.treeDelete(tree, toDeleteNode);
    tree.inorderTreeWalkPretty(tree.root, "");
  }

  @Test
  public void iterativeTreeSearch() throws Exception {
    BinarySearchTree<Integer> tree = new BinarySearchTree<>();
    prepareTestData(tree);
    System.out.println("IterativeTreeSearch: " + tree.iterativeTreeSearch(tree.root, 17).toString());
  }

  @Test
  public void treeSearch() throws Exception {
    BinarySearchTree<Integer> tree = new BinarySearchTree<>();
    prepareTestData(tree);
    System.out.println("TreeSearch: " + tree.treeSearch(tree.root, 17).toString());
  }

  @Test
  public void inorderTreeWalk() throws Exception {
    BinarySearchTree<Integer> tree = new BinarySearchTree<>();
    prepareTestData(tree);
    tree.inorderTreeWalk(tree.root);
  }

  private void prepareTestData(BinarySearchTree<Integer> tree) {
    tree.treeInsert(tree, new Node<>(15, null, null, null));
    tree.treeInsert(tree, new Node<>(6, null, null, null));
    tree.treeInsert(tree, new Node<>(18, null, null, null));
    tree.treeInsert(tree, new Node<>(3, null, null, null));
    tree.treeInsert(tree, new Node<>(7, null, null, null));
    tree.treeInsert(tree, new Node<>(17, null, null, null));
    tree.treeInsert(tree, new Node<>(20, null, null, null));
    tree.treeInsert(tree, new Node<>(2, null, null, null));
    tree.treeInsert(tree, new Node<>(4, null, null, null));
    tree.treeInsert(tree, new Node<>(13, null, null, null));
    tree.treeInsert(tree, new Node<>(9, null, null, null));
  }

}