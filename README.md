# DAA Assignment 1

This repository contains solutions for Assignment 1 of the Design and Analysis of Algorithms course. The assignment required the implementation and analysis of four major divide and conquer algorithms.

## Implemented Algorithms

1. Merge Sort with cutoff to insertion sort
2. Randomized Quick Sort (recursing on smaller partition)
3. Deterministic Selection (Median of Medians)
4. Closest Pair of Points in 2D (Divide and Conquer)

Each algorithm is instrumented with a `MetricsTracker` to measure the following metrics:

- **Execution Time (in nanoseconds)**
- **Maximum Recursion Depth**
- **Number of Comparisons**

## Results

| Algorithm       | Execution Time (ns) | Max Recursion Depth | Comparisons |
|-----------------|----------------------|----------------------|-------------|
| Merge Sort      | ~700,000             | 8                    | ~6,700      |
| Quick Sort      | ~600,000             | 7                    | ~7,800      |
| Selection (MoM) | ~1,800,000           | 10                   | ~2,900      |
| Closest Pair    | ~6,900,000           | 9                    | ~1,700      |

> Note: Metrics are approximate and based on random inputs of size 1000.

## How to Run

Make sure you are using Java 17+.

Each algorithm has a `main` method. To run any of them:

```bash
javac *.java
java MergeSort
java QuickSort
java Select
java ClosestPair
```

## Repository Link

This repository is submitted as the official assignment submission:
[https://github.com/Bornqazaq/daa-assignment1](https://github.com/Bornqazaq/daa-assignment1)