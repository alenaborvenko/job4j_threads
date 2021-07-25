package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    try {
                        System.out.println("start loading...");
                        for (int i = 0; i <= 100; i++) {
                            System.out.print("\rLoading : " + i + "%");
                            Thread.sleep(1000);
                        }
                        System.out.println("finish loading");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
        thread.join();
    }
}
