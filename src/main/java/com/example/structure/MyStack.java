package com.example.structure;

import java.util.EmptyStackException;

public class MyStack {

    public int getTop() {
        return top;
    }

    public int getStackSize() {
        return stackSize;
    }

    public int[] getStack() {
        return stack;
    }

    private int top;
    private int stackSize;
    private int[] stack;

    public MyStack(int stackSize) {
        top = -1;
        this.stackSize = stackSize;
        stack = new int[stackSize];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int peek() {
        if (top == -1) {
            throw new EmptyStackException();
        }

        return stack[top];
    }

    public int pop() {
        if (stackSize < 1 || top < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int item = peek();
        stack[top] = 0;
        top--;

        return item;
    }

    public int push(int item) {
        top++;

        if (top > stackSize - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        stack[top] = item;
        return item;
    }

    public int search(int item) {
        int found = lastIndexOf(item, top);
        return found;
    }

    private int lastIndexOf(int item, int currentIndex) {
        if (currentIndex == -1) return -1;
        if (stack[currentIndex] == item) return currentIndex;

        int nextIndex = currentIndex - 1;
        return lastIndexOf(item, nextIndex);
    }
}
