package com.yyqian.algorithm.graph.path;

/**
 * Created by yyqian on 5/31/16.
 */
public interface SP {
  double distTo(int v);
  boolean hashPathTo(int v);
  Iterable<DirectedEdge> pathTo(int v);
}
