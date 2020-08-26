package com.greg.exercisequeues.customlinkedlist;

interface CustomListManipulation<T> {
    void addElementFront(T element);
    void addElementEnd(T element);
    T getElement();
}

