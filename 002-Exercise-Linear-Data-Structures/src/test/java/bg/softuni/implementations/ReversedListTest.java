package bg.softuni.implementations;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReversedListTest {

    @Test
    public void testGetReturnsReversedOrderAndGrows() {
        ReversedList<Integer> list = new ReversedList<>();
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }

        assertEquals(5, list.size());
        assertEquals(8, list.capacity());
        assertEquals(Integer.valueOf(5), list.get(0));
        assertEquals(Integer.valueOf(1), list.get(4));
    }

    @Test
    public void testRemoveAtAndIterator() {
        ReversedList<Integer> list = new ReversedList<>();
        for (int i = 1; i <= 4; i++) {
            list.add(i);
        }

        assertEquals(Integer.valueOf(3), list.removeAt(1));

        List<Integer> values = new ArrayList<>();
        for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
            values.add(it.next());
        }
        assertEquals(List.of(4, 2, 1), values);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetInvalidIndexThrows() {
        new ReversedList<Integer>().get(0);
    }
}
