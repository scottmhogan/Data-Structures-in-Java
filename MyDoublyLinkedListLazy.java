//Scott Hogan
//Data Structures
//5:30-6:45

public class MyDoublyLinkedListLazy<E> {
	private MyNode<E> start, end;
	private int size;
	private int numOfDeleted;

	public MyDoublyLinkedListLazy() {
		start = null;
		end = null;
		size = 0;
		numOfDeleted = 0;
	}

	public MyDoublyLinkedListLazy(E v) {
		start = null;
		end = null;
		size = 0;
		numOfDeleted = 0;
		this.add(v);
	}

	public static void main(String[] args) {
		MyDoublyLinkedListLazy<String> doubleList = new MyDoublyLinkedListLazy<String>();
		doubleList.add("0");
		doubleList.add("1");
		doubleList.add("2");
		doubleList.add("3");
		doubleList.printList();
		doubleList.lazyDelete(0);
		doubleList.printList();
		doubleList.lazyDelete(1);
		doubleList.printList();

	}

	public void printList() {
		MyNode<E> current = start;
		while (current != null) {
			if (current.deleted)
				current = current.next;
			if (current != null) {
				System.out.print(current + ",");
				current = current.next;
			}
		}
		System.out.println();
	}

	public void printListRev() {
		MyNode<E> current = end;
		while (current != null) {
			if (current.deleted)
				current = current.prev;
			if (current == null)
				return;
			else {
				System.out.print(current + ",");
				current = current.prev;
			}
		}
		System.out.println();
	}

	public void add(E v) {
		MyNode<E> temp = new MyNode<E>(v);
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
			add(v);// O(1)
		else {
			MyNode<E> temp = new MyNode<E>(v);
			if (i == 0) {
				temp.next = start;
				start.prev = temp;
				start = temp;
			} else {
				MyNode<E> current = start;
				for (; i > 1; i--) {
					current = current.next;
				}
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
		// Takes advantage of lazy deletion
		if (current != null && !current.deleted)
			return current.val;
		else
			System.out.println("Value not found or value is deleted(lazy)");
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
		if (current != null && !current.deleted)
			return current.val;
		else
			System.out.println();
		System.out.println("Value not found or value is deleted(lazy)");
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

	// Regular delete() method. Lazy deletion below.
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

	// Homework Chapter 3 Assignment 4 - Lazy Deletion. Marks items as deleted
	// instead of actually getting rid of them
	// Also deletes items that are considered deleted if half of the items are
	// considered deleted
	public void lazyDelete(int index) {
		int j = index;
		MyNode<E> temp = start;
		if (start != null) {
			if (start.deleted == true) {
				index++;
			}
			for (int i = 0; i < index; i++) {
				temp = temp.next;
				if (temp.deleted == true) {
					i--;
				}
			}
			temp.deleted = true;
			numOfDeleted++;
			size--;

			//If over half are considered deleted, delete them
			System.out.println("Lazily removed element at index " + j);
			if (numOfDeleted > this.size / 2) {
				System.out.println("Half or more are deleted");
				MyNode<E> current = start;
				if (start.deleted) {
					start = start.next;
					start.prev = null;
					size--;
				} else {
					for (int i = 0; i < this.size; i++) {
						if(current.next == null)
							return;
						if (current.deleted) {
							current = null;
							numOfDeleted--;
							size--;
						}
						current = current.next;

					}
				}
			}
		}
	}

	private static class MyNode<E> {
		public E val;
		public boolean deleted;
		public MyNode<E> next, prev;

		public MyNode(E v) {
			deleted = false;
			val = v;
			next = null;
			prev = null;
		}

		public String toString() {
			return val.toString();
		}
	}
}
