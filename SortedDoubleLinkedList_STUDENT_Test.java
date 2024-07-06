import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SortedDoubleLinkedList_STUDENT_Test {

    private SortedDoubleLinkedList<Integer> sortedList;
    private Comparator<Integer> comparator;

    @BeforeEach
    public void setUp() {
        comparator = Integer::compareTo;
        sortedList = new SortedDoubleLinkedList<>(comparator);
    }

    @AfterEach
    public void tearDown() {
        sortedList = null;
    }

    @Test
    public void testAdd() {
        sortedList.add(3);
        sortedList.add(1);
        sortedList.add(2);
        assertEquals(3, sortedList.getSize());
        assertEquals(1, sortedList.getFirst());
        assertEquals(3, sortedList.getLast());

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);

        assertEquals(expected, sortedList.toArrayList());
    }

    @Test
    public void testAddToEnd() {
        assertThrows(UnsupportedOperationException.class, () -> sortedList.addToEnd(1));
    }

    @Test
    public void testAddToFront() {
        assertThrows(UnsupportedOperationException.class, () -> sortedList.addToFront(1));
    }

    @Test
    public void testRemove() {
        sortedList.add(1);
        sortedList.add(2);
        sortedList.add(3);
        assertNotNull(sortedList.remove(2, comparator));
        assertEquals(2, sortedList.getSize());
        assertEquals(1, sortedList.getFirst());
        assertEquals(3, sortedList.getLast());
        assertNull(sortedList.remove(4, comparator));
    }

    @Test
    public void testToArrayList() {
        sortedList.add(1);
        sortedList.add(2);
        sortedList.add(3);
        ArrayList<Integer> arrayList = sortedList.toArrayList();
        assertEquals(3, arrayList.size());
        assertEquals(1, arrayList.get(0));
        assertEquals(2, arrayList.get(1));
        assertEquals(3, arrayList.get(2));
    }

    @Test
    public void testIterator() {
        sortedList.add(1);
        sortedList.add(2);
        sortedList.add(3);
        ListIterator<Integer> iterator = sortedList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorNoSuchElementException() {
        ListIterator<Integer> iterator = sortedList.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testListIterator() {
        sortedList.add(1);
        sortedList.add(2);
        sortedList.add(3);
        ListIterator<Integer> listIterator = sortedList.iterator();
        
        // Forward iteration
        assertTrue(listIterator.hasNext());
        assertEquals(1, listIterator.next());
        assertTrue(listIterator.hasNext());
        assertEquals(2, listIterator.next());
        assertTrue(listIterator.hasNext());
        assertEquals(3, listIterator.next());
        assertFalse(listIterator.hasNext());
        
        // Backward iteration
        assertTrue(listIterator.hasPrevious());
        assertEquals(3, listIterator.previous());
        assertTrue(listIterator.hasPrevious());
        assertEquals(2, listIterator.previous());
        assertTrue(listIterator.hasPrevious());
        assertEquals(1, listIterator.previous());
        assertFalse(listIterator.hasPrevious());
    }

    @Test
    public void testGetFirst() {
        sortedList.add(2);
        sortedList.add(1);
        sortedList.add(3);
        assertEquals(1, sortedList.getFirst());
        sortedList.retrieveFirstElement();
        assertEquals(2, sortedList.getFirst());
    }

    @Test
    public void testGetLast() {
        sortedList.add(2);
        sortedList.add(1);
        sortedList.add(3);
        assertEquals(3, sortedList.getLast());
        sortedList.retrieveLastElement();
        assertEquals(2, sortedList.getLast());
    }

    @Test
    public void testGetSize() {
        assertEquals(0, sortedList.getSize());
        sortedList.add(1);
        assertEquals(1, sortedList.getSize());
        sortedList.add(2);
        assertEquals(2, sortedList.getSize());
        sortedList.retrieveFirstElement();
        assertEquals(1, sortedList.getSize());
    }
}
