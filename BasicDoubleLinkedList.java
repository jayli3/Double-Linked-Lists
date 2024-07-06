import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A basic implementation of a double linked list.
 *
 * @param <T> the type of elements held in this list
 * 
 * @author JLi
 */

public class BasicDoubleLinkedList<T> implements Iterable<T> {
	protected Node head;
	protected Node tail;
	protected int size;
	
	/**
     * Constructs an empty list.
     */
	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
     * Adds a new element to the end of the list.
     * 
     * @param data the data to be added
     */
	public void addToEnd(T data) {
		Node newNode = new Node(data);
		if (tail == null) {
			tail = newNode;
			head = tail;
		} else {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}
	
	/**
     * Adds a new element to the front of the list.
     * 
     * @param data the data to be added
     */
	public void addToFront(T data) {
		Node newNode= new Node(data);
		if (head == null) {
			tail = newNode;
			head = tail;
		} else {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		size++;
	}
	
	/**
     * Returns the first element in the list.
     * 
     * @return the first element, or null if the list is empty
     */
	public T getFirst() {
		return head != null ? head.data : null;
	}
	
	/**
     * Returns the last element in the list.
     * 
     * @return the last element, or null if the list is empty
     */
	public T getLast() {
		return tail != null ? tail.data : null;
	}
	
	/**
     * Returns the size of the list.
     * 
     * @return the size of the list
     */
	public int getSize() {
		return size;
	}
	
	/**
     * Returns a list iterator over the elements in this list (in proper sequence).
     * 
     * @return a list iterator over the elements in this list
     */
	public ListIterator<T> iterator() {
	    return new DoubleLinkedListIterator();
	}
	
	/**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * 
     * @param targetData the element to be removed from this list, if present
     * @param comparator the comparator to determine equality of elements
     * @return the node containing the removed element, or null if the element was not found
     */
	public Node	remove(T targetData, Comparator<T> comparator) {
		Node current = head;
		while (current != null) {
			if (comparator.compare(targetData, current.data) == 0) {
				if (current == head) {
					head = current.next;
					if (head != null) {
						head.prev = null;
					}
				} else {
					current.prev.next = current.next;
				}
				if (current == tail) {
					tail = current.prev;
					if (tail != null) {
						tail.next = null;
					}
				} else {
					current.next.prev = current.prev;
				}
				size--;
				return current;
			}
			current = current.next;
		}
		return null;
	}
	
	/**
     * Retrieves and removes the first element of this list.
     * 
     * @return the first element, or null if the list is empty
     */
	public T retrieveFirstElement() {        
        if (head == null) {
            return null;
        }
        T data = head.data;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return data;
	}
	
	/**
     * Retrieves and removes the last element of this list.
     * 
     * @return the last element, or null if the list is empty
     */
	public T retrieveLastElement() {
		if (tail == null) {
            return null;
        }
        T data = tail.data;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return data;
	}
	
	/**
     * Returns an ArrayList containing all of the elements in this list in proper sequence (from first to last element).
     * 
     * @return an ArrayList containing all of the elements in this list in proper sequence
     */
	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
	}
	
	/**
     * Node class used for building the linked list.
     */
	protected class Node {
        T data;
        Node prev;
        Node next;
        
        /**
         * Constructs a new node with the specified data.
         * 
         * @param data the data to be stored in the node
         */
        Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

	/**
     * List iterator implementation for the double linked list.
     */
	protected class DoubleLinkedListIterator implements ListIterator<T> {
		private Node current;
		private int index;
		
		/**
         * Constructs a new list iterator starting at the head of the list.
         */
		DoubleLinkedListIterator() {
			current = head;
			index = 0;
		}
		
		/**
         * Checks if there is a next element in the list.
         *
         * @return true if there is a next element, false otherwise
         */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
         * Returns the next element in the list and advances the iterator.
         *
         * @return the next element in the list
         * @throws NoSuchElementException if there are no more elements
         */
		@Override
	    public T next() {
	        if (!hasNext()) {
	            throw new NoSuchElementException();
	        }
	        T data = current.data;
	        current = current.next;
	        index++;
	        return data;
	    }

		/**
         * Checks if there is a previous element in the list.
         *
         * @return true if there is a previous element, false otherwise
         */
		@Override
		public boolean hasPrevious() {
		    return (current != head);
		}

		/**
         * Returns the previous element in the list and moves the iterator backwards.
         *
         * @return the previous element in the list
         * @throws NoSuchElementException if there are no previous elements
         */
		@Override
		public T previous() {
		    if (!hasPrevious()) {
		        throw new NoSuchElementException();
		    }
		    if (current == null) {
		        current = tail; 
		    } else {
		        current = current.prev;
		    }
		    index--;
		    return current.data;
		}
		
		/**
         * Unsupported operation.
         *
         * @throws UnsupportedOperationException when this method is called
         */
		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		/**
         * Unsupported operation.
         *
         * @throws UnsupportedOperationException when this method is called
         */
		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		/**
         * Unsupported operation.
         *
         * @throws UnsupportedOperationException when this method is called
         */
		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
			
		}

		/**
         * Unsupported operation.
         *
         * @throws UnsupportedOperationException when this method is called
         */
		@Override
		public void set(Object e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
			
		}

		/**
         * Unsupported operation.
         *
         * @throws UnsupportedOperationException when this method is called
         */
		@Override
		public void add(Object e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		
	}

}
