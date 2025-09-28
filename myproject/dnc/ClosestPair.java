package com.myproject.dnc;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Point {
        public final double x;
        public final double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double findClosest(Point[] input) {
        if (input == null || input.length < 2) {
            throw new IllegalArgumentException("At least 2 points required");
        }

        Point[] sortedX = Arrays.copyOf(input, input.length);
        Arrays.sort(sortedX, Comparator.comparingDouble(p -> p.x));

        Point[] buffer = new Point[input.length];
        return solve(sortedX, buffer, 0, sortedX.length - 1);
    }

    private static double solve(Point[] pts, Point[] buf, int left, int right) {
        int n = right - left + 1;
        if (n <= 3) {
            return bruteForceAndSort(pts, left, right);
        }

        int mid = (left + right) >>> 1;
        double midX = pts[mid].x;

        double d1 = solve(pts, buf, left, mid);
        double d2 = solve(pts, buf, mid + 1, right);
        double d = Math.min(d1, d2);

        mergeByY(pts, buf, left, mid, right);

        int count = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(pts[i].x - midX) < d) {
                buf[count++] = pts[i];
            }
        }

        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count && (buf[j].y - buf[i].y) < d; j++) {
                d = Math.min(d, distance(buf[i], buf[j]));
            }
        }

        return d;
    }

    private static double bruteForceAndSort(Point[] pts, int l, int r) {
        double best = Double.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            for (int j = i + 1; j <= r; j++) {
                best = Math.min(best, distance(pts[i], pts[j]));
            }
        }
        Arrays.sort(pts, l, r + 1, Comparator.comparingDouble(p -> p.y));
        return best;
    }

    private static void mergeByY(Point[] pts, Point[] buf, int l, int m, int r) {
        int i = l, j = m + 1, k = 0;
        while (i <= m && j <= r) {
            if (pts[i].y <= pts[j].y) {
                buf[k++] = pts[i++];
            } else {
                buf[k++] = pts[j++];
            }
        }
        while (i <= m) buf[k++] = pts[i++];
        while (j <= r) buf[k++] = pts[j++];
        System.arraycopy(buf, 0, pts, l, k);
    }

    private static double distance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
