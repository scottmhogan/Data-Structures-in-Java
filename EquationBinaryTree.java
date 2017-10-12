//Scott Hogan
//Data Structures
//5:30-6:45 Tuesday-Thursday
//
//This is a program that creates 3 different binary trees.
//One of them is a tree that is populated with a prefix expression,
//one of them is a tree that is populated with an infix expression,
//and one of them is a tree that is populated with a postfix expression.
//They have a runtime of O(N). Each tree is then printed out in all three
//orders. Pre-order, in-order, and post-order.

//Sample infix, prefix, and postfix equations
//String infix = "((a+(b*c))+(((d*e)+f)*g))";
//String prefix = "-*9*56-+/892y";
//String postfix = "236*+92*8+0*+";

import java.util.Stack;
import java.util.Scanner;

public class EquationBinaryTree {

	private EquationBinaryTreeNode root;

	public EquationBinaryTree() {
		root = null;
	}

	//Tester class for Assignment 6
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Type keyword 'prefix', 'infix', or 'postfix'");
		String keyword = scan.nextLine();
		
		System.out.println("Type "+keyword+" equation here: ");
		String equation = scan.nextLine();
		
		if(keyword.equalsIgnoreCase("prefix"))
		{
			EquationBinaryTree etPrefix = new EquationBinaryTree();
			EquationBinaryTreeNode preRoot = etPrefix.populateFromPrefix(equation);
			
			System.out.println("**********************************");
			System.out.println("Prefix equation: " + equation);
			System.out.println();
			System.out.println("Prefix tree pre-order traversal: ");
			etPrefix.preOrder(preRoot);
			System.out.println();
			System.out.println();
			System.out.println("Prefix tree in-order traversal: ");
			etPrefix.inOrder(preRoot);
			System.out.println();
			System.out.println();
			System.out.println("Prefix tree post-order traversal: ");
			etPrefix.postOrder(preRoot);
			System.out.println();
			System.out.println("**********************************");
		}
		else if(keyword.equalsIgnoreCase("infix"))
		{
			EquationBinaryTree etInfix = new EquationBinaryTree();
			EquationBinaryTreeNode inRoot = etInfix.populateFromInfix(equation);
			
			System.out.println("**********************************");
			System.out.println("Infix equation: " + equation);
			System.out.println();
			System.out.println("Infix tree pre-order traversal: ");
			etInfix.preOrder(inRoot);
			System.out.println();
			System.out.println();
			System.out.println("Infix tree in-order traversal: ");
			etInfix.inOrder(inRoot);
			System.out.println();
			System.out.println();
			System.out.println("Infix tree post-order traversal: ");
			etInfix.postOrder(inRoot);
			System.out.println();
			System.out.println();
			System.out.println("**********************************");
		}
		else if(keyword.equalsIgnoreCase("postfix"))
		{
			EquationBinaryTree etPostfix = new EquationBinaryTree();
			char[] postfixArray = equation.toCharArray();
			EquationBinaryTreeNode postRoot = etPostfix.populateFromPostfix(postfixArray);
			
			System.out.println("*********************************");
			System.out.println("Postfix equation: " + equation);
			System.out.println();
			System.out.println("Postfix tree pre-order traversal: ");
			etPostfix.preOrder(postRoot);
			System.out.println();
			System.out.println();
			System.out.println("Postfix tree in-order traversal: ");
			etPostfix.inOrder(postRoot);
			System.out.println();
			System.out.println();
			System.out.println("Postfix tree post-order traversal: ");
			etPostfix.postOrder(postRoot);
			System.out.println();
			System.out.println("*********************************");
		}
		else
			System.out.println("Invalid equation or keyword");
	}

	// Print post-order traversal
	public void postOrder(EquationBinaryTreeNode node) { // O(N) Run time
		if (node == null)
			return;
		postOrder(node.left); // O(N)
		postOrder(node.right); // O(N)
		System.out.print(node.data); // O(1)
	}

	// Print in-order traversal
	public void inOrder(EquationBinaryTreeNode node) {// O(N) Total run time
		if (node == null)
			return;
		inOrder(node.left); // O(N)
		System.out.print(node.data);// O(1)
		inOrder(node.right);// O(N)
	}

	// Print pre-order traversal
	public void preOrder(EquationBinaryTreeNode node) {// O(N) Total run time
		if (node == null)
			return;
		System.out.print(node.data);// O(1)
		preOrder(node.left);// O(N)
		preOrder(node.right);// O(N)
	}

	
	public EquationBinaryTreeNode populateFromPrefix(String pre) // O(N) Total run time												
	{
		return root = populateFromPrefixHelper(pre); // time is O(N)
	}

	//---------------------------
	// Populate tree from prefix
	//---------------------------
	public EquationBinaryTreeNode populateFromPrefixHelper(String pre) // O(N) total run time
	{
		Stack<EquationBinaryTreeNode> stack = new Stack<>(); //O(1)
		for (int i = (pre.length() - 1); i >= 0; i--) // O(N)
		{
			char ch = pre.charAt(i); // o(1)
			if (isOperator(ch)) {

				EquationBinaryTreeNode node = new EquationBinaryTreeNode(null, ch, null);
				node.left = stack.pop();// O(1)
				node.right = stack.pop(); // O(1)
				stack.push(node); // O(1)
			} else {
				stack.add(new EquationBinaryTreeNode(null, ch, null)); // O(1)
			}
		}
		root = stack.pop(); // O(1)
		return root;
	}

	//----------------------------
	// Populate tree from postfix
	//----------------------------
	public EquationBinaryTreeNode populateFromPostfix(char postfix[]) { //O(N) total run time
		Stack<EquationBinaryTreeNode> stack = new Stack(); //O(1)
		EquationBinaryTreeNode t, t1, t2; //O(1)

		for (int i = 0; i < postfix.length; i++) { //O(N)
			if (!isOperator(postfix[i])) 
			{
				t = new EquationBinaryTreeNode(postfix[i]); //O(1)
				stack.push(t); //O(1)
			} else // Operator
			{
				t = new EquationBinaryTreeNode(postfix[i]); //O(1)
				
				// pop top two
				t1 = stack.pop(); //O(1)
				t2 = stack.pop(); //O(1)

				// create children
				t.right = t1; //O(1)
				t.left = t2; //O(1)

				stack.push(t); //O(1)
			}
		}

		t = stack.peek(); //O(1)
		stack.pop(); //O(1)
		return t;
	}

	//---------------------------
	// Populate tree from infix
	//---------------------------
	public EquationBinaryTreeNode populateFromInfix(String inf) { //O(N) total run time
		return root = populateFromInfixHelper(inf); //O(N)
	}

	private EquationBinaryTreeNode populateFromInfixHelper(String inf) { //O(N) total run time
		if (inf.length() == 1)
			return new EquationBinaryTreeNode(inf.charAt(0)); //O(1)

		String[] pieces = infixBreakdownHelper(inf);  //O(N)
		EquationBinaryTreeNode temp = new EquationBinaryTreeNode(pieces[1].charAt(0)); //O(1)
		temp.left = populateFromInfixHelper(pieces[0]); //O(N)
		temp.right = populateFromInfixHelper(pieces[2]);//O(N)
		return temp;
	}

	private String[] infixBreakdownHelper(String inf) { //O(N) total runtime
		inf = inf.substring(1, inf.length() - 1);
		int open = 0; //O(1)
		int pos = 0; //O(1)
		for (; pos < inf.length(); pos++) {//O(N)
			if (inf.charAt(pos) == '(')
				open++;
			else if (inf.charAt(pos) == ')')
				open--;
			if (open == 0)
				break;
		}
		pos++; //O(1)
		return new String[] { inf.substring(0, pos), "" + inf.charAt(pos), inf.substring(pos + 1) };
	}

	public char[] toCharArray(String s) {
		char[] array = new char[s.length()];
		for (int i = 0; i < s.length(); i++) {
			array[i] = s.charAt(i);
		}
		return array;
	}

	public boolean isOperator(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/') {
			return true;
		}
		return false;
	}

	private class EquationBinaryTreeNode {
		char data;
		EquationBinaryTreeNode left;
		EquationBinaryTreeNode right;

		public EquationBinaryTreeNode(char d) {
			data = d;
			left = null;
			right = null;
		}

		// Overload for EquationBinaryTreeNode with 3 parameters
		public EquationBinaryTreeNode(EquationBinaryTreeNode left, char v, EquationBinaryTreeNode right)
		{
			left = null;
			right = null;
			data = v;
		}

		public String toString() {
			return "" + data;
		}
	}
}
