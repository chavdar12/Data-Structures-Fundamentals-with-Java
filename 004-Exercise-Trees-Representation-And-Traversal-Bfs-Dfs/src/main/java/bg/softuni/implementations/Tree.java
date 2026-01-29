package bg.softuni.implementations;

import bg.softuni.interfaces.AbstractTree;

import java.util.ArrayList;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {

    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key, Tree<E>... children) {
        this.key = key;
        this.children = new ArrayList<>();
        for (Tree<E> child : children) {
            this.children.add(child);
            child.setParent(this);
        }
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public String getAsString() {
        StringBuilder sb = new StringBuilder();
        this.print(this, 0, sb);
        return sb.toString().trim();
    }

    private void print(Tree<E> eTree, int i, StringBuilder sb) {
        sb.append(String.format("%s%s%n", this.getPadding(i), eTree.getKey()));
        for (Tree<E> child : eTree.children) {
            this.print(child, i + 2, sb);
        }
    }

    private Object getPadding(int i) {
        return " ".repeat(Math.max(0, i));
    }

    @Override
    public List<E> getLeafKeys() {
        List<E> leafKeys = new ArrayList<>();
        this.getLeafKeys(this, leafKeys);
        return leafKeys;
    }

    private void getLeafKeys(Tree<E> eTree, List<E> leafKeys) {
        for (Tree<E> child : eTree.children) {
            if (child.children.isEmpty()) {
                leafKeys.add(child.getKey());
            } else {
                this.getLeafKeys(child, leafKeys);
            }
        }
    }

    @Override
    public List<E> getMiddleKeys() {
        List<E> middleKeys = new ArrayList<>();
        this.getMiddleKeys(this, middleKeys);
        return middleKeys;
    }

    private void getMiddleKeys(Tree<E> eTree, List<E> middleKeys) {
        for (Tree<E> child : eTree.children) {
            if (!child.children.isEmpty()) {
                middleKeys.add(child.getKey());
                this.getMiddleKeys(child, middleKeys);
            }
        }
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        Tree<E> deepestLeftmostNode = this;
        deepestLeftmostNode = this.getDeepestLeftmostNode(this, deepestLeftmostNode, 0);
        return deepestLeftmostNode;
    }

    private Tree<E> getDeepestLeftmostNode(Tree<E> eTree, Tree<E> deepestLeftmostNode, int i) {
        for (Tree<E> child : eTree.children) {
            if (child.children.isEmpty()) {
                if (i > deepestLeftmostNode.children.size()) {
                    deepestLeftmostNode = child;
                }
            } else {
                deepestLeftmostNode = this.getDeepestLeftmostNode(child, deepestLeftmostNode, i + 1);
            }
        }
        return deepestLeftmostNode;
    }

    @Override
    public List<E> getLongestPath() {
        List<E> longestPath = new ArrayList<>();
        this.getLongestPath(this, longestPath, 0);
        return longestPath;
    }

    private void getLongestPath(Tree<E> eTree, List<E> longestPath, int i) {
        for (Tree<E> child : eTree.children) {
            if (child.children.isEmpty()) {
                if (i > longestPath.size()) {
                    longestPath.clear();
                    longestPath.add(child.getKey());
                }
            } else {
                longestPath.add(child.getKey());
                this.getLongestPath(child, longestPath, i + 1);
            }
        }
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        List<List<E>> pathsWithGivenSum = new ArrayList<>();
        this.pathsWithGivenSum(this, pathsWithGivenSum, new ArrayList<>(), sum);
        return pathsWithGivenSum;
    }

    private <E> void pathsWithGivenSum(Tree eTree, List<List<E>> pathsWithGivenSum, ArrayList<E> es, int sum) {
        for (Object child : eTree.children) {
            if (((Tree) child).children.isEmpty()) {
                if (sum == (int) ((Tree) child).getKey()) {
                    es.add((E) ((Tree<?>) child).getKey());
                    pathsWithGivenSum.add(new ArrayList<>(es));
                    es.remove(es.size() - 1);
                }
            } else {
                es.add((E) ((Tree) child).getKey());
                this.pathsWithGivenSum((Tree) child, pathsWithGivenSum, es, sum - (int) ((Tree) child).getKey());
                es.remove(es.size() - 1);
            }
        }
    }


    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        List<Tree<E>> subTreesWithGivenSum = new ArrayList<>();
        this.subTreesWithGivenSum(this, sum);
        return subTreesWithGivenSum;
    }

    private void subTreesWithGivenSum(Tree<E> eTree, int sum) {
        for (Tree<E> child : eTree.children) {
            if (!child.children.isEmpty()) {
                this.subTreesWithGivenSum(child, sum - (int) child.getKey());
            }
        }
    }
}