package implementations;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class TreeFactory {
    private Map<Integer, Tree<Integer>> nodesByKeys;

    public TreeFactory() {
        this.nodesByKeys = new LinkedHashMap<>();
    }

    public Tree<Integer> createTreeFromStrings(String[] input) {
        for (String line : input) {
            int[] nodeVal = Arrays.stream(line.split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();

            addEdge(nodeVal[0], nodeVal[1]);
        }

        return getRoot();
    }

    private Tree<Integer> getRoot() {
        for (Tree<Integer> value : nodesByKeys.values()) {
            if (value.getParent() == null) {

                return value;
            }
        }

        return null;
    }

    public Tree<Integer> createNodeByKey(int key) {
        this.nodesByKeys.putIfAbsent(key, new Tree<>(key));
        return this.nodesByKeys.get(key);
    }

    public void addEdge(int parent, int child) {
        Tree<Integer> parentTree = this.createNodeByKey(parent);
        Tree<Integer> childTree = this.createNodeByKey(child);

        parentTree.addChild(childTree);
        childTree.setParent(parentTree);
    }
}