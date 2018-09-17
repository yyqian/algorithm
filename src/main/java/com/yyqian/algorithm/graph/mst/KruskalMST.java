package com.yyqian.algorithm.graph.mst;

import com.yyqian.algorithm.graph.mst.helper.AbstractUF;
import com.yyqian.algorithm.graph.mst.helper.WeightedQuickUnionUF;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by yyqian on 5/31/16.
 *
 * 这个算法先把 EdgeWeightedGraph 所有的 edges 都推到 MinPQ 中, 让它们按 weight 从小到大排序
 * 然后把 PQ 顶部的 edge 拿出来, 判断该 edge 两端的顶点是否已经连通（用 Union Find）
 * 如果连通了, 则略过
 * 如果没连通, 用 UF 把它们连通, 并且把该 edge 加到 MST 中
 * 然后再从 PQ 中取下一个
 */
public class KruskalMST {
  private Queue<Edge> mst;

  public KruskalMST(EdgeWeightedGraph G) {
    mst = new LinkedList<>();
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    AbstractUF uf = new WeightedQuickUnionUF(G.V());
    for (Edge e : G.edges()) {
      pq.add(e);
    }
    while (!pq.isEmpty() && mst.size() < G.V() - 1) {
      Edge e = pq.remove();
      int v = e.either();
      int w = e.other(v);
      // 这儿需要判断 v 和 w 是否连通, 这里不能通过 marked 来判断连通性
      // 因为即使 v 和 w 都标记了, 但如果它们还不属于同一个 Component, 就不是连通的
      // KruskalMST 算法在 MST 树生成过程中已加入 MST 的顶点不一定是连通的, 要到生成完毕才是完全连通的
      // 这一点和 Prim 算法不同, Prim 算法是从当前 MST 逐渐向外扩张的, 所以在过程中当前 MST 是完全连通的
      if (uf.connected(v, w)) continue; // Ignore ineligible edges.
      uf.union(v, w);
      mst.add(e);
    }
  }

  public Iterable<Edge> edges() {
    return mst;
  }
}
