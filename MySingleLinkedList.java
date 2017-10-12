//Scott Hogan
//Data Structures
//5:30-6:45

public class MySingleLinkedList<E> {

	public static void main(String[] args) {
		MySingleLinkedList<String> list = new MySingleLinkedList<>();
		System.out.println("Swap with Single Linked List");
		list.add("Zero");
		list.add("One");
		list.add("Two");
		list.add("Three");
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

		MyNode<E> prev;
		MyNode<E> nen;
		//Special case for swapping index 0
		if (i == 0) {
			prev = start;
			nen = start.next;
			prev.next = nen.next;
			nen.next = prev;
			start = nen;
		} else {
			MyNode<E> current = start;
			for (; i > 1; i--) {
				current = current.next;
			}

			if (current.next != null && current.next.next != null) {
				prev = current;
				nen = current.next;
				prev.next = nen.next;
				nen.next = nen.next.next;
				prev.next.next = nen;
			}
		}
	}

	private class MyNode<E> {
		public E val;
		public MyNode<E> next;

		public MyNode(E v) {
			val = v;
			next = null;
		}

		public String toString() {
			return val.toString();
		}
	}

	private MyNode<E> start;
	private int size;

	public MySingleLinkedList() {
		start = null;
		size = 0;
	}

	public MySingleLinkedList(E v) {
		start = null;
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

	public void add(E v) {
		MyNode<E> temp = new MyNode<>(v);
		if (start == null) {
			start = temp;
		} else {
			MyNode<E> current = start;
			// run until finding the end of the list
			while (current.next != null) {
				current = current.next;
			}
			// current is now the end of the list
			current.next = temp;
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
				start = temp;
			} else {
				MyNode<E> current = start;
				// run until finding the end of the list
				for (; i > 1; i--) {
					current = current.next;
				}
				// current is now the item before we want to insert
				temp.next = current.next;
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
			size--;
			return current.val;
		}
		while (current.next != null && !current.next.val.equals(v)) {
			current = current.next;
		}
		if (current.next != null) {
			current.next = current.next.next;
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
			size--;
			return current.val;
		}
		for (; i > 1; i--) {
			current = current.next;
		}
		if (current != null) {
			current.next = current.next.next;
			size--;
			return current.val;
		} else
			return null;
	}

}