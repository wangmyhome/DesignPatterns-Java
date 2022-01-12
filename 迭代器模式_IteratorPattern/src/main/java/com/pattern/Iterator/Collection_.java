package com.pattern.Iterator;

public interface Collection_<E> {
    void add(E o);
    int size();

    Iterator_ iterator();
}