package com.greg.exercisequeues.customlinkedlist;

@SuppressWarnings("unchecked")
class Node<T> {

    private T data;
    private Node<T> previousNode;
    private Node<T> nextNode;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public Node<T> getPreviousNode() {
        return previousNode;
    }
}
