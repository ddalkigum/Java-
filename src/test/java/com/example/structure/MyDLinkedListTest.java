package com.example.structure;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MyDLinkedListTest {

    public MyDLinkedList<Integer> dLinkedList;

    @BeforeEach
    public void beforeEach() {
        dLinkedList = new MyDLinkedList<Integer>();
        dLinkedList.addFirst(1);
        dLinkedList.addFirst(2);
    }

    @Test
    @DisplayName("head 와 tail 정상적으로 변경되는지 테스트, size 테스트")
    void addFirst() {
        MyDNode<Integer> head = dLinkedList.getHead();
        MyDNode<Integer> tail = dLinkedList.getTail();

        assertThat(dLinkedList.size()).isEqualTo(2);
        assertThat(head.data).isEqualTo(2);
        assertThat(tail.data).isEqualTo(1);
    }

    @Test
    @DisplayName("값만 추가할 경우 tail 정상적으로 변경되는지 테스트, size 테스트")
    void add() {
        dLinkedList.add(3);

        MyDNode<Integer> tail = dLinkedList.getTail();

        assertThat(tail.data).isEqualTo(3);
        assertThat(dLinkedList.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("삽입 추가하는 경우, head 정삭적으로 변경되는지 테스트, size 테스트")
    void addWithHeadIndex() {
        dLinkedList.add(0, 3);
        MyDNode<Integer> head = dLinkedList.getHead();

        assertThat(head.data).isEqualTo(3);
        assertThat(dLinkedList.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("삽입 추가하는 경우, tail 정상적으로 변경되는지 테스트, size 테스트")
    void addWithTailIndex() {
        dLinkedList.add(dLinkedList.size(), 3);
        MyDNode<Integer> tail = dLinkedList.getTail();

        assertThat(tail.data).isEqualTo(3);
        assertThat(dLinkedList.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Argument 없이 삭제 메서드 호출시 head 삭제 테스트")
    void removeWithoutArgument() {
        dLinkedList.remove();
        MyDNode<Integer> head = dLinkedList.getHead();

        assertThat(head.data).isEqualTo(1);
    }

    @Test
    @DisplayName("가장 마지막 노드 삭제시 tail 정상적으로 변경되는지 테스트")
    void removeWithTailIndex() {
        dLinkedList.remove(dLinkedList.size() - 1);
        MyDNode<Integer> tail = dLinkedList.getTail();

        assertThat(tail.data).isEqualTo(2);
    }

    @Test
    @DisplayName("처음 노드 삭제시 head 정상적으로 변경되는지 테스트")
    void removeWithHeadIndex() {
        dLinkedList.remove(0);
        MyDNode<Integer> head = dLinkedList.getHead();

        assertThat(head.data).isEqualTo(1);
    }

    @Test
    @DisplayName("없는 요소 삭제시 false 를 반환")
    void removeWithUnExistValue() {
        boolean isRemoved = dLinkedList.remove((Integer) 3);

        assertThat(isRemoved).isFalse();
    }

    @Test
    @DisplayName("정상적으로 삭제된 경우 true 를 반환")
    void removeWithExistValue() {
        boolean isRemoved = dLinkedList.remove((Integer) 1);

        assertThat(isRemoved).isTrue();
    }

    @Test
    @DisplayName("정상적으로 값이 교체되는지 확인")
    void set() {
        dLinkedList.set(0, 50);

        MyDNode<Integer> head = dLinkedList.getHead();

        assertThat(head.data).isEqualTo(50);
    }

    @Test
    void contains() {
        boolean isContained = dLinkedList.contains(1);

        assertThat(isContained).isTrue();

        isContained = dLinkedList.contains(50);

        assertThat(isContained).isFalse();
    }

    @Test
    @DisplayName("head 부터 탐색, 가장 먼저 발견한 index 를 리턴")
    void indexOf() {
        dLinkedList.addFirst(1);
        dLinkedList.addFirst(1);
        int index = dLinkedList.indexOf(1);

        assertThat(index).isEqualTo(0);
    }

    @Test
    @DisplayName("tail 부터 탐색, 가장 먼저 발견한 index 를 리턴")
    void lastIndexOf() {
        dLinkedList.addFirst(1);
        dLinkedList.addFirst(1);

        int lastIndex = dLinkedList.lastIndexOf(1);

        assertThat(lastIndex).isEqualTo(3);
    }

    @Test
    void size() {
        assertThat(dLinkedList.size()).isEqualTo(2);
    }

    @Test
    void isEmpty() {
        assertThat(dLinkedList.isEmpty()).isFalse();

        dLinkedList.remove();
        dLinkedList.remove();

        assertThat(dLinkedList.isEmpty()).isTrue();
    }

    @Test
    void clear() {
        dLinkedList.clear();

        MyDNode<Integer> head = dLinkedList.getHead();
        MyDNode<Integer> tail = dLinkedList.getTail();

        assertThat(head).isNull();
        assertThat(tail).isNull();
    }
}