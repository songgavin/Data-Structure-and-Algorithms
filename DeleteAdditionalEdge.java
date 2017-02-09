class TreeNode {
    TreeNode left;
    TreeNode right;
}

public class DeleteAdditionalEdge {
    public void deleteEdge(TreeNode root) {
    
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        
        queue.offer(root);
        visited.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode cur = queue.pop();
            if (cur.left != null) {
                if (visited.contains(cur.left)) {
                    cur.left = null;
                    return;
                }
                queue.offer(cur.left);
                visited.add(cur.left);
            }
            if (cur.right != null) {
                if (visited.contains(cur.right)) {
                    cur.right = null;
                    return;
                }
                queue.offer(cur.right);
                visited.add(cur.right);
            }
        }
        return;
    }
}
