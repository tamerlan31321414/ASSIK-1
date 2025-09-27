package com.myproject.metrics;

public class MetricsTracker {
    private long comparisons;
    private long allocations;
    private long swaps;
    private int currentDepth;
    private int maxDepth;

    private long startTime;
    private long endTime;

    public MetricsTracker() {
        reset();
    }

    public void reset() {
        comparisons = 0;
        allocations = 0;
        swaps = 0;
        currentDepth = 0;
        maxDepth = 0;
        startTime = 0;
        endTime = 0;
    }

    public void enter() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exit() {
        if (currentDepth > 0) {
            currentDepth--;
        }
    }

    public void addComparison() {
        comparisons++;
    }

    public void addAllocation() {
        allocations++;
    }

    public void addSwap() {
        swaps++;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public long getElapsedMillis() {
        return (endTime - startTime) / 1_000_000;
    }

    public String summary() {
        return String.format(
                "comparisons=%d, allocations=%d, swaps=%d, maxDepth=%d, time=%d ms",
                comparisons, allocations, swaps, maxDepth, getElapsedMillis()
        );
    }


    public long getComparisons() { return comparisons; }
    public long getAllocations() { return allocations; }
    public long getSwaps() { return swaps; }
    public int getMaxDepth() { return maxDepth; }
}
