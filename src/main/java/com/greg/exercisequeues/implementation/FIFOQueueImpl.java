package com.greg.exercisequeues.implementation;

import com.greg.exercisequeues.common.QueueAnalysis;
import com.greg.exercisequeues.common.QueueException;
import com.greg.exercisequeues.common.QueueManipulation;

import java.util.LinkedList;

public class FIFOQueueImpl<T extends Number> implements QueueAnalysis, QueueManipulation<T> {

    private LinkedList<T> list = new LinkedList<>();

    @Override
    public void add(T number) {

        this.list.add(number);

    }

    @Override
    public T get() throws QueueException {

        if (!this.list.isEmpty()) {
            T number = this.list.get(0);
            this.list.remove(0);
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