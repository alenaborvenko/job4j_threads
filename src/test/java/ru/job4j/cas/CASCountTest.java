package ru.job4j.cas;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class CASCountTest {
    @Test
    public void casTest() throws InterruptedException {
        CASCount count = new CASCount();
        Thread th = new Thread(count::increment);
        Thread thread = new Thread(count::increment);
        th.start();
        thread.start();
        th.join();
        thread.join();
        assertThat(count.get(), is(2));
    }
}