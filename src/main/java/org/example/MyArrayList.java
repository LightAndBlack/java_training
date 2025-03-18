package org.example;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Класс MyArrayList представляет собой динамический массив, который может хранить элементы любого типа.
 * Поддерживает операции добавления, удаления, получения элементов, а также сортировку.
 *
 * @param <T> Тип элементов, хранимых в списке.
 */
public class MyArrayList<T> {
    Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Создает новый MyArrayList с начальной емкостью по умолчанию.
     */
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Создает новый MyArrayList с указанной начальной емкостью.
     *
     * @param initialCapacity Начальная емкость списка.
     * @throws IllegalArgumentException если начальная емкость меньше нуля.
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            elements = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Размер массива не может быть меньше 0");
        }
    }

    /**
     * Возвращает массив элементов списка.
     *
     * @return Массив элементов.
     */
    public Object[] getElements() {
        return elements;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element Элемент для добавления.
     */
    public void add(T element) {
        adjustCapacity(size + 1);
        elements[size++] = element;
    }

    /**
     * Регулирует емкость массива для хранения элементов.
     *
     * @param minCapacity Минимальная требуемая емкость.
     */
    private void adjustCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(DEFAULT_CAPACITY, elements.length * 2);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Возвращает размер списка.
     *
     * @return Размер списка.
     */
    public int size() {
        return size;
    }

    /**
     * Добавляет элемент по указанному индексу.
     *
     * @param index   Индекс для вставки элемента.
     * @param element Элемент для добавления.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка.
     */
    public void add(int index, T element) {
        checkIndexForAdd(index);
        adjustCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index Индекс элемента.
     * @return Элемент по указанному индексу.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка.
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    /**
     * Проверяет корректность указанного индекса.
     *
     * @param index Индекс для проверки.
     * @param isAdd Если true, проверка для метода добавления.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка.
     */
    private void checkIndex(int index, boolean isAdd) {
        if (index < 0 || index >= size + (isAdd ? 1 : 0)) {
            throw new IndexOutOfBoundsException("Индекс: " + index + " выходит за пределы массива (Размер: " + size + ")");
        }
    }

    private void checkIndex(int index) {
        checkIndex(index, false);
    }

    private void checkIndexForAdd(int index) {
        checkIndex(index, true);
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index Индекс элемента для удаления.
     * @return Удаленный элемент.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка.
     */
    public T remove(int index) {
        checkIndex(index);
        @SuppressWarnings("unchecked")
        T deletedElement = (T) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return deletedElement;
    }

    /**
     * Очищает список.
     */
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0; // Сбрасываем размер списка
    }

    /**
     * Реализует сортировку слиянием для MyArrayList.
     *
     * @param comparator Компаратор для сравнения элементов.
     */
    public void mergeSort(Comparator<? super T> comparator) {
        if (size < 2) {
            return; // Базовый случай: список уже отсортирован, если размер меньше 2
        }

        // Создаём временный массив для хранения элементов
        Object[] tempArray = Arrays.copyOf(elements, size);
        mergeSortRecursive(tempArray, 0, size - 1, comparator);
    }

    private void mergeSortRecursive(Object[] tempArray, int left, int right, Comparator<? super T> comparator) {
        if (left >= right) {
            return; // Базовый случай: один элемент
        }

        int middle = (left + right) / 2;

        // Рекурсивно сортируем левую и правую половины
        mergeSortRecursive(tempArray, left, middle, comparator);
        mergeSortRecursive(tempArray, middle + 1, right, comparator);

        // Сливаем две половины
        merge(tempArray, left, middle, right, comparator);
    }

    @SuppressWarnings("unchecked")
    private void merge(Object[] tempArray, int left, int middle, int right, Comparator<? super T> comparator) {
        int leftIndex = left;
        int rightIndex = middle + 1;
        int mergedIndex = left;

        // Временный массив для слияния
        Object[] merged = new Object[right - left + 1];

        int k = 0;
        // Слияние двух отсортированных частей
        while (leftIndex <= middle && rightIndex <= right) {
            if (comparator.compare((T) tempArray[leftIndex], (T) tempArray[rightIndex]) <= 0) {
                merged[k++] = tempArray[leftIndex++];
            } else {
                merged[k++] = tempArray[rightIndex++];
            }
        }

        // Копируем оставшиеся элементы с левой стороны
        while (leftIndex <= middle) {
            merged[k++] = tempArray[leftIndex++];
        }

        // Копируем оставшиеся элементы с правой стороны
        while (rightIndex <= right) {
            merged[k++] = tempArray[rightIndex++];
        }

        // Копируем обратно в основной массив
        System.arraycopy(merged, 0, tempArray, left, merged.length);

        // Обновляем основной массив
        System.arraycopy(tempArray, left, elements, left, merged.length);
    }

    /**
    * Добавление элемента после сортировки
     */
    public void addSorted(T element, Comparator<? super T> comparator) {
        // Если список пуст, добавляем элемент
        if (size == 0) {
            add(element);
            return;
        }

        // Найдём корректное место для вставки
        int index = 0;
        while (index < size && comparator.compare((T) elements[index], element) < 0) {
            index++;
        }

        // Вставляем элемент на корректную позицию
        add(index, element);
    }
}