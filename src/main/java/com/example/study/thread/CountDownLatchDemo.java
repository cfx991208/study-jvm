package com.example.study.thread;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.CountDownLatch;

@Getter
@AllArgsConstructor
enum CountEnum {

    ONE(6,"韩"),TWO(1,"楚"),THREE(2,"燕"),FOUR(3,"赵"),FIVE(4,"魏"),SIX(5,"齐");
    private Integer code;

    private String message;

    public static CountEnum forCountry(int index){
        CountEnum[] values = CountEnum.values();
        for (CountEnum countEnum: values) {
            if (index == countEnum.getCode()){
                return countEnum;
            }
        }
        return null;
    }
}

/**
 * 自减，与之相对的是自增（CyclicBarrier）略
 * 更加强大的Semaphore（jUC中写过了）
 * 10 - 9 -8 -...- 0
 */
public class CountDownLatchDemo {

    private static final int totalCount = 6;

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(totalCount);
        for (int i = 1; i <= totalCount; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "国被灭。。。");
                latch.countDown();
            }, CountEnum.forCountry(i).getMessage()).start();
        }

        try {
            latch.await();
            System.out.println(Thread.currentThread().getName() + "\t 大秦灭六国，登基称帝！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
