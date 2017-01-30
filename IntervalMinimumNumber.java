// http://www.lintcode.com/en/problem/interval-minimum-number/#
/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */
class SegmentTreeNode {
    int start;
    int end;
    SegmentTreeNode left;
    SegmentTreeNode right;
    int min;
    SegmentTreeNode(int start, int end, int min) {
        this.start = start;
        this.end = end;
        this.min = min;
    }
}

class SegmentTree {
    int[] nums;
    SegmentTreeNode root;
    public SegmentTree(int[] nums) {
        this.nums = nums;
        if (nums.length != 0) this.root = build(nums, 0, nums.length - 1);
    }
    
    public SegmentTreeNode build(int[] nums, int start, int end) {
        if (start == end) {
            return new SegmentTreeNode(start, end, nums[start]);
        }
        int mid = start + (end - start) / 2;
        SegmentTreeNode left = build(nums, start, mid);
        SegmentTreeNode right = build(nums, mid+1, end);
        SegmentTreeNode root = new SegmentTreeNode(start, end, Math.min(left.min, right.min));
        root.left = left;
        root.right = right;
        return root;
    }
    
    public int query(int start, int end) {
        return query(root, start, end);
    }
    
    public int query(SegmentTreeNode root, int start, int end) {
        if (root == null || end < root.start || start > root.end) {
            return -1;
        } else {
            start = Math.max(root.start, start);
            end = Math.min(root.end, end);
        }
        
        if (start == root.start && end == root.end) {
            return root.min;
        }
        
        int mid = root.start + (root.end - root.start) / 2;
        if (end <= mid) {
            return query(root.left, start, end);
        } else if (start > mid) {
            return query(root.right, start, end);
        } else {
            return Math.min(query(root.left, start, mid), query(root.right, mid+1, end));
        }
    }
}
 
public class Solution {
    /**
     *@param A, queries: Given an integer array and an query list
     *@return: The result list
     */
    public ArrayList<Integer> intervalMinNumber(int[] A, 
                                                ArrayList<Interval> queries) {
        // write your code here
        SegmentTree tree = new SegmentTree(A);
        ArrayList<Integer> result = new ArrayList<>();
        for (Interval query : queries) {
            result.add(tree.query(query.start, query.end));
        }
        return result;
    }
}
