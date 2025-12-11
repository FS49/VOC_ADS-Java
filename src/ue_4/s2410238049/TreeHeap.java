package ue_4.s2410238049;

import lecture.chapter03.IComparator;
import lecture.chapter03.IKey;
import ue_3.s2410238049.MyBTree;

public class TreeHeap extends MyBTree {
    private final IComparator comparator;

    public TreeHeap(IComparator comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator must not be null");
        }
        this.comparator = comparator;
    }

    @Override
    public void insert(Object data) {
        Node newNode = new Node(null, data, null);
        breadthFirstAppend(newNode);
        upHeap(newNode);
    }

    @Override
    public void remove(Object data) {
        Node toRemove = searchNodeByData(root, data);
        if (toRemove == null) {
            return;
        }

        Node lastNode = findLastNode();
        if (lastNode == toRemove) {
            removeLastNode(lastNode);
            return;
        }

        Object lastData = lastNode.data;
        removeLastNode(lastNode);
        toRemove.data = lastData;

        if (toRemove.parent != null &&
                comparator.compare(toRemove.data, toRemove.parent.data) < 0) {
            upHeap(toRemove);
        } else {
            downHeap(toRemove);
        }
    }

    public Object removeMin() {
        if (root == null) {
            return null;
        }
        Object minData = root.data;
        remove(root.data);
        return minData;
    }

    protected Node heapSearch(Node current, IKey key) {
        if (current == null) {
            return null;
        }

        if (key.matches(current.data)) {
            return current;
        }

        if (comparator.compare(current.data, key) >= 0) {
            return null;
        }

        Node found = heapSearch(current.left, key);
        if (found == null) {
            found = heapSearch(current.right, key);
        }
        return found;
    }

    public Object heapSearch(IKey key) {
        Node found = heapSearch(root, key);
        return (found != null) ? found.data : null;
    }

    private Node searchNodeByData(Node current, Object data) {
        if (current == null) {
            return null;
        }

        int cmp = comparator.compare(current.data, data);
        if (cmp == 0) {
            return current;
        }

        if (cmp > 0) {
            return null;
        }

        Node found = searchNodeByData(current.left, data);
        if (found == null) {
            found = searchNodeByData(current.right, data);
        }
        return found;
    }

    private void upHeap(Node node) {
        if (node == null || node.parent == null) {
            return;
        }

        if (comparator.compare(node.data, node.parent.data) < 0) {
            swapData(node, node.parent);
            upHeap(node.parent);
        }
    }

    private void downHeap(Node node) {
        if (node == null) {
            return;
        }

        Node smallerChild = findSmallerChild(node);
        if (smallerChild == null) {
            return;
        }

        if (comparator.compare(smallerChild.data, node.data) < 0) {
            swapData(node, smallerChild);
            downHeap(smallerChild);
        }
    }

    private Node findSmallerChild(Node node) {
        if (node.left == null && node.right == null) {
            return null;
        }
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }

        return (comparator.compare(node.left.data, node.right.data) <= 0)
                ? node.left : node.right;
    }

    private void swapData(Node a, Node b) {
        Object tmp = a.data;
        a.data = b.data;
        b.data = tmp;
    }

    private Node findLastNode() {
        if (root == null) {
            return null;
        }

        Node last = root;
        lib.Queue queue = new lib.Queue();
        queue.enqueue(root);

        while (!queue.empty()) {
            last = (Node) queue.dequeue();
            if (last.left != null) {
                queue.enqueue(last.left);
            }
            if (last.right != null) {
                queue.enqueue(last.right);
            }
        }
        return last;
    }

    private void removeLastNode(Node lastNode) {
        if (lastNode == null) {
            return;
        }

        if (lastNode.parent == null) {
            root = null;
        } else if (lastNode.isLeftChild()) {
            lastNode.parent.left = null;
        } else if (lastNode.isRightChild()) {
            lastNode.parent.right = null;
        }

        lastNode.parent = null;
        lastNode.left = null;
        lastNode.right = null;
    }

    public boolean isHeap() {
        return isHeap(root);
    }

    private boolean isHeap(Node current) {
        if (current == null) {
            return true;
        }

        if (current.left != null &&
                comparator.compare(current.data, current.left.data) > 0) {
            return false;
        }
        if (current.right != null &&
                comparator.compare(current.data, current.right.data) > 0) {
            return false;
        }

        return isHeap(current.left) && isHeap(current.right);
    }
}
