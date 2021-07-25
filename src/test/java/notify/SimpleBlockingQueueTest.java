package notify;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {
    @Test
    public void testBlockingQueue() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread concumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                queue.poll();
            }
        });
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 11; i++) {
                queue.offer(i);
            }
        });
        producer.start();
        concumer.start();
        producer.join();
        concumer.join();
        assertThat(10, is(queue.poll()));
    }
}