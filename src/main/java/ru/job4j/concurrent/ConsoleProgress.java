package ru.job4j.concurrent;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        char[] process = {'\\', '|', '/', '—'};
        int i = 0;
        while (!currentThread().isInterrupted()) {
            try {
                System.out.print("\r load: " + process[(i++) % process.length]);
                sleep(500);
            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        sleep(5000);
        progress.interrupt();
    }
}
