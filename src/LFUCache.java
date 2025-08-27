import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    public static class Node {

        int key;
        int value;
        int frequency;
        Node next;
        Node prev;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }

    public static class DoubleLinkedList {

        Node head, tail;
        int size;

        DoubleLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void add(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLast() {
            if (size == 0) return null;

            Node node = tail.prev;
            remove(node);
            return node;
        }

    }

    int capacity;
    int minFreq;
    Map<Integer, Node> nodeMap;
    Map<Integer, DoubleLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        nodeMap = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {

        if (!nodeMap.containsKey(key)) return -1;

        Node node = nodeMap.get(key);
        updateFreq(node);

        return node.value;
    }

    public void put(int key, int value) {

        if (capacity == 0) return;

        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            node.value = value;
            updateFreq(node);
        } else {
            if (nodeMap.size() == capacity) {
                DoubleLinkedList minList = freqMap.get(minFreq);
                Node removedNode = minList.removeLast();
                nodeMap.remove(removedNode.key);
            }
            Node newNode = new Node(key, value);
            nodeMap.put(newNode.key, newNode);
            freqMap.computeIfAbsent(1, k -> new DoubleLinkedList()).add(newNode);
            minFreq = 1;
        }
    }

    public void updateFreq(Node node) {

        int oldFreq = node.frequency;
        DoubleLinkedList oldList = freqMap.get(oldFreq);

        oldList.remove(node);

        if (oldFreq == minFreq && oldList.size == 0) {
            minFreq++;
        }

        node.frequency++;
        freqMap.computeIfAbsent(node.frequency, k -> new DoubleLinkedList()).add(node);

    }
}
