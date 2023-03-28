package com.example.structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MySLinkedListTest {

    public MySLinkedList<String> myLinkedList;
    public String testWord1 = "test1";
    public String testWord2 = "test2";
    public String testWord3 = "test3";
    public String testWord4 = "test4";

    @BeforeEach
    void beforeEach() {
        myLinkedList = new MySLinkedList<String>();

        myLinkedList.add(testWord1);
        myLinkedList.add(testWord2);
        myLinkedList.add(testWord3);
        myLinkedList.add(testWord4);
    }

    @Test
    void search() {
        MyNode<String> foundNode = myLinkedList.search(0);
        assertThat(foundNode.data).isEqualTo(testWord1);

        MyNode<String> foundNode2 = myLinkedList.search(1);
        assertThat(foundNode2.data).isEqualTo(testWord2);
    }

    @Test
    void addFirst() {
        myLinkedList.addFirst(testWord2);

        MyNode<String> foundFirstNode = myLinkedList.search(0);
        assertThat(foundFirstNode.data).isEqualTo(testWord2);
    }

    @Test
    void addLast() {
        myLinkedList.addLast(testWord2);

        MyNode<String> foundFirstNode = myLinkedList.search(myLinkedList.size() - 1);
        assertThat(foundFirstNode.data).isEqualTo(testWord2);
    }

    @Test
    @DisplayName("add() index 와 value 를 지정하여 중간에 삽입")
    void add() {
        myLinkedList.add(2, testWord1);
        MyNode<String> foundNode = myLinkedList.search(2);

        assertThat(foundNode.data).isEqualTo(testWord1);
    }

    @Test
    @DisplayName("argument 없이 remove() 메서드를 호출할 경우 가장 앞에서부터 삭제")
    void removeWithoutArgument() {
        String removedValue = myLinkedList.remove();
        MyNode<String> foundNode = myLinkedList.search(0);

        assertThat(removedValue).isEqualTo(testWord1);
        assertThat(foundNode.data).isEqualTo(testWord2);
    }

    @Test
    @DisplayName("index 를 이용한 삭제")
    void removeUsingIndex() {
        myLinkedList.remove(myLinkedList.size() - 1);

        String foundValue = myLinkedList.get(myLinkedList.size() - 1);
        assertThat(foundValue).isEqualTo(testWord3);
    }

    @Test
    @DisplayName("value 를 이용한 삭제, 삭제는 가장 앞에서 부터 진행")
    void removeUsingValue() {
        myLinkedList.addLast(testWord1);
        myLinkedList.remove(testWord1);

        String firstNodeValue = myLinkedList.get(0);
        assertThat(firstNodeValue).isEqualTo(testWord2);

        int foundIndex = myLinkedList.indexOf(testWord1);
        assertThat(foundIndex).isEqualTo(myLinkedList.size() - 1);
    }

    @Test
    void set() {
        myLinkedList.set(1, testWord4);

        int foundIndex = myLinkedList.indexOf(testWord4);
        assertThat(foundIndex).isEqualTo(1);
    }

    @Test
    void contains() {
        boolean isContain = myLinkedList.contains(testWord1);
        assertThat(isContain).isTrue();

        isContain = myLinkedList.contains("random");
        assertThat(isContain).isFalse();
    }

    @Test
    void indexOf() {
        int foundIndex = myLinkedList.indexOf(testWord1);
        assertThat(foundIndex).isEqualTo(0);
    }

    @Test
    void size() {
        int listSize = myLinkedList.size();
        assertThat(listSize).isEqualTo(4);
    }

    @Test
    void isEmpty() {
        boolean isEmpty = myLinkedList.isEmpty();
        assertThat(isEmpty).isFalse();
    }

    @Test
    void clear() {
        myLinkedList.clear();
        int size = myLinkedList.size();

        assertThat(size).isEqualTo(0);
    }

    @Test
    void testClone() throws CloneNotSupportedException {
        Object cloned = myLinkedList.clone();
        int originCode = System.identityHashCode(myLinkedList);
        int code = System.identityHashCode(cloned);

        assertThat(originCode).isNotEqualTo(code);
    }
}