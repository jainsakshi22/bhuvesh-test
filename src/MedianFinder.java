import java.util.HashMap;
import java.util.Map;

public class MedianFinder {

    class DLinkedNode {
        int val;
        DLinkedNode prev, next;
    }

    private Map<Integer, DLinkedNode> map = new HashMap<>();
    private int size;
    private DLinkedNode head, tail;

    DLinkedNode min = null;
    DLinkedNode max = null;
    boolean isOdd = false;

    /** initialize your data structure here. */
    public MedianFinder() {
        this.size = 0;

        head = new DLinkedNode();
        tail = new DLinkedNode();

        head.next = tail;
        tail.prev = head;
    }

    private DLinkedNode searchNext(DLinkedNode midNode, int num) {
        while (midNode.next.next !=null && midNode.val < num) {
            midNode = midNode.next;
        }
        return midNode;
    }

    private DLinkedNode searchPrev(DLinkedNode midNode, int num) {
        while (midNode.prev !=null && midNode.val > num) {
            midNode = midNode.prev;
        }
        midNode = midNode.prev != null ? midNode : head;
        return midNode;
    }

    private void addNode(DLinkedNode newNode, DLinkedNode oldNode) {
        /**
         * Always add the new node right after head.
         */
        newNode.prev = oldNode;
        newNode.next = oldNode.next;

        oldNode.next.prev = newNode;
        oldNode.next = newNode;
    }

    public void addNum(int num) {
        isOdd = !isOdd;
        DLinkedNode newNode = new DLinkedNode();
        newNode.val = num;
        if (size < 2) {
            //map.put(key, newNode);
            if (isOdd) {
                addNode(newNode, head);
                max = newNode;
            } else {
                if (num < max.val) {
                    addNode(newNode, head);
                    min = newNode;
                } else {
                    min = max;
                    max = newNode;
                    addNode(newNode, min);
                }


            }
        } else {
            if (!isOdd) {
                if (num < min.val) { //Insert in beginning
                    DLinkedNode oldNode = searchPrev(min, num);
                    addNode(newNode, oldNode);

                } else if (num >= max.val) {
                    DLinkedNode oldNode = searchNext(max, num);
                    addNode(newNode, oldNode);
                    min = max;
                    max = max.next;
                } else if (num > min.val) {
                    addNode(newNode, min);
                    min = newNode;
                    //min = newNode;
                }
            } else {
                if (num < min.val) { //Insert in beginning
                    DLinkedNode oldNode = searchPrev(min, num);
                    addNode(newNode, oldNode);
                    max = min;
                    min = min.prev;
                } else if (num >= max.val) {
                    DLinkedNode oldNode = searchNext(max, num);
                    addNode(newNode, oldNode);
                } else if (num > min.val) {
                    addNode(newNode, min);
                    min = newNode;
                }
            }

        }
        ++size;
    }

    public double findMedian() {
        if (size == 0) {
            return -1;
        }
        double median = 0.0;
        if (isOdd) {
            median = max.val;
        } else {
            median = (min.val + max.val) * 0.5;
        }
        return median;
    }

    public static void main(String[] args) {
         MedianFinder obj = new MedianFinder();
         obj.addNum(12);
        System.out.println(obj.findMedian());
        obj.addNum(10);
        System.out.println(obj.findMedian());
        obj.addNum(13);
        System.out.println(obj.findMedian());
        obj.addNum(11);
        System.out.println(obj.findMedian());
//        obj.addNum(5);
//        System.out.println(obj.findMedian());
//        obj.addNum(0);
//        System.out.println(obj.findMedian());
//        obj.addNum(6);
//        System.out.println(obj.findMedian());
//        obj.addNum(3);
//        System.out.println(obj.findMedian());
//        obj.addNum(1);
//        System.out.println(obj.findMedian());
//        obj.addNum(0);
//        System.out.println(obj.findMedian());
//        obj.addNum(0);
//        System.out.println(obj.findMedian());

    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */