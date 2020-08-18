package com.greg.exercisequeues.common;

public interface QueueManipulation<T> {
    void add(T number);
    T get() throws QueueException;
}
