package ue_3.s2410238049;

import lecture.chapter03.BTree;

public class MyBTree extends BTree {

    public void insert(Object data) {
        Node newNode = new Node(null, data, null);
        breadthFirstAppend(newNode);
    }

    protected void breadthFirstAppend(Node newNode) {
        if (root == null) {
            root = newNode;
            return;
        }

        int h = height(root);

        for (int level = 0; level <= h; level++) {
            if (appendAtLevel(root, newNode, level)) {
                return;
            }
        }
    }

    private boolean appendAtLevel(Node node, Node newNode, int level) {
        if (node == null) {
            return false;
        }

        if (level == 0) {
            if (node.left == null) {
                node.left = newNode;
                newNode.parent = node;
                return true;
            }
            if (node.right == null) {
                node.right = newNode;
                newNode.parent = node;
                return true;
            }
            return false;
        }

        if (appendAtLevel(node.left, newNode, level - 1)) {
            return true;
        }
        return appendAtLevel(node.right, newNode, level - 1);
    }

    private static class NodeRef {
        Node node;
        Node parent;
    }

    private void findLast(Node current, Node parent, NodeRef last) {
        if (current == null) {
            return;
        }

        last.node = current;
        last.parent = parent;

        findLast(current.left, current, last);
        findLast(current.right, current, last);
    }

    private Node findNode(Node current, Object data) {
        if (current == null) {
            return null;
        }

        if ((current.data == null && data == null) ||
                (current.data != null && current.data.equals(data))) {
            return current;
        }

        Node n = findNode(current.left, data);
        if (n != null) {
            return n;
        }

        return findNode(current.right, data);
    }

    protected void remove(Node node) {
        if (root == null || node == null) {
            return;
        }

        NodeRef last = new NodeRef();
        findLast(root, null, last);

        if (last.node == null) {
            return;
        }

        if (node != last.node) {
            node.data = last.node.data;
        }

        if (last.parent == null) {
            root = null;
        } else if (last.parent.left == last.node) {
            last.parent.left = null;
        } else {
            last.parent.right = null;
        }

        last.node.parent = null;
    }

    public void remove(Object data) {
        remove(findNode(root, data));
    }
}
