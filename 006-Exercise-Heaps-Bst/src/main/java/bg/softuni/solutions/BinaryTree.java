package bg.softuni.solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

        if (firstPath.isEmpty() || secondPath.isEmpty()) {
            return null;
        }

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
        // BFS so the shallowest node at each horizontal offset wins
        Map<Integer, Integer> map = new TreeMap<>();
        Deque<BinaryTree> nodes = new ArrayDeque<>(List.of(this));
        Deque<Integer> offsets = new ArrayDeque<>(List.of(0));

        while (!nodes.isEmpty()) {
            BinaryTree node = nodes.poll();
            int offset = offsets.poll();
            map.putIfAbsent(offset, node.value);
            if (node.left != null) {
                nodes.offer(node.left);
                offsets.offer(offset - 1);
            }
            if (node.right != null) {
                nodes.offer(node.right);
                offsets.offer(offset + 1);
            }
        }

        return new ArrayList<>(map.values());
    }
}
