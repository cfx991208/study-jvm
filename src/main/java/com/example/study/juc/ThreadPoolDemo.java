package com.example.study.juc;


import java.util.concurrent.*;

/**
 * 线程池
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        initPoolParams();
    }

    private static void initPoolParams() {
        // AbortPolicy()  默认拒绝策略，超出就报错
        // CallerRunsPolicy() 超出会回退给上一个线程
        // DiscardPolicy() 超出会默默抛弃无法处理的任务
        // DiscardOldestPolicy() 超出会抛弃等待时间最长的线程，然后把最新的加入到任务中再次提交
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            for (int i = 0; i < 9; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 处理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private static void intiPool() {
        // 创建固定数量的线程池
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 创建只有一个线程的线程池
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 创建可重用性的线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 处理业务");
                });
//                try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

}