package com.example.structure;

import com.example.structure.interfaces.MyList;

import java.util.NoSuchElementException;

public class MyDLinkedList<E> implements MyList<E> {

    private MyDNode<E> head;
    private MyDNode<E> tail;
    private int size;

    public MyDLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    protected MyDNode<E> getHead() {
        return head;
    }

    protected MyDNode<E> getTail() {
        return tail;
    }

    private MyDNode<E> search(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        // 인덱스의 값이 절반 초과인 경우
        if (index + 1 > size / 2) {
            MyDNode<E> node = tail;

            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }

            return node;
        }

        MyDNode<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }

    public void addFirst(E value) {
        MyDNode<E> newNode = new MyDNode<>(value);
        newNode.next = head;

        if (head != null) {
            head.prev = newNode;
        }

        head = newNode;
        size++;

        if (head.next == null) {
            tail = head;
        }
    }

    @Override
    public boolean add(E value) {
        MyDNode<E> newNode = new MyDNode<>(value);

        if (size == 0) {
            addFirst(value);
            return true;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public void add(int index, E value) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        if (index == 0) {
            addFirst(value);
            return;
        }

        if (index == size) {
            add(value);
            return;
        }

        MyDNode<E> prevNode = search(index - 1);
        MyDNode<E> nextNode = prevNode.next;
        MyDNode<E> newNode = new MyDNode<E>(value);

        prevNode.next = null;
        nextNode.prev = null;

        newNode.next = nextNode;
        newNode.prev = prevNode;

        nextNode.prev = newNode;
        prevNode.next = newNode;
        size++;
    }

    public E remove() {
        MyDNode<E> headNode = head;
        if (headNode == null) throw new NoSuchElementException();

        E element = headNode.data;
        MyDNode<E> nextNode = head.next;
        head.data = null;
        head.next = null;

        if (nextNode != null) {
            nextNode.prev = null;
        }

        head = nextNode;
        size--;

        if (size == 0) {
            tail = null;
        }

        return element;
    }

    @Override
    public boolean remove(E value) {
        MyDNode<E> prevNode = head;
        MyDNode<E> node = head;

        for (; node != null; node = node.next) {
            if (value.equals(node.data)) {
                break;
            }

            prevNode = node;
        }

        if (node == null) return false;

        if (node.equals(head)) {
            remove();
            return true;
        }

        MyDNode<E> nextNode = node.next;
        prevNode.next = null;
        node.data = null;
        node.prev = null;
        node.next = null;

        size--;

        if (nextNode == null) {
            tail = prevNode;
            return true;
        }

        nextNode.prev = null;
        nextNode.prev = prevNode;
        prevNode.next = nextNode;

        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        if (index == 0) {
            E element = head.data;
            remove();
            return element;
        }

        MyDNode<E> prevNode = search(index - 1);
        MyDNode<E> currentNode = prevNode.next;

        E element = currentNode.data;

        if (index == size - 1) {
            prevNode.next = null;
            tail = prevNode;
            size--;
            return element;
        }

        size--;
        MyDNode<E> nextNode = currentNode.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        return element;
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public void set(int index, E value) {
        MyDNode<E> node = search(index);
        node.data = value;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(Object value) {
        int index = 0;

        for (MyDNode<E> node = head; node != null; node = node.next) {
            if (value.equals(node.data)) {
                return index;
            }
            index++;
        }

        return -1;
    }

    public int lastIndexOf(Object value) {
        int index = size - 1;

        for (MyDNode<E> node = tail; node != null; node = node.prev) {
            if (value.equals(node.data)) {
                return index;
            }

            index--;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (MyDNode<E> node = head; node != null;) {
            MyDNode<E> nextNode = node.next;
            node.data = null;
            node.prev = null;
            node.next = null;
            node = nextNode;
        }

        head = null;
        tail = null;
        size = 0;
    }
}
