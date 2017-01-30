/**
 * http://www.lintcode.com/en/problem/segment-tree-build-ii/
 * Definition of SegmentTreeNode:
 * public class SegmentTreeNode {
 *     public int start, end, max;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end, int max) {
 *         this.start = start;
 *         this.end = end;
 *         this.max = max
 *         this.left = this.right = null;
 *     }
 * }
 */
public class SegmentTree2 {
    /**
     *@param A: a list of integer
     *@return: The root of Segment Tree
     */
    public SegmentTreeNode build(int[] A) {
        // write your code here
        return build(A, 0, A.length - 1);
    }
    
    public SegmentTreeNode build(int[] A, int start, int end) {
        if (start > end || A == null || start < 0 || end < 0 || start >= A.length || end >= A.length) {
            return null;
        }
        if (start == end) {
            return new SegmentTreeNode(start, end, A[start]);
        }
        
        int mid = start + (end - start) / 2;
        SegmentTreeNode left = build(A, start, mid);
        SegmentTreeNode right = build(A, mid + 1, end);
        SegmentTreeNode root = new SegmentTreeNode(start, end, Math.max(left.max, right.max));
        root.left = left;
        root.right = right;
        return root;
    }
    
    /*
     *@param root, start, end: The root of segment tree and an segment / interval
     *@return: The maximum number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        if (root == null || end < root.start || start > root.end) {
            return 0;
        } else {
            start = Math.max(root.start, start);
            end = Math.min(root.end, end);
        }
        // write your code here
        if (root.start == start && root.end == end) {
            return root.max;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (end <= mid) {
            return query(root.left, start, end);
        } else if (start > mid) {
            return query(root.right, start, end);
        } else {
            return Math.max(query(root.left, start, mid), query(root.right, mid + 1,end));
        }
    }
    
    /*
     *@param root, index, value: The root of segment tree and 
     *@ change the node's value with [index, index] to the new given value
     *@return: void
     */
    public void modify(SegmentTreeNode root, int index, int value) {
        // write your code here
        if (root == null || index < root.start || index > root.end) return;
        int mid = root.start + (root.end - root.start) / 2;
        
        if (root.start == index && root.end == index) {
            root.max = value;
            return;
        }
        
        if (index > mid) {
            modify(root.right, index, value);
        } else {
            modify(root.left, index, value);
        }
        
        root.max = Math.max(root.left.max, root.right.max);
    }
}
