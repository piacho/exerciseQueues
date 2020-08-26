package com.greg.exercisequeues.customlinkedlist;

@SuppressWarnings("unchecked")
public class DoublyLinkedListImpl<T> implements CustomList<T> {

    private Node<T> head = null;
    private Node<T> tail = null;

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public long size() {
        long size = isEmpty() ? 0 : 1;
        Node<T> node = this.head;
        if (!isEmpty()) {
            while (node.getNextNode() != null) {
                size += 1;
                node = node.getNextNode();
            }
        }
        return size;
    }

    @Override
    public void addElementEnd(T element) {
        Node<T> node = new Node(element);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.setNextNode(node);
            node.setPreviousNode(this.tail);
            this.tail = node;
            this.tail.setNextNode(null);
        }
    }

    @Override
    public void addElementFront(T element) {
        Node<T> node = new Node(element);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            this.head.setPreviousNode(node);
            node.setNextNode(this.head);
            this.head = node;
            this.head.setPreviousNode(null);
        }
    }

    @Override
    public T getElement() {
        Node<T> node = this.head;
        Node<T> nodeBack = this.tail;
        T element = null;
        if (!isEmpty()) {
            element = node.getData();
            this.head = node.getNextNode();
            this.tail = nodeBack.getPreviousNode();
        }
        return element;
    }
}
