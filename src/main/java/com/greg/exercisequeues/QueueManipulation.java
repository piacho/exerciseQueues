package com.greg.exercisequeues;

interface QueueManipulation<T> {
    void add(T number);
    T get() throws QueueException;
}
