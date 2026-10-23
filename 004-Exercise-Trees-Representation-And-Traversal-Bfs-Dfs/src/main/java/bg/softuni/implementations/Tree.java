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
        List<Tree<E>> deepest = new ArrayList<>(List.of(this));
        int[] maxDepth = {0};
        this.findDeepest(this, 0, maxDepth, deepest);
        return deepest.get(0);
    }

    private void findDeepest(Tree<E> eTree, int depth, int[] maxDepth, List<Tree<E>> deepest) {
        if (depth > maxDepth[0]) {
            maxDepth[0] = depth;
            deepest.set(0, eTree);
        }
        for (Tree<E> child : eTree.children) {
            this.findDeepest(child, depth + 1, maxDepth, deepest);
        }
    }

    @Override
    public List<E> getLongestPath() {
        List<E> longestPath = new ArrayList<>();
        for (Tree<E> node = this.getDeepestLeftmostNode(); node != null; node = node.parent) {
            longestPath.add(0, node.getKey());
        }
        return longestPath;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        List<List<E>> pathsWithGivenSum = new ArrayList<>();
        this.pathsWithGivenSum(this, pathsWithGivenSum, new ArrayList<>(), sum);
        return pathsWithGivenSum;
    }

    private void pathsWithGivenSum(Tree<E> eTree, List<List<E>> pathsWithGivenSum, List<E> path, int sum) {
        path.add(eTree.getKey());
        int remaining = sum - (int) eTree.getKey();
        if (eTree.children.isEmpty()) {
            if (remaining == 0) {
                pathsWithGivenSum.add(new ArrayList<>(path));
            }
        } else {
            for (Tree<E> child : eTree.children) {
                this.pathsWithGivenSum(child, pathsWithGivenSum, path, remaining);
            }
        }
        path.remove(path.size() - 1);
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        List<Tree<E>> subTreesWithGivenSum = new ArrayList<>();
        this.subTreeSum(this, sum, subTreesWithGivenSum);
        return subTreesWithGivenSum;
    }

    private int subTreeSum(Tree<E> eTree, int sum, List<Tree<E>> subTreesWithGivenSum) {
        int total = (int) eTree.getKey();
        for (Tree<E> child : eTree.children) {
            total += this.subTreeSum(child, sum, subTreesWithGivenSum);
        }
        if (total == sum) {
            subTreesWithGivenSum.add(eTree);
        }
        return total;
    }
}
