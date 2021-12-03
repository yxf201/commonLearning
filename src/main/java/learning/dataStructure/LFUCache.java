package learning.dataStructure;

import java.util.HashMap;

public class LFUCache {
    private class Node {
        int key;
        int value;
        Node pre;
        Node next;
        int frequent = 1;

        Node() {
        }

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString(){
            return "(" + key + "," + value + " : " + frequent + ")";
        }
    }

    private class DoubleList {
        Node head;
        Node tail;

        /** 其实，可以冗余一个frequent的，但是node里已经有了，目前够用，懒得再维护一个了 */

        int size = 0;

        DoubleList() {
            this.head = new Node();
            this.tail = new Node();

            head.next = tail;
            tail.pre = head;
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for( Node node = head.next; node != tail; node = node.next ){
                sb.append(node.toString());
                if( node != tail.pre ){
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }

        private void removeNode(Node node) {
            Node p = node.pre;
            Node n = node.next;
            p.next = n;
            n.pre = p;

            this.size--;
            if( this.size == 0 && LFUCache.this.min == node.frequent ){
                LFUCache.this.min++;
            }
            LFUCache.this.size--;

            //nodeMap.remove(node.key);
        }

        //add to head
        private void add(Node node) {
            Node p = head;
            Node n = head.next;
            node.pre = p;
            node.next = n;
            p.next = node;
            n.pre = node;

            this.size++;
            if( node.frequent == 1 ){
                LFUCache.this.min = 1;
            }
            LFUCache.this.size++;

            //nodeMap.put(node.key, node);
        }

        private void removeTail() {
            if( size > 0 ){
                nodeMap.remove(tail.pre.key);
                this.removeNode(tail.pre);
            }
        }
    }

    private int capacity;
    private int min;//min frequent in this LFUCache
    private int size;

    private HashMap<Integer, Node> nodeMap;
    private HashMap<Integer, DoubleList> listMap;//<frequent, DoubleList<Node>>

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.min = 1;
        this.size = 0;

        this.nodeMap = new HashMap<>();
        this.listMap = new HashMap<>();
    }

    public int get(int key) {
        Node node;
        if (size <= 0 || (node = nodeMap.get(key)) == null) {
            return -1;
        }
        listMap.get(node.frequent).removeNode(node);
        addToFrequent(++node.frequent, node);
        return node.value;
    }

    private void addToFrequent(int frequent, Node node) {
        listMap.putIfAbsent(frequent, new DoubleList());
        DoubleList list = listMap.get(frequent);
        list.add(node);
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }

        if( nodeMap.get(key) == null ){
            if( this.size >= this.capacity ){
                listMap.get(min).removeTail();
            }

            Node node = new Node(key, value);
            nodeMap.put(key, node);
            addToFrequent(1, node);
        }else{
            Node node = nodeMap.get(key);
            node.value = value;

            listMap.get(node.frequent).removeNode(node);
            addToFrequent(++node.frequent, node);
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */