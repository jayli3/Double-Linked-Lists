import java.util.Comparator;
import java.util.ListIterator;

/**
 * A sorted implementation of a double linked list.
 *
 * @param <T> the type of elements held in this list
 * 
 * @author JLi
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	private Comparator<T> comparator;
	
	/**
     * Constructs a sorted double linked list with the specified comparator.
     *
     * @param comparator the comparator to determine the ordering of elements
     */
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
	/**
     * Adds a new element to the sorted list maintaining the order determined by the comparator.
     *
     * @param data the data to be added
     */
	public void add(T data) {
		Node newNode = new Node(data);
        if (head == null) { 
            head = newNode;
            tail = newNode;
        } else {
            Node current = head;
            Node prev = null;
            while (current != null && comparator.compare(data, current.data) > 0) {
                prev = current;
                current = current.next;
            }
            if (prev == null) { 
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else if (current == null) { 
                prev.next = newNode;
                newNode.prev = prev;
                tail = newNode;
            } else { 
                prev.next = newNode;
                newNode.prev = prev;
                newNode.next = current;
                current.prev = newNode;
            }
        }
        size++;
	}
	
	/**
     * Throws UnsupportedOperationException as adding to the end is invalid for a sorted list.
     *
     * @param data the data to be added
     * @throws UnsupportedOperationException always thrown as this operation is not supported
     */
	@Override
    public void addToEnd(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

	/**
     * Throws UnsupportedOperationException as adding to the front is invalid for a sorted list.
     *
     * @param data the data to be added
     * @throws UnsupportedOperationException always thrown as this operation is not supported
     */
    @Override
    public void addToFront(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }
	
    /**
     * Returns a list iterator over the elements in this list (in proper sequence).
     *
     * @return a list iterator over the elements in this list
     */
    @Override
    public ListIterator<T> iterator() {
        return super.iterator(); 
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * @param data the element to be removed from this list, if present
     * @param comparator the comparator to determine equality of elements
     * @return the node containing the removed element, or null if the element was not found
     */
    @Override
    public Node remove(T data, Comparator<T> comparator) {
        return super.remove(data, comparator);
    }
}
