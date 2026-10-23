package bg.softuni.implementations;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    private ArrayDeque<Integer> deque;

    @Before
    public void setUp() {
        this.deque = new ArrayDeque<>();
        for (int i = 1; i <= 4; i++) {
            this.deque.addLast(i);
        }
    }

    private List<Integer> toList(ArrayDeque<Integer> d) {
        List<Integer> result = new ArrayList<>();
        for (Integer value : d) {
            result.add(value);
        }
        return result;
    }

    @Test
    public void testIteratorWhenFull() {
        assertEquals(4, deque.capacity());
        assertEquals(List.of(1, 2, 3, 4), toList(deque));
    }

    @Test
    public void testAddFirstWrapsAround() {
        deque.removeLast();
        deque.addFirst(0);
        assertEquals(List.of(0, 1, 2, 3), toList(deque));
        deque.addFirst(-1);
        assertEquals(List.of(-1, 0, 1, 2, 3), toList(deque));
    }

    @Test
    public void testInsertInMiddle() {
        deque.insert(2, 99);
        assertEquals(List.of(1, 2, 99, 3, 4), toList(deque));
        deque.insert(0, 0);
        deque.insert(deque.size(), 100);
        assertEquals(List.of(0, 1, 2, 99, 3, 4, 100), toList(deque));
    }

    @Test
    public void testRemoveFromMiddle() {
        assertEquals(Integer.valueOf(2), deque.remove(1));
        assertEquals(List.of(1, 3, 4), toList(deque));
        assertEquals(Integer.valueOf(4), deque.remove(Integer.valueOf(4)));
        assertNull(deque.remove(Integer.valueOf(42)));
        assertEquals(List.of(1, 3), toList(deque));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetAtSizeThrows() {
        deque.get(deque.size());
    }

    @Test
    public void testStackAndQueueOperations() {
        deque.push(0);
        assertEquals(Integer.valueOf(0), deque.peek());
        assertEquals(Integer.valueOf(0), deque.pop());
        assertEquals(Integer.valueOf(1), deque.poll());
        assertEquals(Integer.valueOf(4), deque.removeLast());
        assertEquals(2, deque.size());
    }

    @Test
    public void testTrimToSizeThenGrow() {
        ArrayDeque<Integer> empty = new ArrayDeque<>();
        empty.trimToSize();
        empty.addLast(1);
        empty.addFirst(0);
        assertEquals(List.of(0, 1), toList(empty));

        deque.removeFirst();
        deque.trimToSize();
        assertEquals(3, deque.capacity());
        assertEquals(List.of(2, 3, 4), toList(deque));
    }

    @Test
    public void testGetByObjectWithNullElement() {
        deque.addFirst(null);
        assertEquals(Integer.valueOf(3), deque.get(Integer.valueOf(3)));
        assertNull(deque.get(Integer.valueOf(42)));
    }
}
