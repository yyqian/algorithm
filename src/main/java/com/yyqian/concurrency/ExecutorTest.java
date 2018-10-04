package com.yyqian.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

public class ExecutorTest {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static final ThreadPoolExecutor myExecutor = new ThreadPoolExecutor(2,
            5, 60, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100)); // 至少 1 个线程，最多 5 个，idle 线程存活 60 秒后被关闭，任务队列最长 100 个

    static {
        myExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy()); // 队列满了就默默地丢弃
        myExecutor.setThreadFactory(r -> {
            Thread t = new Thread(r);
            t.setName("MyExecutor-" + t.getName());
            t.setUncaughtExceptionHandler((t1, e) -> {
                System.out.println("uncaughtException in thread: " + t1.getName());
                e.printStackTrace();
            });
            return t;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // ExecutorService.submit
        Future<String> f1 = executorService.submit(() -> "Hello, World");
        System.out.println(f1.get());
        // CompletionService.submit/take
        CompletionService<String> completionService =
                new ExecutorCompletionService<>(executorService);
        for (int i = 0; i < 10; ++i) {
            int l = i;
            completionService.submit(() -> "Hello " + l);
        }
        for (int i = 0; i < 10; ++i) {
            System.out.println(completionService.take().get());
        }
        // ExecutorService.invokeAll
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            int l = i;
            tasks.add(() -> "Foobar " + l);
        }
        List<Future<String>> futures = executorService.invokeAll(tasks);
        for (Future<String> f : futures) {
            System.out.println(f.get());
        }
        myExecutor.submit(() -> {
            Thread t = Thread.currentThread();
            System.out.println("this is my executor. " + t.getName());
        });
        executorService.shutdown();
        myExecutor.shutdown();
    }
}
