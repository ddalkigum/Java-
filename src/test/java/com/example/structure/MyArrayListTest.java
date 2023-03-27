package com.example.structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    public MyArrayList<String> arrList;

    public static final String testWord = "First";
    public static final String testWord2 = "Second";
    public static final String testWord3 = "Third";
    public static final String testWord4 = "Fourth";

    @BeforeEach
    void beforeEach() {
        arrList = new MyArrayList<>();
        arrList.add(testWord);
        arrList.add(testWord2);
    }

    @Test
    @DisplayName("새롭게 추가할 경우 뒤에서 부터 추가한다")
    void add() {
        assertThat(arrList.get(0)).isEqualTo(testWord);
        assertThat(arrList.get(1)).isEqualTo(testWord2);
    }

    @Test
    @DisplayName("뒤에서 차례대로 추가")
    void addLast() {
        assertThat(arrList.get(0)).isEqualTo(testWord);
        assertThat(arrList.get(1)).isEqualTo(testWord2);
    }

    @Test
    @DisplayName("index 를 이용하여 성공적으로 삭제, 사이즈가 올바른지 확인")
    void remove() {
        String removedElement = arrList.remove(0);

        assertThat(removedElement).isEqualTo(testWord);

        int arrSize = arrList.size();
        assertThat(arrSize).isEqualTo(1);
    }

    @Test
    @DisplayName("index 의 범위가 음수이거나, 사이즈보다 큰 경우 IndexOutOfBoundsException")
    void indexOutOfBoundRemove() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrList.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> arrList.remove(3));
    }

    @Test
    @DisplayName("index 에 해당하는 value 를 가지고 옴")
    void get() {
        String foundWord = arrList.get(0);
        assertThat(foundWord).isEqualTo(testWord);
    }

    @Test
    @DisplayName("index 의 범위가 음수이거나, 사이즈보터 큰 경우 IndexOutOfBoundsException")
    void indexOutOfBoundGet() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrList.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> arrList.get(3));
    }

    @Test
    @DisplayName("index 에 존재하는 값은 value 로 재정의한다")
    void set() {
        arrList.set(0, testWord3);
        String firstWord = arrList.get(0);

        assertThat(firstWord).isEqualTo(firstWord);
    }

    @Test
    @DisplayName("index 의 범위가 음수이거나, 사이즈보다 큰 경우 IndexOutOfBoundsException")
    void indexOutOfBoundSet() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrList.set(-1, testWord3));
        assertThrows(IndexOutOfBoundsException.class, () -> arrList.set(3, testWord3));
    }

    @Test
    @DisplayName("argument 로 받은 value 가 존재한다면 true, 존재하지 않는다면 false")
    void contains() {
        boolean isFoundTestWord = arrList.contains(testWord);
        assertThat(isFoundTestWord).isTrue();

        boolean isFoundTestWord3 = arrList.contains(testWord3);
        assertThat(isFoundTestWord3).isFalse();
    }

    @Test
    @DisplayName("value 에 해당하는 가장 첫번째 index 를 가지고 온다")
    void indexOf() {
        arrList.add(testWord);
        int foundIndex = arrList.indexOf(testWord);

        assertThat(foundIndex).isEqualTo(0);
    }

    @Test
    @DisplayName("value 에 해당하는 가장 마지막 index 를 가지고 온다")
    void lastIndexOf() {
        arrList.add(testWord);
        int foundIndex = arrList.lastIndexOf(testWord);

        assertThat(foundIndex).isEqualTo(2);
    }

    @Test
    @DisplayName("리스트의 사이즈를 가지고 옴")
    void size() {
        int arrSize = arrList.size();

        assertThat(arrSize).isEqualTo(2);
    }

    @Test
    @DisplayName("현재 리스트가 비어있는지 확인한다, 삭제 후 확인")
    void isEmpty() {
        boolean emptyList = arrList.isEmpty();

        assertThat(emptyList).isFalse();

        arrList.remove(1);
        arrList.remove(0);

        boolean nonEmptyList = arrList.isEmpty();
        assertThat(nonEmptyList).isTrue();
    }

    @Test
    @DisplayName("리스트의 모든 값을 없앤다")
    void clear() {
        arrList.clear();

        assertThat(arrList.size()).isEqualTo(0);
    }
}