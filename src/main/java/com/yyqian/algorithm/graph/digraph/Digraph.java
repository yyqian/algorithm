package com.yyqian.algorithm.graph.digraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yyqian on 5/30/16.
 */
public class Digraph {
  private final int V;
  private int E;
  private List<Integer>[] adj;

  public Digraph(int V) {
    this.V = V;
    this.E = 0;
    adj = (ArrayList<Integer>[]) new ArrayList[V];
    for (int v = 0; v < V; v++) {
      adj[v] = new ArrayList<>();
    }
  }

  public void addEdge(int v, int w) {
    adj[v].add(w);
    E++;
  }

  public Iterable<Integer> adj(int v) {
    return adj[v];
  }

  public Digraph reverse() {
    Digraph digraph = new Digraph(V);
    for (int v = 0; v < V; v++) {
      for (int w : adj[v]) {
        digraph.addEdge(w, v);
      }
    }
    return digraph;
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }
}
