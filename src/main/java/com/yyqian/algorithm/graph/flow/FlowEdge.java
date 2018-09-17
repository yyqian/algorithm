package com.yyqian.algorithm.graph.flow;

/**
 * Created by yyqian on 6/22/16.
 *
 */
public class FlowEdge {

  private final int from; // 边的起点
  private final int to; // 边的终点
  private final double capacity; // 容量
  private double flow; // 流量

  public FlowEdge(int from, int to, double capacity) {
    this.from = from;
    this.to = to;
    this.capacity = capacity;
  }

  public int other(int vertex) {
    return vertex == from ? to : from;
  }

  public double residualCapacityTo(int vertex) {
    if (vertex == from) {
      return flow;
    } else if (vertex == to) {
      return capacity - flow;
    } else {
      throw new RuntimeException("Inconsistent edge");
    }
  }

  public void addResidualFlowTo(int vertex, double delta) {
    if (vertex == from) {
      flow -= delta;
    } else if (vertex == to) {
      flow += delta;
    } else {
      throw new RuntimeException("Inconsistent edge");
    }
  }

  @Override
  public String toString() {
    return String.format("%d->%d %.2f %.2f", from, to, capacity, flow);
  }

  public int from() {
    return from;
  }

  public int to() {
    return to;
  }

  public double capacity() {
    return capacity;
  }

  public double flow() {
    return flow;
  }
}
