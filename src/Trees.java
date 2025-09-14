import javax.lang.model.util.Elements;
import java.util.*;

public class Trees {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        preorderTraversalHelper(root, list);

        return list;
    }

    public void preorderTraversalHelper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        preorderTraversalHelper(root.left, list);
        preorderTraversalHelper(root.right, list);
        list.add(root.val);

    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        preorderTraversalHelper(root, list);

        return list;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        preorderTraversalHelper(root, list);

        return list;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();

        if (root == null) return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.remove();
                if (currentNode == null) {
                    if (queue.isEmpty()) {
                        break;
                    } else {
                        queue.add(null);
                    }
                } else {
                    subList.add(currentNode.val);
                    if (currentNode.left != null) {
                        queue.add(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        queue.add(currentNode.right);
                    }
                }
            }
            list.add(subList);
        }
        return list;
    }

    public int maxDepth(TreeNode root) {
        return maxDepthHelper(root);
    }

    public int maxDepthHelper(TreeNode root) {

        if (root == null) return 0;

        int leftHeight = maxDepthHelper(root.left);
        int rightHeight = maxDepthHelper(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        return isBalancedHelper(root) != -1;

    }

    public int isBalancedHelper(TreeNode root) {

        if (root == null) return 0;

        int leftHeight = isBalancedHelper(root.left);
        int rightHeight = isBalancedHelper(root.right);

        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }

        if (Math.abs(isBalancedHelper(root.left) - isBalancedHelper(root.right)) > 1) {
            return -1;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }

    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return maxSum;
    }

    public int maxPathSumHelper(TreeNode root) {
        if (root == null) return 0;

        int leftPath = Math.max(0, maxPathSumHelper(root.left));
        int rightPath = Math.max(0, maxPathSumHelper(root.right));

        maxSum = Math.max(maxSum, leftPath + rightPath + root.val);

        return root.val + Math.max(leftPath, rightPath);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) return true;
        else if (p == null || q == null) return false;

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

    }

    public void helper(TreeNode p, TreeNode q) {


    }

}
