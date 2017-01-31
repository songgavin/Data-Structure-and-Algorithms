// Fourth Question in http://www.1point3acres.com/bbs/thread-218243-1-1.html
import java.util.*;
class TreeNode {
    TreeNode left;
    TreeNode right;
}

public class UniqueBinaryTrees {

    public static List<TreeNode> getBinaryTrees(int n) {
        List<TreeNode> result = new ArrayList<>();
        if (n == 0) {
            result.add(null);
            return result;
        }
        if (n == 1) {
            result.add(new TreeNode());
            return result;
        }
        
        for(int i = 0; i < n; i++) {
            List<TreeNode> leftNodes = getBinaryTrees(i);
            List<TreeNode> rightNodes = getBinaryTrees(n - i - 1);
            
            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    TreeNode root = new TreeNode();
                    root.left = leftNode;
                    root.right = rightNode;
                    result.add(root);
                }
            }
        }
        
        return result;
    }

    public static List<TreeNode> getBinaryTreesWithMemory(int n) {
        return getBinaryTreesWithMemory(n, new HashMap<Integer,List<TreeNode>>());
    }


    public static List<TreeNode> getBinaryTreesWithMemory(int n, Map<Integer, List<TreeNode>> memory) {
        if (memory.containsKey(n)) {
            return memory.get(n);
        }
        List<TreeNode> result = new ArrayList<>();
        if (n == 0) {
            result.add(null);
            return result;
        }
        if (n == 1) {
            result.add(new TreeNode());
            return result;
        }
        
        for(int i = 0; i < n; i++) {
            List<TreeNode> leftNodes = getBinaryTrees(i);
            List<TreeNode> rightNodes = getBinaryTrees(n - i - 1);
            
            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    TreeNode root = new TreeNode();
                    root.left = leftNode;
                    root.right = rightNode;
                    result.add(root);
                }
            }
        }
        
        memory.put(n, result);
        return result;
    }
    
    public static List<String> serializeTreeNodeList(List<TreeNode> nodeList) {
        List<String> list = new ArrayList<>();
        for (TreeNode node : nodeList) {
            list.add(serialize(node));
        }
        return list;
    }

    public static String serialize(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> queue = new LinkedList<>();
        
        StringBuilder sb = new StringBuilder();
        queue.offer(root);
        sb.append("" + 'O');
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (curNode.left != null) {
                sb.append("," + 'O');
                queue.offer(curNode.left);
            } else {
                sb.append(",#");
            }
            if (curNode.right != null) {
                sb.append("," + 'O');
                queue.offer(curNode.right);
            } else {
                sb.append(",#");
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data.equals("")) return null; 
        String[] nodeList = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode();
        queue.offer(root);
        int i = 1;
        while (i < nodeList.length) {
            TreeNode curNode = queue.poll();
            if (!nodeList[i].equals("#")) {
                curNode.left = new TreeNode();
                queue.offer(curNode.left);
            }
            i++;
            if (!nodeList[i].equals("#")) {
                curNode.right = new TreeNode();
                queue.offer(curNode.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        List<TreeNode> nodeList = Solution.getBinaryTrees(3);
        System.out.println(Solution.serializeTreeNodeList(nodeList));
        List<TreeNode> nodeList2 = Solution.getBinaryTreesWithMemory(3);
        System.out.println(Solution.serializeTreeNodeList(nodeList2));
    }
}
