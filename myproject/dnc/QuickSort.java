package com.myproject.dnc;

import com.myproject.util.ArrayUtils;

import static com.myproject.util.ArrayUtils.Metrics;

public class QuickSort {

    public static String sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return "empty or null array";
        }

        Metrics.reset();
        Metrics.startTimer();

        quickSort(arr, 0, arr.length - 1);

        Metrics.stopTimer();
        return Metrics.summary();
    }

    private static void quickSort(int[] arr, int low, int high) {
        while (low < high) {
            Metrics.enter();

            int p = ArrayUtils.partitionLomuto(arr, low, high);

            if (p - low < high - p) {
                quickSort(arr, low, p - 1);
                low = p + 1;
            } else {
                quickSort(arr, p + 1, high);
                high = p - 1;
            }

            Metrics.exit();
        }
    }
}
