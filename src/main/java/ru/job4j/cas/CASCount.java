package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int prev;
        do {
            prev = count.get();
        } while (!count.compareAndSet(prev, prev + 1));
    }

    public synchronized int get() {
        return count.get();
    }
}