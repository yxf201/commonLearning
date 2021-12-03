package learning.dataStructure;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private int count;
    private int capacity;
    private Map<Integer, Node> map;

    private Node head;
    private Node tail;

    private static class Node{
        int key;
        int value;
        Node pre;
        Node next;

        Node(int key){
            this.key = key;
        }

        Node(int key, int value){
            this(key);
            this.value = value;
        }

        Node(int key, Node pre, Node next){
            this(key);
            this.pre = pre;
            this.next = next;
        }
    }


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;

        this.head = new Node(-1);
        this.tail = new Node(-1);
        head.next = tail;
        tail.pre = head;

        this.map = new HashMap<>();
    }

    public int get(int key) {
        Node node = map.get(key);
        if( node == null ){
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if( node == null ){
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            count++;
            addFirst(newNode);
            if( count > capacity ){
                map.remove(this.tail.pre.key);
                remove(this.tail.pre);
                count--;
            }
        }else{
            node.value = value;
            moveToHead(node);
        }
    }

    private void moveToHead(Node node){
        remove(node);
        addFirst(node);
    }

    private void remove(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
    }

    private void addFirst(Node node){
        Node next = this.head.next;
        head.next = node;
        node.pre = head;
        node.next = next;
        next.pre = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */