package com.example.structure.array;

public class MyArray {
    int[] intArr;
    int count;

    public int size;
    public static final int ERROR_NUM = -99;

    public MyArray() {
        count = 0;
        size = 10;
        intArr = new int[size];
    }

    public MyArray(int size) {
        count = 0;
        this.size = size;
        intArr = new int[size];
    }

    public int[] add(int num) throws OutOfMemoryError {
        if (count >= this.size) {
            throw new OutOfMemoryError();
        }

        intArr[count++] = num;
        return intArr;
    }

    public int[] insert(int position, int num) throws Error {
        // position이 현재 intArr의 길이보다 큰 경우
        if (position >= count) {
            throw new Error("Current count = " + count + ", position = " + position);
        }

        // position이 size보다 큰 경우
        if (position >= size) {
            throw new Error("Max size = " + size + ", position = " + position);
        }

        // 한칸씩 다 밀어줘야 함
        for (int i = 0; i < intArr.length; i++) {
            if (i == position) {
                intArr[i + 1] = intArr[i];
                intArr[i] = num;
            }
        }
        count++;
        return intArr;
    }

    public int[] remove(int position) {
        // position 음수인 경우, size 이상인 경우
        if (position < 0 || position >= size || position > count) {
            throw new Error("Invalid position");
        }

        // 한칸씩 다 땡겨줘야 함
        for (int i = 0; i < intArr.length - 1; i++) {
            if (i >= position) {
                intArr[i] = intArr[i + 1];
            }
        }
        count--;
        return intArr;
    }

    public int getSize() {
        return count;
    }

    public boolean isEmpty() {
        if (count == 0) return true;
        return false;
    }

    public int getValue(int position) {
        if (position < 0 || position > count || position > size) {
            throw new Error("Invalid position");
        }
        return intArr[position];
    }

    public int[] removeAll() {
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = 0;
        }
        count = 0;
        return intArr;
    }
}
