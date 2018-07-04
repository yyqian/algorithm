package com.yyqian.algorithm.graph.path;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yyqian on 5/31/16.
 */
public class EdgeWeightedDigraph {
  private final int V;
  private int E;
  private List<DirectedEdge>[] adj;

  public EdgeWeightedDigraph(int V) {
    this.V = V;
    this.E = 0;
    adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];
    for (int i = 0; i < V; i++) {
      adj[i] = new ArrayList<>();
    }
  }

  public void addEdge(DirectedEdge e) {
    adj[e.from()].add(e);
    E++;
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public Iterable<DirectedEdge> adj(int v) {
    return adj[v];
  }

  public Iterable<DirectedEdge> edges() {
    List<DirectedEdge> edges = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      for (DirectedEdge e : adj[i]) {
        edges.add(e);
      }
    }
    return edges;
  }
}
