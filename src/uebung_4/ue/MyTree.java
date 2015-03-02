package uebung_4.ue;

import kapitel_3.work.generics.Tree;

public class MyTree<T> extends Tree<T> {
	private Node<T> current = null;

	protected Node<T> breadthFirstAppend(Node<T> newNode) {
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

	public void insert(T data) {
		breadthFirstAppend(new Node<T>(null, data, null));	
	}
}
