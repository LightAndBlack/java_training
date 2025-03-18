package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    private MyArrayList<Integer> list;

    @BeforeEach
    void setUp() {
        // Создаём новый экземпляр MyArrayList перед каждым тестом
        list = new MyArrayList<>();
    }

    @Test
    void testAdd() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    void testAddByIndex() {
        list.add(10);
        list.add(20);

        list.add(1, 15); // Вставляем элемент 15 на позицию 1

        assertEquals(10, list.get(0));
        assertEquals(15, list.get(1));
        assertEquals(20, list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    void testRemove() {
        list.add(10);
        list.add(20);
        list.add(30);

        Integer removed = list.remove(1); // Удаляем элемент на индексе 1 (20)

        assertEquals(20, removed);
        assertEquals(10, list.get(0));
        assertEquals(30, list.get(1));
        assertEquals(2, list.size());

        // Проверяем удаление первого элемента
        list.remove(0);
        assertEquals(30, list.get(0));

        // Проверяем удаление последнего элемента
        list.remove(0);
        assertEquals(0, list.size());
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
    void testSize() {
        assertEquals(0, list.size());
        list.add(10);
        list.add(20);
        assertEquals(2, list.size());
    }

    @Test
    void testClear() {
        list.add(10);
        list.add(20);
        list.clear();

        assertEquals(0, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testMergeSortAscending() {
        list.add(40);
        list.add(10);
        list.add(30);
        list.add(20);

        list.mergeSort(Comparator.naturalOrder()); // Сортируем по возрастанию

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
        assertEquals(40, list.get(3));
    }

    @Test
    void testMergeSortDescending() {
        list.add(40);
        list.add(10);
        list.add(30);
        list.add(20);

        list.mergeSort(Comparator.reverseOrder()); // Сортируем по убыванию

        assertEquals(40, list.get(0));
        assertEquals(30, list.get(1));
        assertEquals(20, list.get(2));
        assertEquals(10, list.get(3));
    }

    @Test
    void testAddAndSort() {
        list.add(50);
        list.add(30);
        list.add(40);
        list.addSorted(35, Comparator.naturalOrder()); // Сортировка по возрастанию
    }
}