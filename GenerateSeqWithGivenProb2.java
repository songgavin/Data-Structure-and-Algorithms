import java.util.*;
/*
 * The third Question in http://www.1point3acres.com/bbs/thread-218628-1-1.html
 */
public class GenerateSeqWithGivenProb {
      /*
       * Generate sequence with given probability
       * ex: cha = {A, B, C, D}, probability = {0.6, 0.2, 0.1, 0.1}. Binary search.
       */
    NavigableMap<Integer, Character> map = new TreeMap<>();
    int sum = 0;
    Random rand = new Random();
    public GenerateSeqWithGivenProb(char[] charArray, int[] weights) {
        for (int i = 0; i < weights.length; i++) {
            map.put(sum, charArray[i]);
            sum += weights[i];
        }
    }
    
    public char getRandom(){
        int randInt = rand.nextInt(sum);
        return map.get(map.floorKey(randInt));
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
