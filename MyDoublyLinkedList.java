//Scott Hogan
//Data Structures
//5:30-6:45

public class MyDoublyLinkedList<E> {

	public static void main(String[] args) {
		MyDoublyLinkedList<String> list = new MyDoublyLinkedList<>();
		System.out.println("Swap with Double Linked List");
		list.add("Four");
		list.add("Five");
		list.add("Six");
		list.add("Seven");
		list.printList();
		list.swapNode(0);
		list.printList();
	}

	// Homework Assignment 4 Chapter 3
	public void swapNode(int i) {
		//Special cases
		if (i < 0)
			i = 0;
		if (i > size - 1)
			i = size - 1;
		
		//Node to save things temporarily
		MyNode<E> prev;
		MyNode<E> nen;
		//Special cases for swapping index 0
		if (i == 0) {
			prev = start;
			nen = start.next;
			prev.next = nen.next; 
			prev.prev = nen;
			nen.next.prev = prev;
			nen.next = prev; 
			nen.prev = null;
			start = nen;
		} else {
			MyNode<E> current = start;
			for (; i > 0; i--) {
				current = current.next;
			}
			
			if (current != null && current.next != null) {
				prev = current;
				nen = current.next;
				prev.prev.next = nen;
				nen.prev = prev.prev;
				if (current.next.next != null) {
					prev.next = nen.next;
					nen.next.prev = prev;
				} else {
					prev.next = null;
					end = prev;
				}
				nen.next = prev;
				prev.prev = nen;
			}
		}
	}

	private class MyNode<E> {
		public E val;
		public MyNode<E> next, prev;

		public MyNode(E v) {
			val = v;
			next = null;
			prev = null;
		}

		public String toString() {
			return val.toString();
		}
	}

	private MyNode<E> start, end;
	private int size;

	public MyDoublyLinkedList() {
		start = null;
		end = null;
		size = 0;
	}

	public MyDoublyLinkedList(E v) {
		start = null;
		end = null;
		size = 0;
		this.add(v);
	}

	public void printList() {
		MyNode<E> current = start;
		while (current != null) {
			System.out.print(current + ",");
			current = current.next;
		}
		System.out.println();
	}

	public void printListRev() {
		MyNode<E> current = end;
		while (current != null) {
			System.out.print(current + ",");
			current = current.prev;
		}
		System.out.println();
	}

	public void add(E v) {
		MyNode<E> temp = new MyNode<>(v);
		if (start == null) {
			start = temp;
			end = start;
		} else {
			end.next = temp;
			temp.prev = end;
			end = end.next;
		}
		size++;
	}

	public void insert(E v, int i) {
		if (i < 0)
			i = 0;
		if (i >= size)
			add(v);
		else {
			MyNode<E> temp = new MyNode<>(v);
			if (i == 0) {
				temp.next = start;
				start.prev = temp;
				start = temp;
			} else {
				/* possibly place to start near end might be faster */
				MyNode<E> current = start;
				// run until finding the end of the list
				for (; i > 1; i--) {
					current = current.next;
				}
				// current is now the item before we want to insert
				temp.next = current.next;
				temp.prev = current;
				current.next.prev = temp;
				current.next = temp;
			}
		}
	}

	public E find(E v) {
		MyNode<E> current = start;
		while (current != null && !current.val.equals(v)) {
			current = current.next;
		}
		if (current != null)
			return current.val;
		else
			return null;
	}

	public E get(int i) {
		if (i < 0)
			i = 0;
		if (i > size - 1)
			i = size - 1;
		/* possibly place to start near end might be faster */
		MyNode<E> current = start;
		for (; i > 0; i--) {
			current = current.next;
		}
		if (current != null)
			return current.val;
		else
			return null;
	}

	public E remove(E v) {
		MyNode<E> current = start;
		if (start != null && start.val.equals(v)) {
			start = start.next;
			if (start != null)
				start.prev = null;
			size--;
			return current.val;
		}
		if (end != null && end.val.equals(v)) {
			current = end;
			end = end.prev;
			if (end != null)
				end.next = null;
			size--;
			return current.val;
		}
		while (current != null && !current.val.equals(v)) {
			current = current.next;
		}
		if (current != null) {
			current.prev.next = current.next;
			current.next.prev = current.prev;
			size--;
			return current.val;
		} else
			return null;
	}

	public E delete(int i) {
		if (i < 0)
			i = 0;
		if (i > size - 1)
			i = size - 1;
		MyNode<E> current = start;
		if (start != null && i == 0) {
			start = start.next;
			if (start != null)
				start.prev = null;
			size--;
			return current.val;
		}
		if (end != null && i == size - 1) {
			current = end;
			end = end.prev;
			if (end != null)
				end.next = null;
			size--;
			return current.val;
		}
		/* possibly place to start near end might be faster */
		for (; i > 1; i--) {
			current = current.next;
		}
		if (current != null) {
			current.next = current.next.next;
			current.next.prev = current;
			size--;
			return current.val;
		} else
			return null;
	}

}
