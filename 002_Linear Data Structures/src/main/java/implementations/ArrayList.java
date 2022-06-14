package implementations;

import interfaces.List;

import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private E[] arr;
    private int size = 4;
    private int length;

    public ArrayList() {
        this.arr = (E[]) new Object[this.size];
        this.length = 0;
    }

    @Override
    public boolean add(E element) {
        if (length > this.arr.length - 1) {
            this.arr = resize();
        }
        this.arr[length] = element;
        this.length++;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (this.length <= this.arr.length) {

            if (this.length - index >= 0) {
                System.arraycopy(this.arr, index, this.arr, index + 1, this.length - index);
            }

            this.arr[index] = element;

        } else {
            var newArray = (E[]) new Object[this.length * 2];

            if (index >= 0) {
                System.arraycopy(this.arr, 0, newArray, 0, index);
            }

            newArray[index] = element;

            if (this.length - (index + 1) >= 0) {
                System.arraycopy(this.arr, index + 1 - 1, newArray, index + 1, this.length - (index + 1));
            }

            this.arr = newArray;
        }
        this.length++;

        return true;
    }

    @Override
    public E get(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        return this.arr[index];
    }

    @Override
    public E set(int index, E element) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        E prevValue = this.arr[index];
        this.arr[index] = element;

        return prevValue;
    }

    @Override
    public E remove(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        var element = this.arr[index];

        if (this.size() - index >= 0) System.arraycopy(this.arr, index + 1, this.arr, index, this.size() - index);

        this.arr[this.size()] = null;

        if (this.length == this.arr.length / 2) {
            this.arr = shrink();
        }

        this.length--;

        return element;
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public int indexOf(E element) {
        int index = -1;

        for (int i = 0; i < this.arr.length; i++) {
            if (element.equals(this.arr[i])) {
                index = i;
                break;
            }
        }

        return index;
    }

    @Override
    public boolean contains(E element) {
        var isContains = false;

        for (int i = 0; i < this.arr.length; i++) {
            if (this.indexOf(element) != -1) {
                isContains = true;
                break;
            }
        }

        return isContains;
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private E[] shrink() {
        var newArray = (E[]) new Object[Math.max(this.arr.length / 2, this.size)];

        if (this.size() >= 0) System.arraycopy(this.arr, 0, newArray, 0, this.size());

        return newArray;
    }

    private E[] resize() {
        var newArray = (E[]) new Object[this.length * 2];

        if (this.length >= 0) System.arraycopy(this.arr, 0, newArray, 0, this.length);

        return newArray;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index <= this.size() - 1;
    }

    private final class ListIterator implements Iterator<E> {
        private int counter;

        public ListIterator() {
            this.counter = 0;
        }

        @Override
        public boolean hasNext() {
            return this.counter <= size() - 1;
        }

        @Override
        public E next() {
            return get(this.counter++);
        }
    }
}
