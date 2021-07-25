package ru.job4j.concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) throws InterruptedException {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        second.start();
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName());
    }

}
