package ru.job4j.forkjoinpool;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class ParallelSearchTest {
    @Test
    public void whenFindElem() {
        Integer[] array = IntStream.range(1, 100).boxed().toArray(Integer[]::new);
        Integer findElem = 8;
        int actual = ParallelSearch.findElem(array, findElem, 0, array.length - 1);
        assertThat(actual, is(7));
    }

    @Test
    public void whenNotFoundElem() {
        String[] array = IntStream.range(1, 100).mapToObj(String::valueOf).toArray(String[]::new);
        String findElem = "no elem in array";
        int actual = ParallelSearch.findElem(array, findElem, 0, array.length - 1);
        assertThat(actual, is(-1));
    }
}