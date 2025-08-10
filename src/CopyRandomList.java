import com.sun.nio.sctp.PeerAddressChangeNotification;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {

        Map<Node, Node> oldToNew = new HashMap<>();

        Node originalNode = head;

        while (originalNode != null) {
            Node newNode = new Node(originalNode.val);
            oldToNew.put(originalNode, newNode);
            originalNode = originalNode.next;
        }

        originalNode = head;

        while (originalNode != null) {
            Node clonedNode = oldToNew.get(originalNode);
            clonedNode.next = oldToNew.get(originalNode.next);
            clonedNode.random = oldToNew.get(originalNode.random);

            originalNode = originalNode.next;
        }

        return oldToNew.get(head);
    }

    public static void main(String[] args) {

    }
}
