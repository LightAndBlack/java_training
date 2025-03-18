package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    private MyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        // Инициализируем пустой список перед каждым тестом
        list = new MyLinkedList<>();
    }

    @Test
    void testAdd() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void testRemove() {
        list.add(10);
        list.add(20);
        list.add(30);

        list.remove(1); // Удаляем элемент по индексу 1 (20)
        assertEquals(10, list.get(0));
        assertEquals(30, list.get(1));

        // Проверяем удаление первого элемента
        list.remove(0);
        assertEquals(30, list.get(0));

        // Удаляем оставшийся элемент
        list.remove(0);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testGet() {
        list.add(10);
        list.add(20);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    }

    @Test
    void testClear() {
        list.add(10);
        list.add(20);
        list.clear();

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testPrintList() {
        list.add(10);
        list.add(20);
        list.add(30);

        // Печатаем список (вывод в консоль)
        list.printList();
    }

    @Test
    void testQuickSort() {
        list.add(40);
        list.add(10);
        list.add(30);
        list.add(20);

        list.quickSort(Comparator.naturalOrder());

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
        assertEquals(40, list.get(3));
    }

    @Test
    void testQuickSortDescending() {
        list.add(40);
        list.add(10);
        list.add(30);
        list.add(20);

        // Сортируем по убыванию
        list.quickSort(Comparator.reverseOrder());

        assertEquals(40, list.get(0));
        assertEquals(30, list.get(1));
        assertEquals(20, list.get(2));
        assertEquals(10, list.get(3));
    }
}