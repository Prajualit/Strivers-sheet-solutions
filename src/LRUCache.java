import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    public static class ListNode {
        int key;
        int value;
        ListNode next;
        ListNode prev;

        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Map<Integer, ListNode> map = new HashMap<>();
    int capacity;
    ListNode head;
    ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();

        head = new ListNode(0, 0);
        tail = new ListNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        ListNode node = map.get(key);

        delete(node);
        insertAtFirst(node);

        return node.value;
    }

    public void put(int key, int value) {

        if (map.containsKey(key)) {
            delete(map.get(key));
        }
        if (map.size() == capacity) {
            delete(tail.prev);
        }

        ListNode newNode = new ListNode(key, value);
        insertAtFirst(newNode);
        map.put(key, newNode);
    }

    public void delete(ListNode node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void insertAtFirst(ListNode node) {
        map.put(node.key, node);
        node.next = head.next;
        node.prev = head;
        head.prev.next = node.next;
        head.next = node;
    }

}
