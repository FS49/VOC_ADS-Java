package uebung_4.ue;

import kapitel_3.vl.IKey;
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
	
	public void remove(IKey key) {
	    Node toRemove = depthFirstPreOrderSearch(root, key);
	    
	    if (toRemove != null) {
	        Node current = toRemove;
	        while (current.left != null) {
	            current = current.left;
	        }
	        toRemove.data = current.data;
	        this.removeLeaf(current);
	    }
	}
	
	public void remove(Object data) {
	    ReferenceKey key = new ReferenceKey(data);
	    
	    remove(key);
	}
}
