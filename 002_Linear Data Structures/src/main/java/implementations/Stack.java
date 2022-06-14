package implementations;

import interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> previous;

        public Node(E value) {
            this.element = value;
        }
    }

    private Node<E> top;
    private int size;

    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element);

        newNode.previous = top;

        top = newNode;

        this.size++;
    }

    @Override
    public E pop() {
        ensureNonEmpty();
        E element = this.top.element;
        Node<E> temp = this.top.previous;
        this.top.previous = null;
        this.top = temp;
        this.size--;
        return element;
    }

    private boolean ensureNonEmpty() {
        return this.size > 0;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
