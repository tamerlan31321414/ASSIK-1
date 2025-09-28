package com.myproject.util;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayUtilsTest {

    @Test
    public void testSwap() {
        int[] a = {1,2,3};
        Collections ArrayUtils;
        ArrayUtils.swap(a,0,2);
        assertArrayEquals(new int[]{3,2,1}, a);
    }

    @Test
    public void testSwapSameIndex() {
        int[] a = {1,2,3};
        ArrayUtils.swap(a,1,1);
        assertArrayEquals(new int[]{1,2,3}, a); // не должно поменяться
    }

    @Test
    public void testShuffleKeepsMultiset() {
        int[] a = new int[]{1,2,3,4,5,5,6};
        int[] copy = Arrays.copyOf(a, a.length);
        ArrayUtils.shuffle(a);
        assertTrue(ArrayUtils.sameMultiset(a, copy));
    }

    @Test
    public void testShuffleSingleElement() {
        int[] a = {42};
        ArrayUtils.shuffle(a);
        assertArrayEquals(new int[]{42}, a); // shuffle с одним элементом
    }

    @Test
    public void testPartitionLomuto() {
        int[] a = new int[]{5,2,9,1,7,3};
        int pivotPos = ArrayUtils.partitionLomuto(a, 0, a.length - 1);
        for (int i = 0; i < pivotPos; i++) assertTrue(a[i] <= a[pivotPos]);
        for (int i = pivotPos + 1; i < a.length; i++) assertTrue(a[i] > a[pivotPos]);
        assertTrue(ArrayUtils.sameMultiset(a, new int[]{5,2,9,1,7,3}));
    }

    @Test
    public void testPartitionLomutoSingleElement() {
        int[] a = {7};
        int pivotPos = ArrayUtils.partitionLomuto(a, 0, 0);
        assertEquals(0, pivotPos);
        assertArrayEquals(new int[]{7}, a);
    }

    @Test
    public void testPartitionHoare() {
        int[] a = new int[]{5,2,9,1,7,3};
        int p = ArrayUtils.partitionHoare(a, 0, a.length - 1);
        for (int i = 0; i <= p; i++) {
            for (int j = p + 1; j < a.length; j++) {
                assertTrue(a[i] <= a[j]);
            }
        }
        assertTrue(ArrayUtils.sameMultiset(a, new int[]{5,2,9,1,7,3}));
    }

    @Test
    public void testPartitionHoareTwoElements() {
        int[] a = {9, 1};
        int p = ArrayUtils.partitionHoare(a, 0, 1);
        assertTrue(a[0] <= a[1]); // после partition должно быть отсортировано
        assertTrue(ArrayUtils.sameMultiset(a, new int[]{9, 1}));
    }
}
