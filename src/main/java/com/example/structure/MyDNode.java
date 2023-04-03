package com.example.structure;

public class MyDNode<E> {

    E data;
    MyDNode<E> next;
    MyDNode<E> prev;

    public MyDNode(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
