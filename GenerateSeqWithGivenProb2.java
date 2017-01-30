import java.util.*;
/*
 * The third Question in http://www.1point3acres.com/bbs/thread-218628-1-1.html
 */
public class GenerateSeqWithGivenProb2 {
	  /*
	   * Generate sequence with given probability
	   * ex: cha = {A, B, C, D}, probability = {0.6, 0.2, 0.1, 0.1}. Binary search.
	   */
    char[] charArray;
    int[] range;
    int sum = 0;
    Random rand = new Random();
    public GenerateSeqWithGivenProb(char[] charArray, int[] weights) {
        this.charArray = charArray;
        range = new int[weights.length];
        for (int i = 0; i < weights.length; i++) {
            range[i] = sum;
            sum += weights[i];
        }
    }
    
    public char getRandom(){
        int randInt = rand.nextInt(sum);
        int index = binarySearch(range, randInt);
        return charArray[index];
    }
    
    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        char[] charArray = new char[]{'A', 'B', 'C', 'D'};
        int[] weights = new int[]{6, 2, 1, 1};
        GenerateSeqWithGivenProb G = new GenerateSeqWithGivenProb(charArray, weights);
        for (int i = 0; i < 10; i++) {
            System.out.println(G.getRandom());
        }
    }
}
