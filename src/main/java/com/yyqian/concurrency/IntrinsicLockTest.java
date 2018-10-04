package com.yyqian.concurrency;

public class IntrinsicLockTest {
    private static class Parent {
        public void doSomething() {
            synchronized (this) {
                System.out.println("Parent is running");
            }
        }
        public static void foo() {
            synchronized (Parent.class) {
                System.out.println("Parent static foo");
            }
        }
    }

    private static class Child extends Parent {
        public synchronized void doSomething() {
            System.out.println("Child is running");
            super.doSomething(); // 内置锁是可重入的
        }
        public synchronized static void foo() {
            System.out.println("Child static foo");
        }
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.doSomething();
        Parent parent = new Child();
        parent.doSomething(); // 多态
        parent.foo(); // call Parent.foo()
    }
}
