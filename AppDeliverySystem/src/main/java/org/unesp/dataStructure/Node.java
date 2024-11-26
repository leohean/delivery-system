package org.unesp.dataStructure;

import org.unesp.entities.Redistributor;

public class Node {
    Redistributor redistributor;
    Node next;

    public Node(Redistributor redistributor) {
        this.redistributor = redistributor;
    }

    public Redistributor getRedistributor() {
        return redistributor;
    }

    public Node getNext() {
        return next;
    }
}