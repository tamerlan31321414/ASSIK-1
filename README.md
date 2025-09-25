# ASSIK-1
Divide and Conquer Algorithms

This repository contains implementations of several well-known divide-and-conquer algorithms, extended with measurement tools for analyzing performance. The focus is on both theoretical complexity and practical behavior.

Algorithms Included
MergeSort → uses a shared buffer for merging and switches to insertion sort on small arrays.
QuickSort → employs randomized pivots and recurses on the smaller partition to keep stack depth bounded.
Deterministic Select → Median-of-Medians algorithm for finding the k-th smallest element in linear time.
Closest Pair of Points (2D) → classical O(n log n) solution using recursive splitting and strip-check optimization.

Collected Metrics
During each run, the following measurements are recorded:
Total execution time
Maximum recursion depth
Number of element comparisons
Memory allocations

Workflow
main branch → production-ready versions only (releases are tagged, e.g. v0.1, v1.0).
feature/* branches → algorithm implementations and experimental changes (e.g., feature/mergesort, feature/quicksort).
