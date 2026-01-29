package bg.softuni.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BinaryTree<E extends Comparable<E>> {
    private int value;
    private BinaryTree left;
    private BinaryTree right;


    public BinaryTree(int key, BinaryTree first, BinaryTree second) {
        this.value = key;
        this.left = first;
        this.right = second;
    }

    public Integer findLowestCommonAncestor(int first, int second) {
        List<Integer> firstPath = findPath(first);
        List<Integer> secondPath = findPath(second);

        int smallerSize = Math.min(firstPath.size(), secondPath.size());

        int i = 0;
        for (; i < smallerSize; i++) {
            if (!firstPath.get(i).equals(secondPath.get(i))) {
                break;
            }
        }
        return firstPath.get(i - 1);
    }

    private List<Integer> findPath(int element) {
        List<Integer> result = new ArrayList<>();
        findNodePath(this, element, result);

        return result;
    }

    private boolean findNodePath(BinaryTree binaryTree, int element, List<Integer> currentPath) {
        if (binaryTree == null) {
            return false;
        }

        currentPath.add(binaryTree.value);

        if (binaryTree.value == element) {
            return true;
        }

        if (findNodePath(binaryTree.left, element, currentPath)) {
            return true;
        }

        if (findNodePath(binaryTree.right, element, currentPath)) {
            return true;
        }

        currentPath.remove(currentPath.size() - 1);
        return false;
    }

    public List<Integer> topView() {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new java.util.TreeMap<>();

        topView(this, 0, map);

        for (Integer value : map.values()) {
            result.add(value);
        }

        return result;
    }

    private void topView(BinaryTree<E> eBinaryTree, int i, Map<Integer, Integer> map) {
        if (eBinaryTree == null) {
            return;
        }

        if (!map.containsKey(i)) {
            map.put(i, eBinaryTree.value);
        }

        topView(eBinaryTree.left, i - 1, map);
        topView(eBinaryTree.right, i + 1, map);
    }

    private void leftView(List<Integer> result) {
        if (this.left != null) {
            this.left.leftView(result);
        }
        result.add(this.value);
    }

    private void traverseTree(BinaryTree binaryTree, int offset, int level, Map<Integer,
            List<Integer>> map) {
        if (binaryTree == null) {
            return;
        }

        if (!map.containsKey(offset)) {
            map.put(offset, new ArrayList<>());
        }

        List<Integer> list = map.get(offset);
        if (list.size() == level) {
            list.add(binaryTree.value);
        }

        traverseTree(binaryTree.left, offset - 1, level + 1, map);
        traverseTree(binaryTree.right, offset + 1, level + 1, map);
    }
}