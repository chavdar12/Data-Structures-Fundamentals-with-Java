package bg.softuni;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinarySearchTree<E extends Comparable<E>> {

    private Node<E> root;

    public BinarySearchTree() {
    }

    private BinarySearchTree(Node<E> root) {
        this.root = root;
    }

    public static class Node<E> {
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;
        private int count;

        public Node(E value) {
            this.value = value;
            this.count = 1;
        }

        public Node<E> getLeft() {
            return this.leftChild;
        }

        public Node<E> getRight() {
            return this.rightChild;
        }

        public E getValue() {
            return this.value;
        }
    }

    public void eachInOrder(Consumer<E> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node<E> node, Consumer<E> consumer) {
        if (node == null) {
            return;
        }
        this.eachInOrder(node.leftChild, consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.rightChild, consumer);
    }

    public Node<E> getRoot() {
        return this.root;
    }

    public void insert(E element) {
        this.root = this.insert(this.root, element);
    }

    // duplicates are ignored so count() and rank() stay meaningful
    private Node<E> insert(Node<E> node, E element) {
        if (node == null) {
            return new Node<>(element);
        }

        int cmp = element.compareTo(node.value);
        if (cmp < 0) {
            node.leftChild = this.insert(node.leftChild, element);
        } else if (cmp > 0) {
            node.rightChild = this.insert(node.rightChild, element);
        }

        node.count = 1 + count(node.leftChild) + count(node.rightChild);
        return node;
    }

    public boolean contains(E element) {
        return this.find(element) != null;
    }

    public BinarySearchTree<E> search(E element) {
        return new BinarySearchTree<>(this.find(element));
    }

    private Node<E> find(E element) {
        Node<E> current = this.root;
        while (current != null) {
            int cmp = element.compareTo(current.value);
            if (cmp == 0) {
                return current;
            }
            current = cmp < 0 ? current.leftChild : current.rightChild;
        }
        return null;
    }

    public List<E> range(E first, E second) {
        List<E> result = new ArrayList<>();
        this.range(this.root, first, second, result);
        return result;
    }

    private void range(Node<E> node, E low, E high, List<E> result) {
        if (node == null) {
            return;
        }

        boolean aboveLow = low.compareTo(node.value) <= 0;
        boolean belowHigh = high.compareTo(node.value) >= 0;

        if (aboveLow) {
            this.range(node.leftChild, low, high, result);
        }
        if (aboveLow && belowHigh) {
            result.add(node.value);
        }
        if (belowHigh) {
            this.range(node.rightChild, low, high, result);
        }
    }

    public void deleteMin() {
        this.ensureNotEmpty();
        this.root = this.deleteMin(this.root);
    }

    private Node<E> deleteMin(Node<E> node) {
        if (node.leftChild == null) {
            return node.rightChild;
        }
        node.leftChild = this.deleteMin(node.leftChild);
        node.count--;
        return node;
    }

    public void deleteMax() {
        this.ensureNotEmpty();
        this.root = this.deleteMax(this.root);
    }

    private Node<E> deleteMax(Node<E> node) {
        if (node.rightChild == null) {
            return node.leftChild;
        }
        node.rightChild = this.deleteMax(node.rightChild);
        node.count--;
        return node;
    }

    private void ensureNotEmpty() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty");
        }
    }

    public int count() {
        return count(this.root);
    }

    private static int count(Node<?> node) {
        return node == null ? 0 : node.count;
    }

    public int rank(E element) {
        int rank = 0;
        Node<E> current = this.root;
        while (current != null) {
            int cmp = element.compareTo(current.value);
            if (cmp > 0) {
                rank += 1 + count(current.leftChild);
                current = current.rightChild;
            } else {
                if (cmp == 0) {
                    return rank + count(current.leftChild);
                }
                current = current.leftChild;
            }
        }
        return rank;
    }

    public E ceil(E element) {
        E result = null;
        Node<E> current = this.root;
        while (current != null) {
            int cmp = element.compareTo(current.value);
            if (cmp == 0) {
                return current.value;
            }
            if (cmp < 0) {
                result = current.value;
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }
        return result;
    }

    public E floor(E element) {
        E result = null;
        Node<E> current = this.root;
        while (current != null) {
            int cmp = element.compareTo(current.value);
            if (cmp == 0) {
                return current.value;
            }
            if (cmp > 0) {
                result = current.value;
                current = current.rightChild;
            } else {
                current = current.leftChild;
            }
        }
        return result;
    }
}
