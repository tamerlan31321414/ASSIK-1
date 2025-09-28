package com.myproject.dnc;

import com.myproject.metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {

    private static final int N = 5000;
    private static final Random rnd = new Random();

    @Test
    public void testRandomArray() {
        int[] a = rnd.ints(N, -10000, 10000).toArray();
        int[] expected = a.clone();
        Arrays.sort(expected);


      

        assertArrayEquals(expected, a);
        Object Metrics = null;
        assertTrue(Metrics.maxDepth <= (int)(Math.log(N) / Math.log(2)) + 5,
                "Depth is too large for MergeSort");
    }

    @Test
    public void testSortedArray() {
        int[] a = new int[N];
        for (int i = 0; i < N; i++) a[i] = i;
        int[] expected = a.clone();

        MergeSort.sort(a);

        assertArrayEquals(expected, a);
    }

    @Test
    public void testReversedArray() {
        int[] a = new int[N];
        for (int i = 0; i < N; i++) a[i] = N - i;
        int[] expected = a.clone();
        Arrays.sort(expected);

        MergeSort.sort(a);

        assertArrayEquals(expected, a);
    }

    @Test
    public void testAllEqualElements() {
        int[] a = new int[N];
        Arrays.fill(a, 42);
        int[] expected = a.clone();

        MergeSort.sort(a);

        assertArrayEquals(expected, a);
    }

    // 🔹 Edge cases
    @Test
    public void testEmptyArray() {
        int[] a = {};
        int[] expected = {};
        MergeSort.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    public void testSingleElement() {
        int[] a = {99};
        int[] expected = {99};
        MergeSort.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    public void testTwoElements() {
        int[] a = {5, 2};
        int[] expected = {2, 5};
        MergeSort.sort(a);
        assertArrayEquals(expected, a);
    }

    
    @Test
    public void testMetrics() {
        int[] a = rnd.ints(100, -1000, 1000).toArray();
        MergeSort.sort(a);
        System.out.println("Metrics: " + Metrics.summary());
        assertTrue(Metrics.comparisons > 0, "Comparisons should be counted");
    }

    public void testLargeArray() {
        int size = 100_000;
        int[] a = rnd.ints(size, -1_000_000, 1_000_000).toArray();
        int[] expected = a.clone();
        Arrays.sort(expected);

        MergeSort.sort(a);

        assertArrayEquals(expected, a);
    }
}
