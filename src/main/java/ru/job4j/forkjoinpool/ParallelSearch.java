package ru.job4j.forkjoinpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T elem;
    private final int startIndex;
    private final int endIndex;

    private ParallelSearch(T[] array, T elem, int startIndex, int endIndex) {
        this.array = array;
        this.elem = elem;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public static <T> int findElem(T[] array, T elem, int startIndex, int endIndex) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ParallelSearch<>(array, elem, startIndex, endIndex));
    }

    @Override
    protected Integer compute() {
        if ((endIndex - startIndex) < 10) {
            return findIndex();
        }
        int mid = startIndex + (endIndex - startIndex) / 2;
        ParallelSearch<T> left = new ParallelSearch<>(array, elem, startIndex, mid);
        ParallelSearch<T> right = new ParallelSearch<>(array, elem, mid + 1, endIndex);
        left.fork();
        right.fork();
        int leftIndex = left.join();
        int rightIndex = right.join();
        return leftIndex != -1 ? leftIndex : rightIndex;
    }

    private int findIndex() {
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }
}
