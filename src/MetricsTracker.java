public class MetricsTracker {
    public static int maxDepth = 0;
    public static int currentDepth = 0;
    public static int comparisons = 0;

    public static void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public static void exitRecursion() {
        currentDepth--;
    }

    public static void reset() {
        maxDepth = 0;
        currentDepth = 0;
        comparisons = 0;
    }

    public static void countComparison() {
        comparisons++;
    }
}