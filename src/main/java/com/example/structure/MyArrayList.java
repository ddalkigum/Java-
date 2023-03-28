package com.example.structure;

import com.example.structure.interfaces.MyList;

import java.util.ArrayList;
import java.util.Arrays;

public class MyArrayList<E> implements MyList<E>, Cloneable {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};

    private int size;

    Object[] array;

    public MyArrayList() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    public MyArrayList(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }

    private void resize() {
        int array_capacity = array.length;

        if (Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        // 용량 가득 참
        if (size == array_capacity) {
            int new_capacity = array_capacity * 2;
            array = Arrays.copyOf(array, new_capacity);
            return;
        }

        // 용량이 절반 미만
        if (size < array_capacity / 2) {
            int new_capacity = array_capacity / 2;
            array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
            return;
        }
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void addLast(E value) {
        if (size == array.length) {
            resize();
        }

        array[size] = value;
        size++;
    }

    public void add(int index, E value) {
        if (index < 0 || index > this.array.length) {
            throw new IndexOutOfBoundsException();
        }

        if (index > size) {
            addLast(value);
            return;
        }

        if (size == this.array.length) {
            resize();
        }

        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = value;
        size++;
    }

    public boolean remove(Object value) {
        int index = indexOf(value);

        if (index == -1) return false;

        remove(index);
        return true;
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();

        E element = (E) array[index];
        array[index] = null;

        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
            array[i + 1] = null;
        }

        size--;
        resize();
        return element;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        return (E)array[index];
    }

    public void set(int index, E value) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        array[index] = value;
    }

    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    public int indexOf(Object value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) return i;
        }

        return -1;
    }

    public int lastIndexOf(Object value) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(value)) return i;
        }

        return -1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }

        size = 0;
        resize();
    }

    public Object clone() throws CloneNotSupportedException {
        MyArrayList<?> cloneList = (MyArrayList<?>) super.clone();
        cloneList.array = new Object[size];
        System.arraycopy(array, 0, cloneList.array, 0, size);

        return cloneList;
    }
}
