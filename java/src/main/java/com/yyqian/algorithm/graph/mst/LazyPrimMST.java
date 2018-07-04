package com.yyqian.algorithm.graph.mst;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by yyqian on 5/31/16.
 *
 * 最小生成树, 保证所有的边的权重加起来最小
 * 这个算法从 0 点出发, 标记 0 点, 然后把 0 点周围所有的 edge 加到 PQ 中
 * 去除 PQ 中最短的 edge, 访问另外一个没被标记的节点（如果被标记则略过, 因为当前 MST 树所有的点都是相互连通的, 如果连上就会形成环）
 * 每次访问都标记该点, 并且把它周围所有有效的边都加到 PQ 中
 *
 * 这个算法称为 Lazy 的原因是:
 * 新加入 MST 的顶点有可能会使得 MinPQ 中之前加入的有效的边变得无效, 也就是说 MinPQ 中有的边连接了新的顶点和 MST 中的其他顶点
 * 该算法不会主动去清除这些无效的边, 而是在后面当把边从队列中取出时, 再次检查边是否有效
 *
 * 这个算法空间复杂度是 O(E)，时间复杂度是 O(ElgE)
 */
public class LazyPrimMST {
  private boolean[] marked; // MST vertices
  private Queue<Edge> mst; // MST edges
  private PriorityQueue<Edge> pq; // 默认是 MinPQ

  public LazyPrimMST(EdgeWeightedGraph G) {
    pq = new PriorityQueue<>();
    mst = new LinkedList<>();
    marked = new boolean[G.V()];

    visit(G, 0); // 从 0 点出发, 将连接 0 点所有的 edge 都 push 到 pq 中
    while (!pq.isEmpty()) {
      Edge e = pq.remove(); // remove the heap root(minimum) 把最小的 edge 取出
      int v = e.either(); // 获取该 edge 的两个顶点
      int w = e.other(v);
      // 正常在 edge 被 push 到队列中的时候, 只有源点被 mark 了, 另外一个点会检查确保没被 mark
      // 但是在从队列中取出的时候, 有可能这个 edge 已经变成无效了（因为另外个顶点已经被加到 MST 中）,所以这里要排除这种无效的 edge
      if (marked[v] && marked[w]) continue;
      // 如果这个边有效, 就加入 MST
      mst.add(e);
      // 从新加入的顶点开始继续探索生成 MST
      if (!marked[v]) visit(G, v);
      if (!marked[w]) visit(G, w);
    }
  }

  private void visit(EdgeWeightedGraph G, int v) {
    // 标记该顶点, 把该顶点周围所有有效的边都加到 MinPQ 中
    marked[v] = true;
    for (Edge e : G.adj(v)) {
      if (!marked[e.other(v)]) pq.add(e);
    }
  }

  public Iterable<Edge> edges() {
    return mst;
  }
}
