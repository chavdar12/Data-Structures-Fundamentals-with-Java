package bg.softuni.implementations;

import bg.softuni.interfaces.Deque;

import java.util.Iterator;
import java.util.Objects;

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
        E[] newElements = (E[]) new Object[Math.max(INITIAL_CAPACITY, this.elements.length * GROWTH_FACTOR)];
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
        this.addLast(this.get(this.size - 1));
        for (int i = this.size - 2; i > index; i--) {
            this.set(i, this.get(i - 1));
        }
        this.set(index, element);
    }

    private int physicalIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + this.size);
        }
        return (this.head + index) % this.elements.length;
    }

    private void ensureIndex(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + this.size);
        }
    }

    @Override
    public void set(int index, E element) {
        this.elements[this.physicalIndex(index)] = element;
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
        return this.elements[this.physicalIndex(index)];
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
            if (Objects.equals(this.elements[currentIndex], object)) {
                return i;
            }

            currentIndex = this.getNextIndex(currentIndex);
        }

        return -1;
    }

    @Override
    public E remove(int index) {
        this.physicalIndex(index);
        if (index == 0) {
            return this.poll();
        } else if (index == this.size - 1) {
            return this.removeLast();
        } else {
            return this.removeAtMiddle(index);
        }
    }

    private E removeAtMiddle(int index) {
        E element = this.get(index);
        for (int i = index; i < this.size - 1; i++) {
            this.set(i, this.get(i + 1));
        }
        this.removeLast();
        return element;
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
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }
}
