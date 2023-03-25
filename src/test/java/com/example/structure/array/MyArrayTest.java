package com.example.structure.array;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MyArrayTest {

    public MyArray arr;

    @BeforeEach
    void beforeEach() {
        this.arr = new MyArray(10);
    }

    @Test
    @DisplayName("성공")
    void addNewElement() {
        arr.add(1);
        assertThat(arr.getValue(0)).isEqualTo(1);
    }

    @Test
    @DisplayName("사이즈 이상으로 추가 한 경우, OutOfMemoryError")
    void throwOutOfMemoryError() {
        assertThrows(OutOfMemoryError.class, () -> {
            for (int i = 0; i < 11; i++) {
                arr.add(1);
            }
        });
    }

    @Test
    @DisplayName("성공")
    void insert() {
        arr.add(1);
        arr.add(2);

        arr.insert(1, 2);
        assertThat(arr.getValue(1)).isEqualTo(2);
    }

    @Test
    @DisplayName("Array의 사이즈 이상으로 입력시 에러")
    void throwErrorWhenInsertOverSize() {
        assertThrows(Error.class, () -> arr.insert(11, 2));
    }

    @Test
    @DisplayName("현재 추가된 요소들의 인덱스 이상으로 넣을 경우 에러")
    void throwErrorWhenInsertOverCount() {
        assertThrows(Error.class, () -> arr.insert(1, 1));
    }

    @Test
    @DisplayName("성공")
    void remove() {
        arr.add(1);
        arr.add(2);
        arr.remove(1);
        int currentArrSize = arr.getSize();

        assertThat(currentArrSize).isEqualTo(1);
    }

    @Test
    @DisplayName("빈 배열일 경우 에러")
    void throwErrorWhenRemoveEmptyArray() {
        assertThrows(Error.class, () -> arr.remove(1));
    }

    @Test
    void getSize() {
        arr.add(1);
        assertThat(arr.getSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("빈 배열인 경우 true")
    void emptyArray() {
        boolean isEmptyArr = arr.isEmpty();
        assertThat(isEmptyArr).isEqualTo(true);
    }

    @Test
    @DisplayName("빈 배열 아닐 경우 false")
    void notEmptyArray() {
        arr.add(1);
        boolean isEmptyArr = arr.isEmpty();
        assertThat(isEmptyArr).isEqualTo(false);
    }

    @Test
    @DisplayName("성공")
    void getValue() {
        arr.add(1);
        int value = arr.getValue(0);

        assertThat(value).isEqualTo(1);
    }

    @Test
    void removeAll() {
        arr.add(1);
        arr.add(2);
        arr.add(3);

        arr.removeAll();

        assertThat(arr.getSize()).isEqualTo(0);
    }
}