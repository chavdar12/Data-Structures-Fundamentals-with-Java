package bg.softuni.implementations;

import bg.softuni.interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {

    private static final int INITIAL_CAPACITY = 4;
    private E[] data;
    private int size;

    public Stack() {
        this.data = (E[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    @Override
    public void push(E element) {
        if (this.size == this.data.length) {
            this.data = resize();
        }

        this.data[this.size] = element;
        this.size++;
    }

    private E[] resize() {
        E[] newData = (E[]) new Object[this.data.length * 2];
        System.arraycopy(this.data, 0, newData, 0, this.data.length);

        return newData;
    }

    @Override
    public E pop() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Empty Stack");
        }

        E element = this.data[this.size - 1];
        this.data[this.size - 1] = null;
        this.size--;

        return element;
    }

    @Override
    public E peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Empty Stack");
        }

        return this.data[this.size - 1];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = size - 1;

            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public E next() {
                return data[index--];
            }
        };
    }
}
