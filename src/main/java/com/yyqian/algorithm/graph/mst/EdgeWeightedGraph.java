package com.yyqian.algorithm.graph.mst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yyqian on 5/30/16.
 */
public class EdgeWeightedGraph {

  private int V;
  private int E;
  private List<Edge>[] adj;

  public EdgeWeightedGraph(int V) {
    this.V = V;
    this.E = 0;
    adj = (ArrayList<Edge>[]) new ArrayList[V];
    for (int i = 0; i < V; i++) {
      adj[i] = new ArrayList<>();
    }
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public void addEdge(Edge e) {
    int either = e.either();
    int other = e.other(either);
    adj[either].add(e);
    adj[other].add(e);
    E++;
  }

  public Iterable<Edge> adj(int V) {
    return adj[V];
  }

  public Iterable<Edge> edges() {
    List<Edge> edges = new ArrayList<>();
    for (int v = 0; v < V; v++) {
      for (Edge e : adj[v]) {
        if (e.other(v) > v) edges.add(e); // 只加一次, 避免重复
      }
    }
    return edges;
  }
}
