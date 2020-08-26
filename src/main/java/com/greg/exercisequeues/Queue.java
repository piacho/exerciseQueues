package com.greg.exercisequeues;

interface Queue<T> extends QueueAnalysis, QueueManipulation<T> {
    boolean isEmpty();
    long size();
    void add(T number);
    T get() throws QueueException;
}
