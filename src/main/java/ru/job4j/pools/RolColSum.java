package ru.job4j.pools;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static Sums[] sum(int[][] matrix) {
        Sums[] sumsMatrix = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sumsMatrix[i] = countSum(matrix, i);
        }
        return sumsMatrix;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sumsMatrix = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            futures.put(i, getTask(matrix, i));
        }
        for (int i = 0; i < sumsMatrix.length; i++) {
            sumsMatrix[i] = futures.get(i).get();
        }
        return sumsMatrix;
    }

    public static CompletableFuture<Sums> getTask(int[][] data, int row) {
        return CompletableFuture.supplyAsync(() -> countSum(data, row));
    }

    private static Sums countSum(int[][] matrix, int row) {
        int rol = 0;
        int col = 0;
        Sums sums = new Sums();
        for (int i = 0; i < matrix.length; i++) {
            rol += matrix[row][i];
            col += matrix[i][row];
        }
        sums.setRowSum(rol);
        sums.setColSum(col);
        return sums;
    }

    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums() {
            this.rowSum = 0;
            this.colSum = 0;
        }

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Sums.class.getSimpleName() + "{", "}")
                    .add("rowSum=" + rowSum)
                    .add("colSum=" + colSum)
                    .toString();
        }
    }
}
