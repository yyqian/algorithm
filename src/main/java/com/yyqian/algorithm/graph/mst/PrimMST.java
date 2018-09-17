package com.yyqian.algorithm.graph.mst;

import com.yyqian.algorithm.sort.helper.IndexMinPQ;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yyqian on 5/31/16.
 *
 * 这个算法最关键的是用一个数组来保存每个顶点到 MST 的最短的边
 * 这个数组会不断更新, 更新的情况是在 MST 扩张之后, 该新增顶点周围的顶点有可能离当前的 MST 更近了
 * 所以可以更新这个列表, 这个列表的更新也会伴随着 PQ 的更新（注意这里的 PQ 是特殊的, 要求是可以 index, 也可以更改 key）
 * 在完成一个点的标记以及相应的 edge list 的修改, 我们选择 PQ 中最短的边进行下一步扩张。
 *
 * 这个算法里 edge list 只保存了顶点数相同的 edge 数目
 * PQ 也最多只会保存顶点数相同的 edge 数目, 而 Lazy 版本保存了每个顶点所有的 edge
 *
 * 时间复杂度: E log V
 * 空间复杂度: V
 */
public class PrimMST {
  private Edge[] edgeTo; // 记录各个点到最小生成树最近的那个边
  private double[] distTo; // distTo[w] = edgeTo[w].weight()
  private boolean[] marked; // true if v on tree, 用来防止生成环
  // 这个 pq 只需要保存边的长度, 它可以保存 index, 而通过 index 可以从 edgeTo 获得当前边的对象
  private IndexMinPQ<Double> pq; // 需要实现 insert, isEmpty, delMin, change

  public PrimMST(EdgeWeightedGraph G) {
    edgeTo = new Edge[G.V()];
    distTo = new double[G.V()];
    marked = new boolean[G.V()];
    for (int v = 0; v < G.V(); v++) {
      distTo[v] = Double.POSITIVE_INFINITY;
    }
    pq = new IndexMinPQ<>(G.V());
    distTo[0] = 0.0;
    pq.insert(0, 0.0); // 以 0 点为起始点
    while (!pq.isEmpty()) {
      visit(G, pq.delMin());
    }
  }

  private void visit(EdgeWeightedGraph G, int v) {
    marked[v] = true;
    for (Edge e : G.adj(v)) {
      int w = e.other(v);
      if (marked[w]) continue;
      if (e.weight() < distTo[w]) {
        // w 顶点找到了离 MST 更近的一条边
        edgeTo[w] = e;
        distTo[w] = e.weight();
        // 如果 pq 中没有这条边, 则添加; 如果有, 则修改该顶点当前到 MST 的距离
        if (pq.containts(w)) pq.change(w, distTo[w]);
        else pq.insert(w, distTo[w]);
      }
    }
  }

  public Iterable<Edge> edges() {
    List<Edge> mst = new ArrayList<>();
    for (int v = 1; v < edgeTo.length; v++) {
      mst.add(edgeTo[v]);
    }
    return mst;
  }

}
