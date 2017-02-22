import java.util.*;
// First question in http://www.1point3acres.com/bbs/thread-228275-1-1.html
public class IntervalArea {

    public static double intervalCoverSize(double[][] intervals) {
        double lowerBound = Double.MAX_VALUE;
        double upperBound = Double.MIN_VALUE;

        for (double[] interval : intervals) {
            lowerBound = Math.min(interval[0], lowerBound);
            upperBound = Math.max(interval[1], upperBound);
        }

        int minNum = (int)Math.floor(lowerBound);
        int maxNum = (int)Math.ceil(upperBound);
        int bucketLen = maxNum - minNum + 1;

        double[] bucketMin = new double[bucketLen];
        double[] bucketMax = new double[bucketLen];
        Arrays.fill(bucketMin, Double.MAX_VALUE);
        Arrays.fill(bucketMax, Double.MIN_VALUE);

        for (double[] interval : intervals) {
            int index1 = (int)Math.floor(interval[0]) - minNum;
            bucketMin[index1] = Math.min(bucketMin[index1], interval[0]);
            bucketMax[index1 + 1] = Math.max(bucketMax[index1 + 1], interval[1]);
        }

        double area = 0;
        for (int i = 0; i < bucketLen; i++) {
            double leftToRight = bucketMin[i];
            double rightToLeft = bucketMax[i];
            if (rightToLeft >= leftToRight) {
                area += 1;
            } else {
                if (leftToRight != Double.MAX_VALUE) {
                    area += i + minNum + 1 - leftToRight;
                } 
                if (rightToLeft != Double.MIN_VALUE) {
                    area += rightToLeft - (i + minNum);
                }
            }
        }

        return area;
    }

    public static void main(String[] args) {
        double[][] IntervalArea = {{0, 1}, {0.5, 1.5}, {1.1, 2.1}, {1.5, 2.5}, {5, 6}};
        System.out.println(MinimumMaxGap.intervalCoverSize(intervals));
    }
}
