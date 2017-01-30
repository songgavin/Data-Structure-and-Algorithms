/**
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
}
