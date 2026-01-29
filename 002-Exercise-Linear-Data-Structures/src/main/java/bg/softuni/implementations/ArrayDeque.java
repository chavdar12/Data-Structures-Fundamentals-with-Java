package bg.softuni.implementations;

import bg.softuni.interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {

    private static final int INITIAL_CAPACITY = 4;
    private static final int GROWTH_FACTOR = 2;

    private E[] elements;
    private int size;
    private int head;
    private int tail;

    public ArrayDeque() {
        this.elements = (E[]) new Object[INITIAL_CAPACITY];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    @Override
    public void add(E element) {
        this.addLast(element);
    }

    @Override
    public void offer(E element) {
        this.addLast(element);
    }

    @Override
    public void addFirst(E element) {
        this.ensureCapacity();
        this.head = this.getPreviousIndex(this.head);
        this.elements[this.head] = element;
        this.size++;
    }

    private int getPreviousIndex(int head) {
        return (head - 1 + this.elements.length) % this.elements.length;
    }

    private void ensureCapacity() {
        if (this.size == this.elements.length) {
            this.grow();
        }
    }

    private void grow() {
        E[] newElements = (E[]) new Object[this.elements.length * GROWTH_FACTOR];
        this.copyAllElements(newElements);
        this.elements = newElements;
        this.head = 0;
        this.tail = this.size;
    }

    private void copyAllElements(E[] newElements) {
        int sourceIndex = this.head;
        int destinationIndex = 0;
        for (int i = 0; i < this.size; i++) {
            newElements[destinationIndex] = this.elements[sourceIndex];
            sourceIndex = this.getNextIndex(sourceIndex);
            destinationIndex++;
        }
    }

    private int getNextIndex(int sourceIndex) {
        return (sourceIndex + 1) % this.elements.length;
    }

    @Override
    public void addLast(E element) {
        this.ensureCapacity();
        this.elements[this.tail] = element;
        this.tail = this.getNextIndex(this.tail);
        this.size++;
    }

    @Override
    public void push(E element) {
        this.addFirst(element);
    }

    @Override
    public void insert(int index, E element) {
        this.ensureIndex(index);
        if (index == 0) {
            this.addFirst(element);
        } else if (index == this.size) {
            this.addLast(element);
        } else {
            this.insertAtMiddle(index, element);
        }
    }

    private void insertAtMiddle(int index, E element) {
        int currentIndex = this.head;
        for (int i = 0; i < index; i++) {
            currentIndex = this.getNextIndex(currentIndex);
        }

        int previousIndex = this.getPreviousIndex(currentIndex);
        int nextIndex = this.getNextIndex(currentIndex);

        E previousElement = this.elements[previousIndex];
        E nextElement = this.elements[nextIndex];

        this.elements[previousIndex] = element;
        this.elements[currentIndex] = previousElement;
        this.elements[nextIndex] = nextElement;

        this.size++;
    }

    private void ensureIndex(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + this.size);
        }
    }

    @Override
    public void set(int index, E element) {
        this.ensureIndex(index);
        int currentIndex = this.head;
        for (int i = 0; i < index; i++) {
            currentIndex = this.getNextIndex(currentIndex);
        }

        this.elements[currentIndex] = element;
    }

    @Override
    public E peek() {
        this.ensureNotEmpty();
        return this.elements[this.head];
    }

    private void ensureNotEmpty() {
        if (this.size == 0) {
            throw new IllegalStateException("Deque is empty");
        }
    }

    @Override
    public E poll() {
        this.ensureNotEmpty();
        E element = this.elements[this.head];
        this.elements[this.head] = null;
        this.head = this.getNextIndex(this.head);
        this.size--;
        return element;
    }

    @Override
    public E pop() {
        return this.poll();
    }

    @Override
    public E get(int index) {
        this.ensureIndex(index);
        int currentIndex = this.head;
        for (int i = 0; i < index; i++) {
            currentIndex = this.getNextIndex(currentIndex);
        }

        return this.elements[currentIndex];
    }

    @Override
    public E get(Object object) {
        int index = this.indexOf(object);
        if (index == -1) {
            return null;
        }

        return this.get(index);
    }

    private int indexOf(Object object) {
        int currentIndex = this.head;
        for (int i = 0; i < this.size; i++) {
            if (this.elements[currentIndex].equals(object)) {
                return i;
            }

            currentIndex = this.getNextIndex(currentIndex);
        }

        return -1;
    }

    @Override
    public E remove(int index) {
        this.ensureIndex(index);
        if (index == 0) {
            return this.poll();
        } else if (index == this.size - 1) {
            return this.removeLast();
        } else {
            return this.removeAtMiddle(index);
        }
    }

    private E removeAtMiddle(int index) {
        int currentIndex = this.head;
        for (int i = 0; i < index; i++) {
            currentIndex = this.getNextIndex(currentIndex);
        }

        int previousIndex = this.getPreviousIndex(currentIndex);
        int nextIndex = this.getNextIndex(currentIndex);

        E previousElement = this.elements[previousIndex];
        E nextElement = this.elements[nextIndex];

        this.elements[previousIndex] = nextElement;
        this.elements[nextIndex] = previousElement;
        this.elements[currentIndex] = null;

        this.size--;

        return previousElement;
    }

    @Override
    public E remove(Object object) {
        int index = this.indexOf(object);
        if (index == -1) {
            return null;
        }

        return this.remove(index);
    }

    @Override
    public E removeFirst() {
        return this.poll();
    }

    @Override
    public E removeLast() {
        this.ensureNotEmpty();
        E element = this.elements[this.getPreviousIndex(this.tail)];
        this.elements[this.getPreviousIndex(this.tail)] = null;
        this.tail = this.getPreviousIndex(this.tail);
        this.size--;
        return element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public void trimToSize() {
        if (this.size == this.elements.length) {
            return;
        }

        E[] newElements = (E[]) new Object[this.size];
        this.copyAllElements(newElements);
        this.elements = newElements;
        this.head = 0;
        this.tail = this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int currentIndex = head;

            @Override
            public boolean hasNext() {
                return currentIndex != tail;
            }

            @Override
            public E next() {
                E element = elements[currentIndex];
                currentIndex = getNextIndex(currentIndex);
                return element;
            }
        };
    }
}
