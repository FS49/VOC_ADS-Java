package uebung_4.tests;

import kapitel_3.work.generics.PGFTree;
import uebung_4.ue.UE4GenericsBTree;

public class TestMyTree {
    public static void main(String[] args) {
        UE4GenericsBTree<PGFTree<Integer>.PGFProxy> myTree = new UE4GenericsBTree<PGFTree<Integer>.PGFProxy>();
        PGFTree<Integer> pgfTree = new PGFTree<Integer>(myTree);
        
        for (int i = 0; i < 63; i++) {
            myTree.insert(pgfTree.pgfProxy(i));
        }
        
        System.out.println(pgfTree);
    }
}
