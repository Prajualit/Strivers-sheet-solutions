import java.util.List;

public class MiddleNode {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public void addLast(int val, ListNode head) {
            if (val == 0) {
                return;
            }
            ListNode newNode = new ListNode(val);
            if (head == null) {
                head = newNode;
                return;
            }

            ListNode currNode = head;
            while (currNode.next != null) {
                currNode = currNode.next;
            }
            currNode.next = newNode;
        }
    }

    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public ListNode deleteMiddle(ListNode head) {

        if (head == null || head.next == null) {
            return null;
        }
        if (head.next.next == null) {
            head.next = null;
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
        ListNode prevSlow = null;

        while (fast != null && fast.next != null) {
            prevSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prevSlow.next = slow.next;
        return head;
    }

    public ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        return prev;
    }

    public ListNode oddEvenList(ListNode head) {

        ListNode lastNode = head;
        while (lastNode != null && lastNode.next != null) {
            lastNode = lastNode.next.next;
        }

        ListNode newList = new ListNode();
        int count = 1;
        ListNode currNode = head;

        while (currNode != null && currNode.next != null) {
            if (count % 2 == 0) {
                newList.addLast(currNode.val, currNode);
            }
            currNode = currNode.next;
            count++;
        }

        currNode = newList;

        while (currNode != null) {
            lastNode.addLast(currNode.val, currNode);
            currNode = currNode.next;
        }

        return head;
    }

    public ListNode sortList(ListNode head) {

        ListNode currNode = head;
        ListNode nextNode = head.next;

        while (nextNode != null) {
            if (currNode.val > nextNode.val) {
                currNode.next = nextNode.next;
                nextNode.next = currNode;
            }

            currNode = currNode.next;
            nextNode = nextNode.next;
        }

        return head;
    }


    public static void main(String[] args) {
    }
}
