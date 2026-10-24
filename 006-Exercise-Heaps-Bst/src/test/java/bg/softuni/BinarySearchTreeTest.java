package bg.softuni;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> bst;

    @Before
    public void setUp() {
        this.bst = new BinarySearchTree<>();
        for (int value : new int[]{12, 21, 5, 1, 8, 18, 23}) {
            this.bst.insert(value);
        }
    }

    private List<Integer> inOrder(BinarySearchTree<Integer> tree) {
        List<Integer> result = new ArrayList<>();
        tree.eachInOrder(result::add);
        return result;
    }

    @Test
    public void testInsertAndEachInOrder() {
        assertEquals(Integer.valueOf(12), bst.getRoot().getValue());
        assertEquals(Integer.valueOf(5), bst.getRoot().getLeft().getValue());
        assertEquals(Integer.valueOf(21), bst.getRoot().getRight().getValue());
        assertEquals(List.of(1, 5, 8, 12, 18, 21, 23), inOrder(bst));
    }

    @Test
    public void testInsertIgnoresDuplicates() {
        bst.insert(8);
        assertEquals(7, bst.count());
    }

    @Test
    public void testContains() {
        assertTrue(bst.contains(18));
        assertFalse(bst.contains(2));
    }

    @Test
    public void testSearchReturnsSubtree() {
        BinarySearchTree<Integer> found = bst.search(5);
        assertEquals(List.of(1, 5, 8), inOrder(found));
        assertEquals(3, found.count());
        assertNull(bst.search(100).getRoot());
    }

    @Test
    public void testRangeIsInclusiveAndOrdered() {
        assertEquals(List.of(5, 8, 12, 18), bst.range(4, 18));
        assertEquals(List.of(), bst.range(13, 17));
    }

    @Test
    public void testDeleteMinAndMax() {
        bst.deleteMin();
        bst.deleteMax();
        assertEquals(List.of(5, 8, 12, 18, 21), inOrder(bst));
        assertEquals(5, bst.count());
    }

    @Test
    public void testDeleteMinUntilEmpty() {
        for (int i = 0; i < 7; i++) {
            bst.deleteMin();
        }
        assertEquals(0, bst.count());
        assertNull(bst.getRoot());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteMinOnEmptyThrows() {
        new BinarySearchTree<Integer>().deleteMin();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteMaxOnEmptyThrows() {
        new BinarySearchTree<Integer>().deleteMax();
    }

    @Test
    public void testRank() {
        assertEquals(0, bst.rank(1));
        assertEquals(3, bst.rank(12));
        assertEquals(4, bst.rank(13));
        assertEquals(7, bst.rank(100));
    }

    @Test
    public void testCeilAndFloor() {
        assertEquals(Integer.valueOf(18), bst.ceil(13));
        assertEquals(Integer.valueOf(12), bst.ceil(12));
        assertNull(bst.ceil(24));
        assertEquals(Integer.valueOf(12), bst.floor(13));
        assertEquals(Integer.valueOf(8), bst.floor(8));
        assertNull(bst.floor(0));
    }
}
