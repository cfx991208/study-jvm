package com.example.study.juc;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("%%%%%%%%%%%%%%%%%我进来啦");
        return 200;
    }
}

/**
 * 实现多线程的第三种方式
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask, "A").start();
        System.out.println(futureTask.get());
    }

}
