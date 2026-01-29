package bg.softuni.solutions;

import bg.softuni.interfaces.Decrease;
import bg.softuni.interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {

    private List<E> elements;

    public MinHeap() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifyUp(this.size() - 1);
    }

    private void heapifyUp(int i) {
        if (i == 0) {
            return;
        }

        int parentIndex = (i - 1) / 2;
        E parent = this.elements.get(parentIndex);
        E current = this.elements.get(i);

        if (parent.compareTo(current) > 0) {
            Collections.swap(this.elements, parentIndex, i);
            this.heapifyUp(parentIndex);
        }
    }

    @Override
    public E peek() {
        if (this.size() == 0) {
            throw new IllegalStateException();
        }

        return this.elements.get(0);
    }

    @Override
    public E poll() {
        if (this.size() == 0) {
            throw new IllegalStateException();
        }

        E element = this.elements.get(0);
        Collections.swap(this.elements, 0, this.size() - 1);
        this.elements.remove(this.size() - 1);
        this.heapifyDown(0);

        return element;
    }

    private void heapifyDown(int i) {
        int leftChildIndex = 2 * i + 1;
        int rightChildIndex = 2 * i + 2;

        if (leftChildIndex >= this.size()) {
            return;
        }

        int minIndex = leftChildIndex;
        if (rightChildIndex < this.size() && this.elements.get(rightChildIndex).compareTo(this.elements.get(leftChildIndex)) < 0) {
            minIndex = rightChildIndex;
        }

        if (this.elements.get(i).compareTo(this.elements.get(minIndex)) > 0) {
            Collections.swap(this.elements, i, minIndex);
            this.heapifyDown(minIndex);
        }
    }

    @Override
    public void decrease(E element) {
        int index = this.elements.indexOf(element);
        if (index == -1) {
            throw new IllegalArgumentException();
        }

        this.elements.get(index).decrease();
        this.heapifyUp(index);
    }
}
