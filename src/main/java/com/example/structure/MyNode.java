package com.example.structure;

public class MyNode<E> {

    E data;
    MyNode<E> next;

    public MyNode(E data) {
        this.data = data;
        this.next = null;
    }
}
