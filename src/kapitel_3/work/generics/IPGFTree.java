package kapitel_3.work.generics;

import kapitel_3.work.generics.BTree.Node;

public interface IPGFTree<T> {
    String getSubTreeFormatFormat(Node<T> node);

    String getNodeFormat(Node<T> node);

    String getChildrenFormat(Node<T> node);

    String getChildFormat(Node<T> node);

    String getEdgeFromParentFormat(Node<T> node);
}
