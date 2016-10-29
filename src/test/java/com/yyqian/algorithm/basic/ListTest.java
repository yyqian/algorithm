package com.yyqian.algorithm.basic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yyqian on 2016/10/29.
 */
public class ListTest {

  @Test
  public void arrayStackTest() throws Exception {
    ArrayStack<Integer> stack = new ArrayStack<>();
    stack.push(99);
    stack.push(32);
    assertEquals(32, stack.pop().intValue());
    assertEquals(99, stack.pop().intValue());
    assertNull(stack.pop());
  }

  @Test
  public void listStackTest() throws Exception {
    ListStack<Integer> stack = new ListStack<>();
    stack.push(99);
    stack.push(32);
    assertEquals(32, stack.pop().intValue());
    assertEquals(99, stack.pop().intValue());
    assertNull(stack.pop());
  }

  @Test
  public void listQueueTest() throws Exception {
    ListQueue<Integer> queue = new ListQueue<>();
    queue.offer(99);
    queue.offer(32);
    assertEquals(99, queue.poll().intValue());
    assertEquals(32, queue.poll().intValue());
    assertNull(queue.poll());
  }
}