package datastructures;

class Node<T> {
    T data;
    Node<T> next;

    public Node(T data){
        this.data = data;
        this.next = null;
    }

}


public class LinkedList<T> {
    Node<T> head;

    public LinkedList() {
        head = null;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void delete(T data){
        Node<T> current=head;
        if (current.data==data) {
            head = current.next;
            return;
        }
        while (current.next != null) {
            if (current.next.data==data) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

}
