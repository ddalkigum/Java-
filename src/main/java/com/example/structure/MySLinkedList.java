package com.example.structure;

import com.example.structure.interfaces.MyList;

import java.util.NoSuchElementException;

// Single LinkedList
public class MySLinkedList<E> implements MyList<E>, Cloneable {

    private MyNode<E> head;
    private MyNode<E> tail;
    private int size;

    public MySLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public MyNode<E> search(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        MyNode<E> x = head;

        for (int i = 0; i < index; i++) {
            x = x.next;
        }

        return x;
    }

    public void addFirst(E value) {
        MyNode<E> newNode = new MyNode<E>(value);
        newNode.next = head;
        head = newNode;
        size++;

        if (head.next == null) tail = head;
    }

    public void addLast(E value) {
        MyNode<E> newNode = new MyNode<>(value);

        if (size == 0) {
            addFirst(value);
            return;
        }

        tail.next = newNode;
        tail = newNode;
        size++;
    }

    @Override
    public boolean add(E value) {
        addLast(value);
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
            addLast(value);
            return;
        }

        MyNode<E> prevNode = search(index - 1);
        MyNode<E> nextNode = prevNode.next;

        MyNode<E> newNode = new MyNode<E>(value);
        newNode.next = null; // 초기화
        newNode.next = nextNode;
        prevNode.next = newNode;
        size++;
    }


    public E remove() {
        MyNode<E> headNode = head;

        if (headNode == null) throw new NoSuchElementException();

        E headValue = headNode.data;
        MyNode<E> nextNode = headNode.next;
        headNode.data = null;
        headNode.next = null;

        head = nextNode;
        size--;

        if (size == 1) {
            tail = null;
        }

        return headValue;
    }

    @Override
    public boolean remove(Object value) {
        MyNode<E> prevNode = head;
        MyNode<E> currentNode = head;

        for (; currentNode != null; currentNode = currentNode.next) {
            if (value.equals(currentNode.data)) break;

            prevNode = currentNode;
        }

        if (currentNode == null) return false;

        if (currentNode.equals(head)) {
            remove();
            return true;
        }

        prevNode.next = currentNode.next;
        // tail 인 경우
        if (prevNode.next == null) {
            tail = prevNode;
        }

        currentNode.data = null;
        currentNode.next = null;
        size--;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        if (size == 1) {
            return remove();
        }

        MyNode<E> prevNode = search(index - 1);
        MyNode<E> currentNode = prevNode.next;
        MyNode<E> nextNode = currentNode.next;

        E element = currentNode.data;

        prevNode.next = nextNode;

        if (prevNode.next == null) {
            tail = prevNode;
        }

        currentNode.next = null;
        currentNode.data = null;
        size--;

        return element;
    }

    @Override
    public E get(int index) {
        E element = search(index).data;
        return element;
    }

    @Override
    public void set(int index, E value) {
        MyNode<E> foundNode = search(index);
        foundNode.data = value;
    }

    @Override
    public boolean contains(Object value) {
        int foundIndex = indexOf(value);
        return foundIndex != -1;
    }

    @Override
    public int indexOf(Object value) {
        MyNode<E> currentNode = head;
        int index = 0;

        for (; currentNode != null; currentNode = currentNode.next) {
            if (currentNode.data.equals(value)) return index;
            index++;
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
        MyNode<E> currentNode = head;
        while (currentNode != null) {
            MyNode<E> nextNode = currentNode.next;
            currentNode.next = null;
            currentNode.data = null;
            currentNode = nextNode;
        }
        head = null;
        tail = null;
        size = 0;
    }

    public Object clone() throws CloneNotSupportedException {

        @SuppressWarnings("unchecked")
        MySLinkedList<? super E> cloned = (MySLinkedList<? super E>) super.clone();

        cloned.head = null;
        cloned.tail = null;
        cloned.size = 0;

        for (MyNode<E> node = head; node != null; node = node.next) {
            cloned.addLast(node.data);
        }

        return cloned;
    }
}
