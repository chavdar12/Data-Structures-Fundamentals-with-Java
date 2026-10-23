package bg.softuni.solutions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LowestCommonAncestorTest {

    @Test
    public void testLCA() {
        BinaryTree binaryTree =
                new BinaryTree(7,
                        new BinaryTree(21, null, null),
                        new BinaryTree(14,
                                new BinaryTree(23, null, null),
                                new BinaryTree(6, null,
                                        new BinaryTree(13, null, null))));

        assertEquals(Integer.valueOf(14), binaryTree.findLowestCommonAncestor(23, 13));
    }

    @Test
    public void testMissingNodeReturnsNull() {
        BinaryTree binaryTree = new BinaryTree(7, new BinaryTree(21, null, null), null);

        assertNull(binaryTree.findLowestCommonAncestor(21, 99));
    }
}
