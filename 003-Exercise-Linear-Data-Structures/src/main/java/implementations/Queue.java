package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        private static <T> Node<T> empty() {
            return new Node<>();
        }

        private static <T> Node<T> of(T element) {
            Node<T> node = Node.empty();
            node.element = element;
            return node;
        }

        private static <T> Node<T> of(T element, Node<T> current) {
            Node<T> node = Node.empty();
            node.element = element;
            node.next = current;
            return node;
        }
    }

    @Override
    public void offer(E element) {
        Node<E> newTail = Node.of(element);
        if (size == 0) {
            head = newTail;
        } else {
            tail.next = newTail;
        }
        tail = newTail;
        size += 1;
    }

    @Override
    public E poll() {
        ensureNotEmpty();
        E element = head.element;
        if (size == 1) {
            head = tail = null;
        } else {
            head = head.next;
        }
        size -= 1;
        return element;
    }

    @Override
    public E peek() {
        ensureNotEmpty();
        return head.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    private void ensureNotEmpty() {
        if (size == 0) {
            throw new IllegalStateException("The Queue is empty");
        }
    }
}
