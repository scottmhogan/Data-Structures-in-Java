//Scott Hogan
//Data Structures and Algorithm Analysis
//5:30-6:45 TR

import java.util.ArrayDeque;

public class MyBinarySearchTree<E extends Comparable<? super E>> {
	private MyTreeNode<E> root;
	private int size, numDeleted;
	private final int MIN = 100;

	public MyBinarySearchTree() {
		root = null;
		size = 0;
		numDeleted = 0;
	}

	public static void main(String[] args) {
		MyBinarySearchTree<Integer> tree1 = new MyBinarySearchTree<Integer>();
		MyBinarySearchTree<Integer> tree2 = new MyBinarySearchTree<Integer>();
		MyBinarySearchTree<Integer> lazyTree = new MyBinarySearchTree<Integer>();

		System.out.println("Inserting values into tree: (4, 7, 10, 2, 3, 9, 11, 1)");
		System.out.println("Printing tree1 in order:");
		tree1.insert(4);
		tree1.insert(7);
		tree1.insert(10);
		tree1.insert(2);
		tree1.insert(3);
		tree1.insert(9);
		tree1.insert(11);
		tree1.insert(1);
		tree1.inOrderPrint();
		System.out.println("Printing tree1 in level order:");
		tree1.levelOrder(new MyBinarySearchTree.Search<Integer>() {
			@Override
			public void s(MyBinarySearchTree.MyTreeNode<Integer> node) {
				System.out.print(node.val + " ");
			}
		});

		System.out.println();
		System.out.println();
		System.out.println("Creating clone of tree1 called tree2...");
		tree2.insert(4);
		tree2.insert(7);
		tree2.insert(10);
		tree2.insert(2);
		tree2.insert(3);
		tree2.insert(9);
		tree2.insert(11);
		tree2.insert(1);
		System.out.println("Checking if tree1 and tree2 are visually identical: " + equalTrees(tree1.root, tree2.root));
		System.out.println("Inserting another node into tree2...");
		tree2.insert(5);
		System.out.println("Checking if tree1 and tree2 are visually identical: " + equalTrees(tree1.root, tree2.root));

		System.out.println();
		System.out.println("Creating lazyTree with (5, 8, 9, 3, 4)");
		lazyTree.insert(5);
		lazyTree.insert(8);
		lazyTree.insert(9);
		lazyTree.insert(3);
		lazyTree.insert(4);
		System.out.println("Lazily deleting value 3");
		lazyTree.lazyRemove(3);
		System.out.println("Checking to see if lazyTree has value 3: " + lazyTree.contains(3));
		System.out.println();

		System.out.println("Finding min after lazily deleting 3: " + lazyTree.findMin());
		System.out.println("Finding max: " + lazyTree.findMax());
		lazyTree.lazyRemove(9);
		System.out.println("Removing value 9 from lazyTree. New max: " + lazyTree.findMax());

	}

	public void lazyRemove(E val) {
		root = lazyRemove(val, root);
		// If more than half of nodes are lazy deleted, truly delete them
		if (size > MIN && size < numDeleted) {
			root = trueDelete(root);
		}
	}

	// *****************************************************
	// 4.16 - Lazy Deletion on the binary search tree class
	// *****************************************************
	private MyTreeNode<E> lazyRemove(E val, MyTreeNode<E> curr) {
		if (curr == null)// not found
			return curr;

		int comparison = val.compareTo(curr.val);

		if (comparison < 0)// item is smaller than current
		{
			curr.left = lazyRemove(val, curr.left);
		} else if (comparison > 0) { // item is larger than current
			curr.right = lazyRemove(val, curr.right);
		} else if (!curr.isDeleted)// two children
		{
			curr.isDeleted = true;
			size--;
			numDeleted++;
		}
		return curr;
	}

	// **************************************************************************
	// Method to truly delete nodes when over half of the BST is lazily deleted
	// **************************************************************************
	private MyTreeNode<E> trueDelete(MyTreeNode<E> node) {
		if (node == null)
			return null;
		node.left = trueDelete(node.left);
		node.right = trueDelete(node.right);
		if (node.isDeleted) {
			if (node.left != null && node.right != null) {
				MyTreeNode<E> rightMin = node.right;
				MyTreeNode<E> rightMinParent = node;
				while (rightMin.left != null) {
					rightMinParent = rightMin;
					rightMin = rightMin.left;
				}
				node.val = rightMin.val;
				node.isDeleted = false;
				if (rightMinParent == node)
					rightMinParent.right = null;
				else
					rightMinParent.left = rightMin.right;
			} else
				node = node.left != null ? node.left : node.right;
		}
		return node;
	}

	// *************************************************************************
	// 4.41 - Modify a binary tree class we created, add a method to print all
	// the nodes in level order (root, all nodes at depth 1[root's children],
	// all nodes at depth 2, etc) Make sure the method runs in O(N), and show
	// that it does with comments about runtime at each step in your method.
	// **************************************************************************
	public void levelOrder(Search<E> search) {
		// If the tree is empty, do nothing
		if (root == null)
			return;
		ArrayDeque<MyTreeNode<E>> trees = new ArrayDeque<>(); 
		// Adds root to the ArrayDeque created above
		trees.offer(root); 
		// As long as the ArrayDeque is not empty
		while (!trees.isEmpty()) {
			// Tracks size of the ArrayDeque
			int size = trees.size();
			// For all values inside of queue
			for (int i = 0; i < size; i++) {
				// Retrieves and removes head of queue and sets to node
				MyTreeNode<E> node = trees.poll();
				search.s(node);
				// If node has a left, add to queue
				if (node.left != null)
					trees.offer(node.left);
				// If node has a right, add to queue
				if (node.right != null)
					trees.offer(node.right);

			}
		}
	}

	interface Search<E> {
		void s(MyTreeNode<E> node);
	}

	// ************************************************************************
	// 4.46 - Modify a binary tree class we created, add a method to see if two
	// binary trees are visually identical
	// *************************************************************************
	static boolean equalTrees(MyTreeNode leftSide, MyTreeNode rightSide) {
		// Empty trees are equal
		if (leftSide == null && rightSide == null)
			return true;

		// Empty tree is not equal to a non-empty one
		if ((leftSide == null && rightSide != null) || (leftSide != null && rightSide == null))
			return false;

		// otherwise check recursively
		return equalTrees(leftSide.left, rightSide.left) && equalTrees(leftSide.right, rightSide.right);
	}

	public boolean contains(E val) {
		return contains(val, root);
	}

	private boolean contains(E val, MyTreeNode<E> curr) {
		if (curr != null) {
			int compare = val.compareTo(curr.val);
			if (compare < 0) {
				return contains(val, curr.left);
			} else if (compare > 0) {
				return contains(val, curr.right);
			} else
				return !curr.isDeleted;
		}
		return false;
	}

	public E findMax() {
		if (root == null)
			return null;
		return findMax(root).val;
	}

	private MyTreeNode<E> findMax(MyTreeNode<E> t) {

		if (t == null)
			return null;
		MyTreeNode<E> tmp = findMax(t.right);
		if (tmp != null)
			return tmp;
		if (!t.isDeleted)
			return t;
		return findMax(t.left);
	}

	public E findMin() {
		if (root == null)
			return null;
		return findMin(root).val;
	}

	private MyTreeNode<E> findMin(MyTreeNode<E> t) {

		if (t == null)
			return null;
		MyTreeNode<E> tmp = findMin(t.left);
		if (tmp != null)
			return tmp;
		if (!t.isDeleted)
			return t;
		return findMin(t.right);
	}

	public void insert(E val) {
		root = insert(val, root);
	}

	private MyTreeNode<E> insert(E val, MyTreeNode<E> curr) {
		if (curr == null) {
			size++;
			return new MyTreeNode<E>(val);
		}
		int compare = val.compareTo(curr.val);
		if (compare > 0) {
			curr.right = insert(val, curr.right);
		} else if (compare < 0) {
			curr.left = insert(val, curr.left);
		} else if (curr.isDeleted) {
			curr.isDeleted = false;
			size++;
			numDeleted--;
		}
		return curr;
	}

	public void inOrderPrint() {
		inOrderPrintHelper(root);
		System.out.println();
	}

	private void inOrderPrintHelper(MyTreeNode<E> curr) {
		if (curr.left != null)
			inOrderPrintHelper(curr.left);
		System.out.print(curr.val + ",");
		if (curr.right != null)
			inOrderPrintHelper(curr.right);
	}

	private static class MyTreeNode<E> {
		E val, value;
		boolean isDeleted;
		MyTreeNode<E> left, right;

		public MyTreeNode(E v) {
			value = this.val;
			val = v;
			left = null;
			right = null;
			isDeleted = false;
		}
	}
}
