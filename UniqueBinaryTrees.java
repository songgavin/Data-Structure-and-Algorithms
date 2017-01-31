class TreeNode {
    TreeNode left;
    TreeNode right;
}

public class UniqueBinaryTrees {

    public static List<TreeNode> getBinaryTrees(int n) {
        List<TreeNode> result;
        if (n == 0) {
            result.add(null);
            return result;
        }
        if (n == 1) {
            result.add(new TreeNode());
            return result;
        }
        
        for(int i = 0; i <= n; i++) {
            List<TreeNode> leftNodes = getBinaryTrees(i);
            List<TreeNode> rightNodes = getBinaryTrees(n - i - 1);
            TreeNode root = new TreeNode();
            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    root.left = leftNode;
                    root.right = rightNode;
                    result.add(root);
                }
            }
        }
        
        return result;
    }

}
