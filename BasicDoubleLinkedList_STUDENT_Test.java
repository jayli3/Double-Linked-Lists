import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList_STUDENT_Test {

    private BasicDoubleLinkedList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new BasicDoubleLinkedList<>();
    }

    @AfterEach
    public void tearDown() {
        list = null;
    }

    @Test
    public void testAddToEnd() {
        list.addToEnd(1);
        list.addToEnd(2);
        assertEquals(2, list.getSize());
        assertEquals(1, list.getFirst());
        assertEquals(2, list.getLast());
    }

    @Test
    public void testAddToFront() {
        list.addToFront(1);
        list.addToFront(2);
        assertEquals(2, list.getSize());
        assertEquals(2, list.getFirst());
        assertEquals(1, list.getLast());
    }

    @Test
    public void testRetrieveFirstElement() {
        list.addToEnd(1);
        list.addToEnd(2);
        assertEquals(1, list.retrieveFirstElement());
        assertEquals(1, list.getSize());
        assertEquals(2, list.getFirst());
    }

    @Test
    public void testRetrieveLastElement() {
        list.addToEnd(1);
        list.addToEnd(2);
        assertEquals(2, list.retrieveLastElement());
        assertEquals(1, list.getSize());
        assertEquals(1, list.getLast());
    }

    @Test
    public void testRemove() {
        list.addToEnd(1);
        list.addToEnd(2);
        list.addToEnd(3);
        Comparator<Integer> comparator = Integer::compareTo;
        assertNotNull(list.remove(2, comparator));
        assertEquals(2, list.getSize());
        assertEquals(1, list.getFirst());
        assertEquals(3, list.getLast());
        assertNull(list.remove(4, comparator));
    }

    @Test
    public void testToArrayList() {
        list.addToEnd(1);
        list.addToEnd(2);
        list.addToEnd(3);
        ArrayList<Integer> arrayList = list.toArrayList();
        assertEquals(3, arrayList.size());
        assertEquals(1, arrayList.get(0));
        assertEquals(2, arrayList.get(1));
        assertEquals(3, arrayList.get(2));
    }

    @Test
    public void testIterator() {
        list.addToEnd(1);
        list.addToEnd(2);
        list.addToEnd(3);
        Iterator<Integer> iterator = list.iterator();
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
        Iterator<Integer> iterator = list.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testListIterator() {
        list.addToEnd(1);
        list.addToEnd(2);
        list.addToEnd(3);
        ListIterator<Integer> listIterator = list.iterator();
        
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
        list.addToEnd(1);
        list.addToEnd(2);
        assertEquals(1, list.getFirst());
        list.retrieveFirstElement();
        assertEquals(2, list.getFirst());
    }

    @Test
    public void testGetLast() {
        list.addToEnd(1);
        list.addToEnd(2);
        assertEquals(2, list.getLast());
        list.retrieveLastElement();
        assertEquals(1, list.getLast());
    }

    @Test
    public void testGetSize() {
        assertEquals(0, list.getSize());
        list.addToEnd(1);
        assertEquals(1, list.getSize());
        list.addToEnd(2);
        assertEquals(2, list.getSize());
        list.retrieveFirstElement();
        assertEquals(1, list.getSize());
    }
}
