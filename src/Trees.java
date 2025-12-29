import java.util.*;

public class Trees {

    public static class TreeNode {
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

    public TreeNode searchBST(TreeNode root, int val) {

        if (root == null) return null;
        if (root.val == val) return root;

        return root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {

        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
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

        list.add(root.val);
        preorderTraversalHelper(root.left, list);
        preorderTraversalHelper(root.right, list);

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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> list = new ArrayList<>();

        if (root == null) return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        int counter = 0;

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

            if (counter % 2 == 1) {
                list.add(subList.reversed());
            } else {
                list.add(subList);
            }
            counter++;
        }
        return list;
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }

        Map<Integer, List<int[]>> map = new HashMap<>();
        int minColumn = 0, maxColumn = 0;

        Queue<Object[]> queue = new LinkedList<>();
        queue.offer(new Object[]{root, 0, 0});

        while (!queue.isEmpty()) {
            Object[] data = queue.poll();
            TreeNode node = (TreeNode) data[0];
            int row = (int) data[1];
            int col = (int) data[2];

            minColumn = Math.min(minColumn, col);
            maxColumn = Math.max(maxColumn, col);

            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }

            map.get(col).add(new int[]{row, node.val});

            if (node.left != null) {
                queue.offer(new Object[]{node.left, row + 1, col - 1});
            }
            if (node.right != null) {
                queue.offer(new Object[]{node.right, row + 1, col + 1});
            }
        }

        for (int i = minColumn; i <= maxColumn; i++) {
            List<int[]> sortedColumn = map.get(i);

            sortedColumn.sort((a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                } else {
                    return Integer.compare(a[1], b[1]);
                }
            });

            List<Integer> columnValues = new ArrayList<>();
            for (int[] pair : sortedColumn) {
                columnValues.add(pair[1]);
            }
            resultList.add(columnValues);
        }

        return resultList;

    }

    public List<Integer> rightSideView(TreeNode root) {

        int count = 0;
        List<Integer> list = new ArrayList<>();

        rightSideViewHelper(root, count, list);

        return list;
    }

    public void rightSideViewHelper(TreeNode root, int count, List<Integer> list) {

        if (root == null) return;
        if (count == list.size()) {
            list.add(root.val);
        }

        rightSideViewHelper(root.right, count + 1, list);
        rightSideViewHelper(root.left, count + 1, list);
    }

    public boolean isSymmetric(TreeNode root) {

        TreeNode leftRoot = root.left;
        TreeNode rightRoot = root.right;

        return isSymmetricHelper(leftRoot, rightRoot);
    }

    public boolean isSymmetricHelper(TreeNode leftRoot, TreeNode rightRoot) {

        if (leftRoot == null && rightRoot == null) {
            return true;
        } else if (leftRoot == null || rightRoot == null) {
            return false;
        }

        if (leftRoot.val == rightRoot.val) {
            return isSymmetricHelper(leftRoot.left, rightRoot.right) && isSymmetricHelper(leftRoot.right, rightRoot.left);
        } else {
            return false;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestorHelper(root, p, q);
    }

    public TreeNode lowestCommonAncestorHelper(TreeNode currentNode, TreeNode p, TreeNode q) {

        if (currentNode == null) return null;
        if (currentNode == p || currentNode == q) return currentNode;

        TreeNode leftSubtree = lowestCommonAncestorHelper(currentNode.left, p, q);
        TreeNode rightSubtree = lowestCommonAncestorHelper(currentNode.right, p, q);

        if (leftSubtree != null && rightSubtree != null) return currentNode;
        else if (leftSubtree == null && rightSubtree != null) return rightSubtree;
        else if (leftSubtree != null && rightSubtree == null) return leftSubtree;
        else return null;

    }

    class Pair {

        TreeNode node;
        int num;

        Pair(TreeNode node, int num) {
            this.node = node;
            this.num = num;
        }

    }

    public int widthOfBinaryTree(TreeNode root) {

        if (root == null) return 0;
        int result = 0;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {

            int size = queue.size();

            int min = queue.peek().num;
            int first = 0, last = 0;

            for (int i = 0; i < size; i++) {

                int currentIndex = queue.peek().num - min;
                TreeNode node = queue.peek().node;

                queue.poll();

                if (i == 0) first = currentIndex;
                if (i == size - 1) last = currentIndex;

                if (node.left != null) {
                    queue.offer(new Pair(node.left, currentIndex * 2 + 1));
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, currentIndex * 2 + 2));
                }
            }
            result = Math.max(result, last - first + 1);
        }

        return result;
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        findParents(parentMap, root, null);

        Queue<TreeNode> queue = new LinkedList<>();

        Set<TreeNode> visited = new HashSet<>();

        int currentDistance = 0;

        queue.offer(target);
        visited.add(target);

        while (!queue.isEmpty() && currentDistance < k) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                if (currentNode.left != null && !visited.contains(currentNode.left)) {
                    visited.add(currentNode.left);
                    queue.offer(currentNode.left);
                }

                if (currentNode.right != null && !visited.contains(currentNode.right)) {
                    visited.add(currentNode.right);
                    queue.offer(currentNode.right);
                }

                TreeNode parent = parentMap.get(currentNode);

                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    queue.offer(parent);
                }
            }
            currentDistance++;
        }

        while (!queue.isEmpty()) {
            result.add(queue.poll().val);
        }

        return result;
    }

    private void findParents(Map<TreeNode, TreeNode> parentMap, TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        parentMap.put(node, parent);
        findParents(parentMap, node.left, node);
        findParents(parentMap, node.right, node);
    }

    public int countNodes(TreeNode root) {
        return countHelper(root);
    }

    public int countHelper(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);

        if (leftHeight == rightHeight) return (1 << leftHeight) - 1;
        else {
            return 1 + countHelper(root.left) + countHelper(root.right);
        }

    }

    private Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return treeMaker(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode treeMaker(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = preOrder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int inRootIndex = inorderMap.get(rootVal);
        int leftSubTreeSize = inRootIndex - inStart;

        root.left = treeMaker(preOrder, preStart + 1, leftSubTreeSize + preStart, inOrder, inStart, inRootIndex - 1);
        root.right = treeMaker(preOrder, preStart + leftSubTreeSize + 1, preEnd, inOrder, inRootIndex + 1, inEnd);

        return root;
    }

    public TreeNode treeMakerPost(int[] postorder, int postStart, int postEnd, int[] inOrder, int inStart, int inEnd) {
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int inRootIndex = inorderMap.get(rootVal);
        int leftSubTreeSize = inRootIndex - inStart;
        int rightSubTreeSize = inEnd - inRootIndex;

        root.right = treeMaker(postorder, postEnd - rightSubTreeSize, postEnd - 1, inOrder, inRootIndex + 1, inEnd);
        root.left = treeMaker(postorder, postStart, postStart + leftSubTreeSize - 1, inOrder, inStart, inRootIndex - 1);

        return root;
    }

    public TreeNode buildTreePost(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null || postorder.length == 0 || inorder.length == 0) {
            return null;
        }

        int[] preorder = new int[postorder.length];
        for (int i = 0; i < postorder.length - 1; i++) {
            preorder[preorder.length - i - 1] = postorder[i];
        }
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return treeMaker(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode prev = null;

    public void flatten(TreeNode root) {
        flattenHelper(root);
    }

    public void flattenHelper(TreeNode root) {
        if (root == null) return;

        flattenHelper(root.right);
        flattenHelper(root.left);
        root.right = prev;
        root.left = null;
        prev = root;

    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        verticalTraversal(root);

    }
}

