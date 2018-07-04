package com.yyqian.algorithm.string.regex;

import com.yyqian.algorithm.graph.digraph.Digraph;
import com.yyqian.algorithm.graph.digraph.DirectedDFS;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yyqian on 6/17/16.
 * 用状态机实现的正则表达式
 */
public class NFA {
  private char[] re; // match transitions
  private Digraph G; // epsilon transitions
  private int M; // number of states

  public boolean recognizes(final String txt) {
    List<Integer> pc = new ArrayList<>();
    DirectedDFS dfs = new DirectedDFS(G, 0); // 通过 epsilon transition, 找出所有初始的可能的状态点
    for (int v = 0; v < G.V(); v++) {
      if (dfs.marked(v)) {
        pc.add(v); // 把用深度优先搜索出来的有可能的状态点存到 pc 列表中
      }
    }
    System.out.print("pc: ");
    pc.stream().forEach(x -> System.out.print(x + " "));
    System.out.println("");
    // 对于每一个字符的 matching, 都要进行两次状态转换, 一次是根据 matching 结果进行转换, 另外一次是用 epsilon transition 转换
    for (int i = 0; i < txt.length(); i++) {
      List<Integer> match = new ArrayList<>();
      // 下面是 matching 以及 matching 后状态转换的过程
      for (int v : pc) {
        // 用 txt 中当前位置的字符去 match 状态机中各个可能的状态, 如果 match 就把转换后的状态加入 match 数组中
        if (v < M && (re[v] == txt.charAt(i) || re[v] == '.')) {
          match.add(v + 1);
        }
      }
      System.out.print(txt.charAt(i) + " match: ");
      match.stream().forEach(x -> System.out.print(x + " "));
      System.out.println("");
      // matching 之后的状态转换之后, 我们还要用 epsilon transition 再转换一次状态
      pc = new ArrayList<>();
      dfs = new DirectedDFS(G, match);
      for (int v = 0; v < G.V(); v++) {
        if (dfs.marked(v)) {
          pc.add(v);
        }
      }
      System.out.print("pc: ");
      pc.stream().forEach(x -> System.out.print(x + " "));
      System.out.println("");
    }
    for (int v : pc) {
      if (v == M) {
        return true; // 判断是否到达了最终状态
      }
    }
    return false;
  }

  private void buildNfa(String regexp) {
    Deque<Integer> ops = new LinkedList<>();
    re = regexp.toCharArray();
    M = re.length;
    G = new Digraph(M + 1);
    for (int i = 0; i < M; i++) {
      int lp = i; // 存左括号的位置
      if (re[i] == '(' || re[i] == '|') {
        ops.push(i);
      } else if (re[i] == ')') {
        int or = ops.pop();
        // 处理 ( | ) 的情况
        if (re[or] == '|') {
          lp = ops.pop();
          G.addEdge(lp, or + 1);
          G.addEdge(or, i);
        } else {
          lp = or; // 也就是 (
        }
      }
      if (i < M - 1 && re[i + 1] == '*') {
        G.addEdge(lp, i + 1);
        G.addEdge(i + 1, lp);
      }
      if (re[i] == '(' || re[i] == '*' || re[i] == ')') {
        G.addEdge(i, i + 1);
      }
    }
  }

  public NFA(String regexp) {
    buildNfa(regexp);
  }

  public static void main(String[] args) {
    String regexp = "(.*" + "iro" + ".*)";
    NFA nfa = new NFA(regexp);
    System.out.println(nfa.recognizes("airon"));
  }
}
