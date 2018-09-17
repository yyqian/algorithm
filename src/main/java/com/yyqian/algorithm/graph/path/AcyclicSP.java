package com.yyqian.algorithm.graph.path;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by yyqian on 6/1/16.
 *
 * 这个算法跟 Dijkstra 不同是: Dijkstra 通过 IndexPQ 来判断顶点的扩张顺序, 而这个算法利用 Acyclic 的特殊性,
 * 先获得 Topological Sort, 然后按这个 sort 来进行扩张
 * 这样可行的原因是, 如果按 Topological 的顺序 relax 的话, 每个点只会被 relax 一次
 * 一旦某个点 v 被 relax 之后, distTo[v] 就不会再变了, 因为无环的性质决定了它不会再被访问一次了。
 *
 * 时间复杂度: E + V
 *
 * 这个算法可以轻易改成最长路径算法, 最长路径可以用于规划并行任务的计算顺序, 例如某个任务 A 依赖于三条任务线, 那么只有当这
 * 三条任务线都完成了, 才能执行任务 A, 所以这三条任务线中最关键的是运行时间最慢的（也就是最长的）任务线,
 * 因此, 规划并行任务的问题实际就是寻找各个点的最长路径的问题
 */
public class AcyclicSP implements SP {

  private DirectedEdge[] edgeTo;
  private double[] distTo;

  public AcyclicSP(EdgeWeightedDigraph G, int s) {
    edgeTo = new DirectedEdge[G.V()];
    distTo = new double[G.V()];
    for (int v = 0; v < G.V(); v++) {
      distTo[v] = Double.POSITIVE_INFINITY;
    }
    distTo[s] = 0.0;
    // 这个地方 Topological 要改一下, 改成可以接受 EdgeWeightedDigraph
    /*
    Topological top = new Topological(G);
    for (int v : top.order()) {
      relax(G, v);
    }
    */
  }

  private void relax(EdgeWeightedDigraph G, int v) {
    for (DirectedEdge e : G.adj(v)) {
      int w = e.to();
      if (distTo[w] > distTo[v] + e.weight()) {
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
        // Dijkstra 算法在这之后还要对 PQ 进行调整, 而这儿不需要, 因为我们是按照 Topological Order 来 relax 各个点的
      }
    }
  }

  @Override
  public double distTo(int v) {
    return distTo[v];
  }

  @Override
  public boolean hashPathTo(int v) {
    return distTo[v] < Double.POSITIVE_INFINITY;
  }

  @Override
  public Iterable<DirectedEdge> pathTo(int v) {
    if (!hashPathTo(v)) return null;
    Deque<DirectedEdge> path = new LinkedList<>();
    for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
      path.addFirst(e);
    }
    return path;
  }
}
