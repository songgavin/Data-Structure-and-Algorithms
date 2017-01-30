import java.util.*;
/*
 * The third Question in http://www.1point3acres.com/bbs/thread-218628-1-1.html follow up
 */
class SegmentTreeNode {
    int start;
    int end;
    SegmentTreeNode left;
    SegmentTreeNode right;
    int count;
    Character val;
    public SegmentTreeNode(int start, int end, Character val, int count) {
        this.start = start;
        this.end = end;
        this.count = count;
        this.val = val;
    }
}

class SegmentTree {
    SegmentTreeNode root;
    Random rand = new Random();
    SegmentTree(char[] charArray, int[] weights) {
        TreeMap<Integer, Character> map = new TreeMap<>();
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            map.put(sum, charArray[i]);
            sum += weights[i];
        }
        root = build(0, sum - 1, map);
    }
    
    SegmentTreeNode build(int start, int end, TreeMap<Integer, Character> map) {
        if (start == end) {
            return new SegmentTreeNode(start, end, map.get(map.floorKey(start)), 1);
        }
        int mid = start + (end - start) / 2;
        SegmentTreeNode left = build(start, mid, map);
        SegmentTreeNode right = build(mid + 1, end, map);
        SegmentTreeNode root = new SegmentTreeNode(start, end, null, left.count + right.count);
        root.left = left;
        root.right = right;
        return root;
    }
    
    char randomQuery() throws Exception{
        return randomQuery(root);
    }
    
    char randomQuery(SegmentTreeNode root) throws Exception{
        if (root.count == 0) {
            throw new Exception("weight goes zero");
        }
        
        if (root.start == root.end) {
            char ret = root.val;
            update(root.start);
            return ret;
        }

        int randCount = rand.nextInt(root.count);
        
        if (randCount < root.left.count) {
            return randomQuery(root.left);
        } else {
            return randomQuery(root.right);
        }
    }
    
    void update(int index) {
        update(root, index);
    }
    
    void update(SegmentTreeNode root, int index) {
        if (root.start == index && root.end == index) {
            root.val = null;
            root.count = 0;
            return;
        }
        
        root.count--;
        int mid = root.start + (root.end - root.start) / 2;
        if (index <= mid) {
            update(root.left, index);
        } else {
            update(root.right, index);
        }
    }
}

public class GenerateSeqWithGivenProb3 {
      /*
       * Generate sequence with given probability
       * ex: cha = {A, B, C, D}, weight = {6, 2, 1, 1}
       */
    SegmentTree tree;
    public GenerateSeqWithGivenProb(char[] charArray, int[] weights) {
        tree = new SegmentTree(charArray, weights);
    }
    
    public char getRandom() throws Exception {
        return tree.randomQuery();
    }

    public static void main(String[] args) throws Exception {
        char[] charArray = new char[]{'A', 'B', 'C', 'D'};
        int[] weights = new int[]{6, 2, 1, 1};
        GenerateSeqWithGivenProb G = new GenerateSeqWithGivenProb(charArray, weights);
        for (int i = 0; i < 12; i++) {
            System.out.println(G.getRandom());
        }
    }
}
