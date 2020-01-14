package com.example.study.thread;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

@Data
@AllArgsConstructor
class User{
    private String name;
    private int age;
}

/**
 * 原子引用
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User z3 = new User("张三", 21);
        User l4 = new User("李四", 24);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t" + atomicReference.get());
        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t" + atomicReference.get());
    }

}
