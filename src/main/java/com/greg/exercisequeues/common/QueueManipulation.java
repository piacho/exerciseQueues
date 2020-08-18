package com.greg.exercisequeues.common;

public interface QueueManipulation {
    void add(Integer number);
    int get() throws QueueException;
}
