package bg.softuni.implementations;

import bg.softuni.interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {

    private final List<E> queue;

    public PriorityQueue() {
        this.queue = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.queue.size();
    }

    @Override
    public void add(E element) {
        this.queue.add(element);
        heapifyUp(this.size() - 1);
    }

    private void heapifyUp(int i) {
        while (i > 0 && isLess(i, getParentIndex(i))) {
            Collections.swap(this.queue, i, getParentIndex(i));
            i = getParentIndex(i);
        }
    }

    private boolean isLess(int i, int parentIndex) {
        return this.queue.get(i).compareTo(this.queue.get(parentIndex)) < 0;
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    @Override
    public E peek() {
        if (this.size() == 0) {
            throw new IllegalStateException("Empty queue!");
        }
        return this.queue.get(0);
    }

    @Override
    public E poll() {
        if (this.size() == 0) {
            throw new IllegalStateException("Empty queue!");
        }
        E element = this.queue.get(0);
        Collections.swap(this.queue, 0, this.size() - 1);
        this.queue.remove(this.size() - 1);
        heapifyDown(0);
        return element;
    }

    private void heapifyDown(int i) {
        while (i < this.size() / 2) {
            int childIndex = getLeftChildIndex(i);
            if (childIndex < this.size() - 1 && isLess(childIndex + 1, childIndex)) {
                childIndex++;
            }
            if (isLess(i, childIndex)) {
                break;
            }
            Collections.swap(this.queue, i, childIndex);
            i = childIndex;
        }
    }

    private int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }
}
