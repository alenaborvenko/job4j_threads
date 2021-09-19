package ru.job4j.pools;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RolColSumTest {
    private int[][] firstArray;
    private int[][] secondArray;

    @Before
    public void init() {
        firstArray = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        secondArray = new int[][]{{1, 2, 3}, {4, 5, 6}};
    }

    @Test
    public void whenAsyncSum1() throws ExecutionException, InterruptedException {
        String d = "[Sums{rowSum=6, colSum=12}, Sums{rowSum=15, colSum=15}, Sums{rowSum=24, colSum=18}]";
        assertThat(Arrays.toString(RolColSum.asyncSum(firstArray)), is(d));
    }

    @Test
    public void whenAsyncSum2() throws ExecutionException, InterruptedException {
        String d = "[Sums{rowSum=3, colSum=5}, Sums{rowSum=9, colSum=7}]";
        assertThat(Arrays.toString(RolColSum.asyncSum(secondArray)), is(d));
    }

    @Test
    public void whenSum1() {
        String d = "[Sums{rowSum=6, colSum=12}, Sums{rowSum=15, colSum=15}, Sums{rowSum=24, colSum=18}]";
        assertThat(Arrays.toString(RolColSum.sum(firstArray)), is(d));
    }

    @Test
    public void whenSum2() {
        String d = "[Sums{rowSum=3, colSum=5}, Sums{rowSum=9, colSum=7}]";
        assertThat(Arrays.toString(RolColSum.sum(secondArray)), is(d));
    }
}