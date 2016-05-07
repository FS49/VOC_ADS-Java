package uebung_4.ue;

import kapitel_3.vl.BTree;
import kapitel_3.vl.ReferenceKey;

public class UE4BTree extends BTree {
    private Node current = null;

    protected Node breadthFirstAppend(Node newNode) {
        if (current == null) {
            root = newNode;
        } else {
            if (current.isLeftChild()) {
                current = current.parent;
                current.right = newNode;
            } else {
                while (current.isRightChild()) {
                    current = current.parent;
                }
                if (current.isLeftChild()) {
                    current = current.parent.right;
                }
                while (current.left != null) {
                    current = current.left;
                }
                current.left = newNode;
            }
        }
        
        newNode.parent = current;
        current = newNode;
        
        return newNode;
    }

    public void insert(Object data) {
        breadthFirstAppend(new Node(null, data, null));	
    }
    
    protected void remove(Node node) {
        if (node != null) {
            Node current = node;
            while (current.left != null) {
                current = current.left;
            }
            node.data = current.data;
            this.removeLeaf(current);
        }
    }
    
    public void remove(Object data) {
        ReferenceKey key = new ReferenceKey(data);
        Node toRemove = depthFirstSearch(root, key);
        
        remove(toRemove);
    }
}
