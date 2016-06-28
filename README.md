# 实现的算法

## 排序

- Bubble
- Insertion
- Selection
- Shell
- Heap (Priority Queue)
- Merge
- Quick
- Radix

## 查找

### 搜索树和哈希表

- Binary Search Tree
- Red Black Tree
- Separate Chaining Hash Table

### 符号表

- Sequential Search Symbol Table
- Linear Probing Hash Symbol Table
- Binary Search Symbol Table
- Sparse Matrix

## 图

### 无向图

- 深度优先搜索
- 广度优先搜索

### 有向图

- 有向无环图
- 强联通

### 最小生成树

- lazy Prim, space E, time ElgE
- eager Prim(using binary heap), space V, time ElgV
- Kruskal, space E, time ElgE

### 最短路径算法

- Dijkstra(eager)
- Topological sort + Relax(for acyclic only)
- Bellman-Ford(SPFA)

### 最大流

- Ford-Fulkerson

## 字符串

### 字符串排序

- LSD: 最简单的 radix sort 例子
- MSD: 适用于变长的字符串排序
- Quick 3 way: 借鉴了 Quicksort 的理念

### 字典树

- Trie
- TST: 解决了 Trie 树在字典库很大时, 空间占用问题很大的问题

### 子字符串搜索

- KMP: 有限状态机的应用
- Boyer-Moore: 比 KMP 平均复杂度更小, 空间复杂度更小
- Rabin-Karp: hash 算法, 优化了 hash 计算
- 后缀数组

### 正则表达式

- NFA

## 关键算法

- 最短路径: Dijkstra, Bellman-Ford(SPFA), Acyclic, A*
- 无向图: 广度优先搜索, 深度优先搜索
- 最小生成树: Prim (eager)
- 排序: QuickSort, MergeSort
- 搜索: 红黑树, 哈希表
- 数据结构: LinkedList, Queue, Stack, Binary Heap (Priority Queue)
- 字符串: MSD, Trie, KMP, 正则表达式
