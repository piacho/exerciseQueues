package com.greg.exercisequeues.customlinkedlist;

public interface CustomList<T> extends CustomListAnalysis, CustomListManipulation<T> {
    boolean isEmpty();
    long size();
    void addElementFront(T element);
    void addElementEnd(T element);
    T getElement();
}
