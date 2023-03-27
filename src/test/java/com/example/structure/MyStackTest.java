package com.example.structure;

import com.example.structure.MyStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    public MyStack stack;
    public int STACK_SIZE = 10;

    @BeforeEach()
    void beforeEach() {
        stack = new MyStack(STACK_SIZE);
    }

    @Test
    @DisplayName("빈 스택일 경우 true 반환")
    void isEmpty() {
        boolean empty = stack.isEmpty();
        assertThat(empty).isEqualTo(true);
    }

    @Test
    @DisplayName("밴 스택이 아닐 경우 false 반환")
    void notEmpty() {
        stack.push(1);
        boolean empty = stack.isEmpty();
        assertThat(empty).isEqualTo(false);
    }

    @Test
    @DisplayName("peek()을 이용하여 마지막 값 가지고 오기, 배열의 길이는 변하지 않음")
    void getLastItemUsingPeek() {
        stack.push(1);
        stack.push(2);

        int lastItem = stack.peek();
        assertThat(lastItem).isEqualTo(2);
        assertThat(stack.getTop()).isEqualTo(1);
    }

    @Test
    @DisplayName("pop()을 이용하여 마지막 값 찾아오기")
    void pop() {
        stack.push(1);
        stack.push(2);

        int lastItem = stack.pop();

        assertThat(lastItem).isEqualTo(2);
        assertThat(stack.getTop()).isEqualTo(0);
    }

    @Test
    @DisplayName("비어있는 스택에서 pop()을 사용하는 경우, ArrayIndexOutOfBoundsException")
    void popWhenEmpty() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> stack.pop());
    }

    @Test
    @DisplayName("push() 성공, 스택의 길이 체크")
    void push() {
        stack.push(1);
        int top = stack.getTop();

        assertThat(top).isEqualTo(0);
    }

    @Test
    @DisplayName("스택의 최대 사이즈 이상으로 push() 할 경우, ArrayIndexOutOfBoundsException")
    void pushOverFlow() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            for (int i = 0; i < 11; i++) {
                stack.push(i + 1);
            }
        });
    }

    @Test
    @DisplayName("search()로 특정 아이템 인덱스 찾기")
    void search() {
        stack.push(1);
        stack.push(2);

        assertThat(stack.search(1)).isEqualTo(0);
        assertThat(stack.search(2)).isEqualTo(1);
    }

    @Test
    @DisplayName("search()로 찾는 경우, 특정 값이 없는 경우 -1을 리턴")
    void searchNotFound() {
        stack.push(1);
        stack.push(2);

        assertThat(stack.search(3)).isEqualTo(-1);
    }

    @Test
    @DisplayName("search()는 같은 값중에 가장 마지막 인덱스를 가지고 와야 함")
    void searchLastIndex() {
        stack.push(1);
        stack.push(1);
        stack.push(1);

        assertThat(stack.search(1)).isEqualTo(2);
    }
}