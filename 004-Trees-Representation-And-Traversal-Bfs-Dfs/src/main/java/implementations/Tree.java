package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private ArrayList<Tree<E>> children;

    @SafeVarargs
    public Tree(E element, Tree<E>... children) {
        this.key = element;
        this.children = new ArrayList<>();

        for (Tree<E> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {
        ArrayList<E> data = new ArrayList<>();
        ArrayDeque<Tree<E>> elements = new ArrayDeque<>();
        elements.push(this);

        while (!elements.isEmpty()) {
            Tree<E> element = elements.poll();
            data.add(element.key);
            for (Tree<E> child : element.children) {
                elements.offer(child);
            }
        }

        return data;
    }

    @Override
    public List<E> orderDfs() {
        ArrayList<E> data = new ArrayList<>();

        Tree<E> current = this;
        dfs(current, data);

        return data;
    }

    private void dfs(Tree<E> node, ArrayList<E> data) {
        for (Tree<E> child : node.children) {
            this.dfs(child, data);
        }
        data.add(node.key);
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Tree<E> search = find(parentKey);

        if (search == null) {
            throw new IllegalArgumentException("Not found!");
        }

        search.children.add(child);
        child.parent = search;
    }

    private Tree<E> find(E parentKey) {
        ArrayDeque<Tree<E>> elements = new ArrayDeque<>();
        elements.push(this);

        while (!elements.isEmpty()) {
            Tree<E> element = elements.poll();

            if (element.key.equals(parentKey)) {
                return element;
            }

            for (Tree<E> child : element.children) {
                elements.offer(child);
            }
        }

        return null;
    }

    @Override
    public void removeNode(E nodeKey) {
        if (this.children.size() == 0) {
            this.key = null;
            return;
        }
        Tree<E> foundSearch = find(nodeKey);
        if (foundSearch == null) {
            throw new IllegalArgumentException("Not found!");
        }

        for (Tree<E> child : foundSearch.children) {
            child.parent = null;
        }

        foundSearch.children.clear();

        Tree<E> node = foundSearch.parent;
        if (node != null) {
            node.children.remove(foundSearch);
        }
    }

    @Override
    public void swap(E firstKey, E secondKey) {

    }
}