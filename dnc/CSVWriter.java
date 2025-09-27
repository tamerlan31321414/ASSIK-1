package com.myproject.dnc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.myproject.util.ArrayUtils.Metrics;

public class CSVWriter implements AutoCloseable {
    private final PrintWriter writer;

    public CSVWriter(String path, boolean append) throws IOException {
        this.writer = new PrintWriter(new BufferedWriter(new FileWriter(path, append)));
    }

    public void writeHeader(String header) {
        writer.println(header);
        writer.flush();
    }

    public void append(String line) {
        writer.println(line);
        writer.flush();
    }

    @Override
    public void close() {
        writer.close();
    }

    public static class MergeSort {

        public static void sort(int[] arr) {
            Metrics.reset();
            Metrics.startTimer();

            int[] buffer = new int[arr.length];
            mergeSort(arr, buffer, 0, arr.length - 1);

            Metrics.stopTimer();
            System.out.println(Metrics.summary());
        }

        private static void mergeSort(int[] arr, int[] buffer, int left, int right) {
            if (left >= right) return;

            Metrics.enter();

            int mid = (left + right) / 2;
            mergeSort(arr, buffer, left, mid);
            mergeSort(arr, buffer, mid + 1, right);
            merge(arr, buffer, left, mid, right);

            Metrics.exit();
        }

        private static void merge(int[] arr, int[] buffer, int left, int mid, int right) {
            int i = left;
            int j = mid + 1;
            int k = left;

            while (i <= mid && j <= right) {
                Metrics.addComparison();
                if (arr[i] <= arr[j]) {
                    buffer[k++] = arr[i++];
                } else {
                    buffer[k++] = arr[j++];
                }
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
}
