package org.unesp.dataStructure;

import org.unesp.entities.Redistributor;

public class CircularList {

    //Declaring head and tail pointer as null.
    public Node head = null;
    public Node tail = null;

    public void add(Redistributor redistributor) {

        Node newNode = new Node(redistributor);

        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.setNext(head);
        } else {
            //tail will point to new node.
            tail.setNext(newNode);
            //New node will become new tail.
            tail = newNode;
            //Since, it is circular linked list tail will point to head.
            tail.setNext(head);
        }
    }

    public Redistributor getRedistributorAtPosition(int position) {
        int cont = 0;
        Node current = head;
        do {
            current = current.getNext();
            cont++;
        } while (cont != position);
        return current.getRedistributor();
    }

    public Redistributor getNextRedistributor(Redistributor redistributor) {
        Node current = head;
        do {
            current = current.getNext();
        } while (!current.getRedistributor().equals(redistributor));
        return current.getNext().getRedistributor();
    }

    public void display() {
        Node current = head;
        if (head == null) {
            System.out.println("List is empty");
        } else {
            System.out.println("Nodes of the circular linked list: ");
            do {
                System.out.println(" " + current.getRedistributor());
                current = current.getNext();
            } while (current != head);
            System.out.println();
        }
    }
}
