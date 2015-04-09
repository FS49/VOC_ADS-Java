package kapitel_3.work.experiments;

import kapitel_3.work.generics.AVLTree;
import kapitel_3.work.generics.IComparator;
import kapitel_3.work.generics.IPGFTree;

public class ExperimentalAVLTree<T> extends AVLTree<T> implements IPGFTree<T> {
    Node<T> newAVLNode = null;
    boolean balanceCalled = true;
    
    public ExperimentalAVLTree(IComparator<T> comparator) {
        super(comparator);
    }

    protected void grownTo(Node<T> node) {
        Node<T> parent = (Node<T>) node.parent;
        
        if (parent != null) {
            if (node.isLeftChild()) {         // Update the balance of the parent
                parent.balance--;             // according to the side on which the
            } else if (node.isRightChild()) { // sub-tree has grown
                parent.balance++;
            }
            if (Math.abs(parent.balance) == 1) {
                grownTo(parent);
            }
        }
    }
    
    protected void justBalance(Node<T> node) {
        Node<T> parent = (Node<T>) node.parent; // tree indicated by the passed node has
        
        if (parent != null) {
            switch(parent.balance) {
            case -2: case +2:
                balance(parent);
                break;
            case -1: case +1:
                justBalance(parent);
                break;
            }
        }
    }
    
    public void insert(T data) {
        if (!balanceCalled) {
            justBalance(newAVLNode);                // The overwritten insert-
        }
        newAVLNode = new Node<T>(null, data, null); // method reports the growth
        root = insert(root, newAVLNode);            // of the tree up to the
        grownTo(newAVLNode);
        balanceCalled = false;
    }
    
    public void grown() {
        justBalance(newAVLNode);
        balanceCalled = true;
    }

    @Override
    public String getSubTreeFormatFormat(
            kapitel_3.work.generics.BTree.Node<T> node) {
        return "";
    }

    @Override
    public String getNodeFormat(kapitel_3.work.generics.BTree.Node<T> node) {
        return "label={[draw=none]below:\\tiny " + ((Node<T>) node).balance + "}";
    }

    @Override
    public String getChildrenFormat(kapitel_3.work.generics.BTree.Node<T> node) {
        return "";
    }

    @Override
    public String getChildFormat(kapitel_3.work.generics.BTree.Node<T> node) {
        return "";
    }

    @Override
    public String getEdgeFromParentFormat(
            kapitel_3.work.generics.BTree.Node<T> node) {
        return "";
    }
}
