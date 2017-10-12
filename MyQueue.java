//Scott Hogan
//MyQueue.java
//Data Structure 5:30-6:45

import java.util.Iterator;

public class MyQueue<E> implements Iterable<E> {

	// Tester class for MyQueue
	public static void main(String[] args) {
		MyQueue<String> queue = new MyQueue<String>();
		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");
		queue.enqueue("D");
		System.out.println(queue);
		System.out.println("Item first in Queue: " + queue.peek());
		System.out.println("Adding E at end of Queue");
		queue.enqueue("E");
		System.out.println(queue);
		System.out.println("Dequeueing...");
		queue.dequeue();
		System.out.println("Queue after Dequeueing: " + queue);
	}

	public Iterator<E> iterator() {
		return new ListIterator<E>(first);
	}

	private class ListIterator<E> implements Iterator<E> {
		private Node<E> current;

		public ListIterator(Node<E> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public E next() {
			if (!hasNext())
				return null;
			E item = current.item;
			current = current.next;
			return item;
		}
	}

	// Keeps track of the first and last nodes in the queue
	private Node<E> first;
	private Node<E> last;

	// Helper Class
	private static class Node<E> {
		private E item;
		private Node<E> next;
	}

	// Constructor
	public MyQueue() {
		first = null;
		last = null;
	}

	// Checks to see if the queue is empty. Used in other methods for error
	// handling.
	public boolean isEmpty() {
		return first == null;
	}

	// Enqueue adds an item to the queue. This means it goes to the end of the line
	public void enqueue(E item) {
		Node<E> oldPrev = last;
		last = new Node<E>();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldPrev.next = last;
	}

	// Removes the item from the beginning of the queue. If the queue is empty, uses
	// error handling.
	public E dequeue() {
		if (isEmpty()) {
			System.out.println("Nothing in Queue");
			return null;
		} else {
			E item = first.item;
			first = first.next;
			if (isEmpty())
				last = null;
			return item;
		}
	}

	// Looks at the item first in the queue. If there is nothing in the queue then
	// uses error handling
	public E peek() {
		if (isEmpty()) {
			System.out.println("Nothing in Queue");
			return null;
		} else
			return first.item;
	}

	// Takes advantage of the StringBuilder class in Java to easily build a new
	// string. Better than creating a String Array. Runs in constant time.
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (E item : this) {
			s.append(item);
			s.append(' ');
		}
		return s.toString();
	}

}
