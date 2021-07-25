package ru.job4j.pool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class ThreadPoolTest {
    @Test
    public void threadPoolTest() {
        ThreadPool threadPool = new ThreadPool();
        List<Integer> out = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> {
            try {
                threadPool.work(() -> out.add(i));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadPool.shutdown();
        assertThat(out, containsInAnyOrder(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    }
}