package com.yyqian.algorithm.sort.helper;

import com.yyqian.algorithm.sort.*;

import java.util.Random;

/**
 * Created by yyqian on 5/22/16.
 */
public class SortCompare {

  private static void sort(String alg, Comparable[] a) {
    if (alg.equals("Insertion")) Insertion.sort(a);
    if (alg.equals("Selection")) Selection.sort(a);
    if (alg.equals("Shell")) Shell.sort(a);
    if (alg.equals("Merge")) Merge.sort(a);
    if (alg.equals("Quick")) Quick.sort(a);
    if (alg.equals("Heap")) Heap.sort(a);
  }

  private static long timeRandomInput(String alg, int N, int T) {
    long startTime = System.nanoTime();
    // Use alg to sort T random arrays of length N.
    Random rnd = new Random();
    Double[] a = new Double[N];
    for (int t = 0; t < T; t++) {
      // Perform one experiment (generate and sort an array).
      for (int i = 0; i < N; i++) {
        a[i] = rnd.nextDouble();
      }
      sort(alg, a);
    }
    long total = System.nanoTime() - startTime;
    System.out.printf("%s sort takes: %d\n", alg, total / (1000 * T));
    return total;
  }

  public static void main(String[] args) {
    int N = 100000;
    int T = 1000;
    String alg1 = "Insertion";
    String alg2 = "Selection";
    String alg3 = "Shell";
    String alg4 = "Merge";
    String alg5 = "Quick";
    String alg6 = "Heap";
    //double t1 = timeRandomInput(alg1, N, T);
    //double t2 = timeRandomInput(alg2, N, T);
    double t3 = timeRandomInput(alg3, N, T);
    double t4 = timeRandomInput(alg4, N, T);
    double t5 = timeRandomInput(alg5, N, T);
    double t6 = timeRandomInput(alg6, N, T);
    System.out.printf("For %d random Doubles\n%s is ", N, alg6);
    System.out.printf("%.1f times faster than %s\n", t3/t6, alg3);
  }
}
