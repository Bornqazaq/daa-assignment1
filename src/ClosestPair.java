import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double findClosest(Point[] points) {
        Point[] px = points.clone();
        Point[] py = points.clone();
        Arrays.sort(px, Comparator.comparingInt(p -> p.x));
        Arrays.sort(py, Comparator.comparingInt(p -> p.y));

        MetricsTracker.reset();
        long start = System.nanoTime();
        double result = closestUtil(px, py, 0, px.length - 1);
        long end = System.nanoTime();

        System.out.println("Min Distance: " + result);
        System.out.println("Execution Time (ns): " + (end - start));
        System.out.println("Max Recursion Depth: " + MetricsTracker.maxDepth);
        System.out.println("Comparisons: " + MetricsTracker.comparisons);
        return result;
    }

    private static double closestUtil(Point[] px, Point[] py, int left, int right) {
        MetricsTracker.enterRecursion();

        if (right - left <= 3) {
            double min = Double.MAX_VALUE;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    MetricsTracker.countComparison();
                    min = Math.min(min, dist(px[i], px[j]));
                }
            }
            MetricsTracker.exitRecursion();
            return min;
        }

        int mid = (left + right) / 2;
        Point midPoint = px[mid];

        Point[] pyl = Arrays.stream(py).filter(p -> p.x <= midPoint.x).toArray(Point[]::new);
        Point[] pyr = Arrays.stream(py).filter(p -> p.x > midPoint.x).toArray(Point[]::new);

        double dl = closestUtil(px, pyl, left, mid);
        double dr = closestUtil(px, pyr, mid + 1, right);
        double d = Math.min(dl, dr);

        Point[] strip = Arrays.stream(py).filter(p -> Math.abs(p.x - midPoint.x) < d).toArray(Point[]::new);

        double stripMin = stripClosest(strip, d);

        MetricsTracker.exitRecursion();
        return Math.min(d, stripMin);
    }

    private static double stripClosest(Point[] strip, double d) {
        double min = d;
        for (int i = 0; i < strip.length; ++i) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; ++j) {
                MetricsTracker.countComparison();
                min = Math.min(min, dist(strip[i], strip[j]));
            }
        }
        return min;
    }

    private static double dist(Point p1, Point p2) {
        return Math.hypot(p1.x - p2.x, p1.y - p2.y);
    }

    public static void main(String[] args) {
        Point[] points = new Point[1000];
        for (int i = 0; i < points.length; i++) {
            int x = (int) (Math.random() * 10000);
            int y = (int) (Math.random() * 10000);
            points[i] = new Point(x, y);
        }

        findClosest(points);
    }
}