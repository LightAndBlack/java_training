package org.example;

import java.util.Comparator;

/**
 * Класс MyLinkedList представляет собой реализацию двусвязного списка.
 * Каждый узел содержит ссылки на предыдущий и следующий узлы,
 * а также хранит данные указанного типа.
 *
 * Поддерживаются операции добавления, удаления, получения элементов,
 * очистки списка и быстрой сортировки.
 *
 * @param <T>  - обобщенный тип элементов(Generics), которые хранятся в списке.
 * Тип <T> будет заменён конкретным типом во время создания объекта пользовательского класса.
 */
public class MyLinkedList<T> {
    private Node<T> first; // Ссылка на первый узел списка
    private Node<T> last;  // Ссылка на последний узел списка

    /**
     * Вложенный класс Node представляет собой элемент (узел) двусвязного списка, содержит ссылки на предыдущий и
     * следующий узел списка.
     */
    private static class Node<T> {
        T data;       // Данные узла (любого типа, определённого пользователем)
        Node<T> next; // Ссылка на следующий узел
        Node<T> prev; // Ссылка на предыдущий узел

        /**
         * Конструктор узла.
         *
         * @param data Данные, которые будет хранить узел.
         */
        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element Элемент для добавления.
     */
    public void add(T element) {
        Node<T> newNode = new Node<>(element); // Создается новый объект класса Node c передачей элемента через конструктор
        if (first == null) { // Если список пуст
            first = last = newNode;
        } else { // Добавление в конец списка
            last.next = newNode; // У текущего последнего узла обновляется ссылка на следующий узел (с новым элементом)
            newNode.prev = last; // У нового узла ссылка на предыдущий узел устанавливается на текущий последний узел
            last = newNode; // Ссылка на последний текущий узел обновляется на узел с новым элементом
        }
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index Индекс элемента для удаления.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка.
     */
    public void remove(int index) {
        if (first == null || index < 0) { // Список пуст или индекс некорректен
            throw new IndexOutOfBoundsException("Индекс вне диапазона.");
        }

        if (index == 0) { // Удаление первого элемента
            first = first.next;
            if (first != null) {
                first.prev = null;
            } else { // Список стал пустым (ссылки на первый и последний узел указывают на пустые значения)
                last = null;
            }
            return;
        }

        Node<T> current = first; // Инициализация текущего узла как первого элемента списка
        int count = 0; // Счётчик для отслеживания текущей позиции

        // Поиск узла с заданным индексом
        while (current != null && count < index) {
            current = current.next; // Перемещаем указатель current на следующий узел списка
            count++;
        }

        if (current == null) { // Индекс превышает размер списка
            throw new IndexOutOfBoundsException("Индекс вне диапазона.");
        }

        if (current.next != null) { // Если удаляемый узел не последний
            Node<T> nextNode = current.next; // Получаем узел, следующий за текущим
            nextNode.prev = current.prev;    // Устанавливаем для следующего узла ссылку предыдущего узла на узел перед удаляемым
        } else { // Если удаляем последний элемент
            last = current.prev;
        }

        if (current.prev != null) { // Проверяем, существует ли предыдущий узел для текущего (удаляемого)
            Node<T> prevNode = current.prev; // Создаем ссылку на узел перед удаляемым
            prevNode.next = current.next;    // Связываем предыдущий узел с узлом после удаляемого
        }
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index Индекс элемента.
     * @return Элемент по указанному индексу.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка.
     */
    public T get(int index) {
        if (first == null || index < 0) { // Список пуст или индекс некорректен
            throw new IndexOutOfBoundsException("Индекс вне диапазона.");
        }

        Node<T> current = first;
        int count = 0;

        // Поиск элемента с заданным индексом
        while (current != null) {
            if (count == index) {
                return current.data;
            }
            current = current.next;
            count++;
        }

        throw new IndexOutOfBoundsException("Индекс вне диапазона.");
    }

    /**
     * Печатает все элементы списка в консоль.
     * Формат вывода: элемент <-> элемент <-> null
     */
    public void printList() {
        Node<T> current = first;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    /**
     * Очищает список, удаляя все элементы.
     * Ссылки на узлы устанавливаются в null.
     */
    public void clear() {
        first = null;
        last = null;
    }

    /**
     * Выполняет быструю сортировку списка MyLinkedList.
     *
     * @param comparator Компаратор для сравнения элементов.
     */
    public void quickSort(Comparator<? super T> comparator) {
        if (first == null || first.next == null) {
            // Если список пуст или в нем только один элемент, сортировка не нужна
            return;
        }
        quickSortRecursive(first, last, comparator);
    }

    /**
     * Рекурсивно выполняет быструю сортировку подсписков.
     *
     * @param low Указатель на первый узел подсписка.
     * @param high Указатель на последний узел подсписка.
     * @param comparator Компаратор для сравнения элементов.
     */
    private void quickSortRecursive(Node<T> low, Node<T> high, Comparator<? super T> comparator) {
        if (low != null && high != null && low != high && low != high.next) {
            // Разбиваем список и получаем узел-разделитель (pivot)
            Node<T> pivot = partition(low, high, comparator);

            // Рекурсивно сортируем левую и правую части
            quickSortRecursive(low, pivot.prev, comparator);
            quickSortRecursive(pivot.next, high, comparator);
        }
    }

    /**
     * Выполняет разбиение списка относительно опорного элемента (pivot).
     *
     * @param low Указатель на первый узел подсписка.
     * @param high Указатель на последний узел подсписка.
     * @param comparator Компаратор для сравнения элементов.
     * @return Узел, который стал опорным элементом после разбиения.
     */
    private Node<T> partition(Node<T> low, Node<T> high, Comparator<? super T> comparator) {
        T pivotData = high.data; // Выбираем последний элемент как pivot
        Node<T> i = low.prev;    // Указатель на начальный элемент меньшей части

        for (Node<T> j = low; j != high; j = j.next) {
            if (comparator.compare(j.data, pivotData) <= 0) {
                // Перемещение текущего элемента в "меньшую" часть
                if (i == null) {
                    i = low; // Если i ещё не инициализирован, присваиваем ему значение low
                }
                else {
                    i = i.next; // Иначе переходим к следующему узлу
                }
                swap(i, j);
            }
        }

        // Финальная корректировка указателя позиции i (если все элементы больше pivot)
        if (i == null) {
            i = low; // Если указатель i ещё не установлен, инициализируем его начальным узлом low
        } else {
            i = i.next; // Если указатель уже установлен, перемещаем его к следующему узлу
        }

        swap(i, high);
        return i;
    }

    /**
     * Меняет местами данные двух узлов.
     *
     * @param node1 Первый узел.
     * @param node2 Второй узел.
     */
    private void swap(Node<T> node1, Node<T> node2) {
        T temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }
}