package org.unesp.dataStructure;

import org.unesp.entities.Redistributor;

public class Node {
    private Redistributor redistributor;
    private Node next;

    public Node(Redistributor redistributor) {
        this.redistributor = redistributor;
    }

    public Redistributor getRedistributor() {
        return redistributor;
    }

    public void setRedistributor(Redistributor redistributor) {
        this.redistributor = redistributor;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}