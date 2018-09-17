package com.yyqian.algorithm.graph.path;

/**
 * Created by yyqian on 6/1/16.
 *
 * 时间复杂度: E V log V
 */
public class DijkstraAllPairsSP {
  private DijkstraSP[] all;

  public DijkstraAllPairsSP(EdgeWeightedDigraph G) {
    all = new DijkstraSP[G.V()];
    for (int v = 0; v < G.V(); v++) {
      all[v] = new DijkstraSP(G, v);
    }
  }

  Iterable<DirectedEdge> path(int s, int t) {
    return all[s].pathTo(t);
  }

  double dist(int s, int t) {
    return all[s].distTo(t);
  }
}
