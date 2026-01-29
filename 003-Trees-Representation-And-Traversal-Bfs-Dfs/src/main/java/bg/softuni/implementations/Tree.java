package bg.softuni.implementations;

import bg.softuni.interfaces.AbstractTree;

import java.util.ArrayList;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {

    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    @SafeVarargs
    public Tree(E key, Tree<E>... children) {
        this.key = key;
        this.children = new ArrayList<>();
        for (Tree<E> child : children) {
            child.parent = this;
            this.children.add(child);
        }
    }


    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();
        List<Tree<E>> queue = new ArrayList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            Tree<E> current = queue.remove(0);
            result.add(current.key);
            queue.addAll(current.children);
        }
        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> result = new ArrayList<>();
        dfs(this, result);
        return result;
    }

    private void dfs(Tree<E> eTree, List<E> result) {
        for (Tree<E> child : eTree.children) {
            dfs(child, result);
        }
        result.add(eTree.key);
    }

    @Override
    public void removeNode(E nodeKey) {
        Tree<E> node = findNode(nodeKey);
        if (node == null) {
            throw new IllegalArgumentException();
        }
        if (node.parent == null) {
            throw new IllegalArgumentException();
        }
        node.parent.children.remove(node);
    }

    private Tree<E> findNode(E nodeKey) {
        if (this.key.equals(nodeKey)) {
            return this;
        }
        for (Tree<E> child : children) {
            Tree<E> node = child.findNode(nodeKey);
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    @Override
    public void swap(E firstKey, E secondKey) {
        Tree<E> firstNode = findNode(firstKey);
        Tree<E> secondNode = findNode(secondKey);
        if (firstNode == null || secondNode == null) {
            throw new IllegalArgumentException();
        }
        Tree<E> firstParent = firstNode.parent;
        Tree<E> secondParent = secondNode.parent;
        if (firstParent == null || secondParent == null) {
            throw new IllegalArgumentException();
        }
        int firstIndex = firstParent.children.indexOf(firstNode);
        int secondIndex = secondParent.children.indexOf(secondNode);
        firstParent.children.set(firstIndex, secondNode);
        secondParent.children.set(secondIndex, firstNode);
        firstNode.parent = secondParent;
        secondNode.parent = firstParent;
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Tree<E> parent = findNode(parentKey);
        if (parent == null) {
            throw new IllegalArgumentException();
        }
        parent.children.add(child);
        child.parent = parent;
    }
}