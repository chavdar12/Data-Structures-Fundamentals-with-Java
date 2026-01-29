package bg.softuni.implementations;

import bg.softuni.interfaces.LinkedList;

import java.util.Iterator;

public class DoublyLinkedList<E> implements LinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    @Override
    public void addFirst(E element) {
        Node<E> newHead = Node.of(element);
        if (size == 0) {
            head = tail = newHead;
        } else {
            newHead.next = head;
            head.prev = newHead;
            head = newHead;
        }
        size += 1;
    }

    @Override
    public void addLast(E element) {
        Node<E> newTail = Node.of(element);
        if (size == 0) {
            head = tail = newTail;
        } else {
            tail.next = newTail;
            newTail.prev = tail;
            tail = newTail;
        }
        size += 1;
    }

    @Override
    public E removeFirst() {
        ensureNotEmpty();
        E element = head.element;
        if (size == 1) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size -= 1;
        return element;
    }

    private void ensureNotEmpty() {
        if (size == 0) {
            throw new IllegalStateException("List is empty!");
        }
    }

    @Override
    public E removeLast() {
        ensureNotEmpty();
        E element = tail.element;
        if (size == 1) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size -= 1;
        return element;
    }

    @Override
    public E getFirst() {
        ensureNotEmpty();
        return head.element;
    }

    @Override
    public E getLast() {
        ensureNotEmpty();
        return tail.element;
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

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        private Node(E element) {
            this.element = element;
        }

        private static <E> Node<E> of(E element) {
            return new Node<>(element);
        }
    }
}
