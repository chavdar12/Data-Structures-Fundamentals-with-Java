package implementations;

import java.util.Iterator;

public class ReversedList<E> {
    private final static int INITIAL_CAPACITY = 2;

    private Object[] items;
    private int size;

    public ReversedList() {
        this.items = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public void add(E element) {
        ensureCapacity();
        items[size++] = element;
    }

    private void ensureCapacity() {
        if (size == items.length) {
            Object[] arr = new Object[items.length * 2];

            System.arraycopy(items, 0, arr, 0, items.length);

            items = arr;
        }
    }

    public E get(int index) {
        return (E) items[calcIndex(index)];
    }

    public E removeAt(int index) {
        E item = get(index);
        shiftLeft(calcIndex(index));
        return item;
    }

    private void shiftLeft(int index) {
        if (size - 1 - index >= 0)
            System.arraycopy(items, index + 1, items, index, size - 1 - index);

        items[--size] = null;
    }


    public int size() {
        return size;
    }

    public int capacity() {
        return items.length;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {

                return index < size();
            }

            @Override
            public E next() {

                return get(index++);
            }
        };
    }

    private int calcIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        return size - 1 - index;
    }
}
