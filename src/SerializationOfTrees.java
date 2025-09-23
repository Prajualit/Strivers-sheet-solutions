import java.util.Objects;

public class SerializationOfTrees {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {

            StringBuilder rootString = new StringBuilder();

            serializeHelper(root, rootString);

            return rootString.deleteCharAt(rootString.length() - 1).toString();
        }

        public void serializeHelper(TreeNode root, StringBuilder rootString) {

            if (root == null) {
                rootString.append("n").append(",");
                return;
            }

            rootString.append(root.val).append(",");

            serializeHelper(root.left, rootString);
            serializeHelper(root.right, rootString);

        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {

            String[] dataArray = data.split(",");
            return deserializeHelper(dataArray, new int[1]);


        }

        public TreeNode deserializeHelper(String[] dataArray, int[] index) {
            if (Objects.equals(dataArray[index[0]], "n")) {
                index[0]++;
                return null;
            }

            TreeNode root = new TreeNode(Integer.parseInt(dataArray[index[0]]));
            index[0]++;
            root.left = deserializeHelper(dataArray, index);
            root.right = deserializeHelper(dataArray, index);


            return root;
        }
    }

}
