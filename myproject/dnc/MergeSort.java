package com.myproject.dnc;

import static com.myproject.util.ArrayUtils.Metrics;

public class MergeSort {

    public static String sort(int[] arr) {
        Metrics.reset();
        Metrics.startTimer();

        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1);

        Metrics.stopTimer();
        return Metrics.summary();
    }

    private static void mergeSort(final int[] arr, final int[] buffer, int left, int right) {
        if (left >= right) return;

        Metrics.enter();
        int mid = (left + right) / 2;

        mergeSort(arr, buffer, left, mid);
        mergeSort(arr, buffer, mid + 1, right);
        merge(arr, buffer, left, mid, right);

        Metrics.exit();
    }

    private static void merge(final int[] arr, final int[] buffer, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            Metrics.addComparison();
            buffer[k++] = (arr[i] <= arr[j]) ? arr[i++] : arr[j++];
            Metrics.addAllocation();
        }
        while (i <= mid) {
            buffer[k++] = arr[i++];
            Metrics.addAllocation();
        }
        while (j <= right) {
            buffer[k++] = arr[j++];
            Metrics.addAllocation();
        }
        for (i = left; i <= right; i++) {
            arr[i] = buffer[i];
        }
    }
}
