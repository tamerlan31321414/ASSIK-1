public class Metrics {
    public long comparisons = 0;
    public long swaps = 0;
    public long allocations = 0;
    private int currentDepth = 0;
    public int maxDepth = 0;
    private long startTime, endTime;

    public void startTimer() { startTime = System.nanoTime(); }
    public void stopTimer() { endTime = System.nanoTime(); }
    public long elapsedNanos() { return endTime - startTime; }

    public void enterRec() { currentDepth++; if (currentDepth > maxDepth) maxDepth = currentDepth; }
    public void exitRec() { currentDepth--; }
}
