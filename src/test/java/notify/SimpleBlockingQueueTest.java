package notify;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {
    @Test
    public void testBlockingQueue() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread concumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    queue.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 11; i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        concumer.start();
        producer.join();
        concumer.join();
        assertThat(10, is(queue.poll()));
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(List.of(0, 1, 2, 3, 4)));
    }
}