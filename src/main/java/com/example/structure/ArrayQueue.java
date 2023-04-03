package com.example.structure;

import com.example.structure.interfaces.MyQueue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements MyQueue<E> {

    private static final int DEFAULT_CAPACITY = 64;

    private Object[] array;
    private int size;

    private int front; // 시작 인덱스
    private int rear; // 마지막 인덱스

    public ArrayQueue() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    public ArrayQueue(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    private void resize(int newCapacity) {
        int arrayCapacity = array.length;
        Object[] newArray = new Object[newCapacity];

        for (int i = 1, j = front + 1; i <= size; i++, j++) {
            newArray[i] = array[j % arrayCapacity];
        }

        this.array = null;
        this.array = newArray;

        front = 0;
        rear = size;
    }

    @Override
    public boolean offer(E value) {
        if ((rear + 1) % array.length == front) {
            resize(array.length * 2);
        }

        rear = (rear + 1) % array.length;
        array[rear] = value;
        size++;

        return true;
    }

    @Override
    public E poll() {
        if (size == 0) return null;

        front = (front + 1) % array.length;

        @SuppressWarnings("unchecked")
        E value = (E) array[front];
        array[front] = null;
        size--;

        if (array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }
        return value;
    }

    public E remove() {
        E value = poll();
        if (value == null) throw new NoSuchElementException();

        return value;
    }

    @Override
    public E peek() {
        if (size == 0) return null;

        @SuppressWarnings("unchecked")
        E value = (E) array[(front + 1) % array.length];
        return value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E value) {
        int start = (front + 1) % array.length;

        for (int i = 0; i < size; i++) {
            if (array[start].equals(value)) return true;

            start = (start + 1) % array.length;
        }

        return false;
    }

    public void clear() {
        Arrays.fill(array, null);

        front = 0;
        rear = 0;
        size = 0;
    }
}
