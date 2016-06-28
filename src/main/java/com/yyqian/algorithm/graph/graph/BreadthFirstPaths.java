package com.yyqian.algorithm.graph.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * Created by yyqian on 5/30/16.
 *
 * 可用于计算类似 Linkedin 的 N 度人脉
 * 这个算法需要一个队列来维持节点探索的先后顺序
 */
public class BreadthFirstPaths {
  private boolean[] marked;
  private int[] edgeTo;
  private final int s; // source

  public BreadthFirstPaths(Graph g, int s) {
    marked = new boolean[g.V()];
    edgeTo = new int[g.V()];
    this.s = s;
    bfs(g, s);
  }

  // 如果一个节点有多个分支, bfs 将这几个连接的节点推到队列中, 保证它们探索的优先级要比它们的子节点更大
  // bfs 可以保证所有的路径都是最短路径。
  // 最差时间复杂度: V + E
  private void bfs(Graph G, int s) {
    // use offer, poll, peek
    Queue<Integer> queue = new ArrayDeque<>();
    marked[s] = true;
    queue.offer(s);
    while (!queue.isEmpty()) {
      int v = queue.poll();
      for (int w : G.adj(v)) {
        if (!marked[w]) {
          marked[w] = true;
          edgeTo[w] = v;
          queue.offer(w);
        }
      }
    }
  }

  public boolean hasPathTo(int v) {
    return marked[v];
  }

  public Iterable<Integer> pathTo(int v) {
    if (!hasPathTo(v)) return null;
    Deque<Integer> path = new ArrayDeque<>();
    for (int w = v; w != s; w = edgeTo[w]) {
      path.push(w);
    }
    path.push(s);
    return path;
  }
}
