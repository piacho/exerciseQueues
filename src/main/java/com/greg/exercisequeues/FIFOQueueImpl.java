package com.greg.exercisequeues;

import com.greg.exercisequeues.customlinkedlist.DoublyLinkedListImpl;

public class FIFOQueueImpl<T extends Number> implements Queue<T> {

    private DoublyLinkedListImpl<T> list = new DoublyLinkedListImpl<>();

    @Override
    public void add(T number) {

        this.list.addElementEnd(number);

    }

    @Override
    public T get() throws QueueException {

        if (!this.list.isEmpty()) {
            T number = this.list.getElement();
            return number;
        } else {
            throw new QueueException("Cannot retrieve element from an empty queue");
        }

    }

    @Override
    public boolean isEmpty() {

        return this.list.isEmpty();

    }

    @Override
    public long size() {

        return this.list.size();

    }
}
