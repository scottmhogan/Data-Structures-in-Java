
//Scott Hogan
//Data Structures
//5:30-6:45

import java.util.Scanner;

public class InfixPostfix {
	private String equation;
	
	// Constructor for InfixPostfix.java
	public InfixPostfix(String infixExpression) {
		equation = infixExpression;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter keyword 'infix' to convert to postfix");
		System.out.println("Enter keyword 'postfix' to convert to infix");
		String type = scan.nextLine();
		if (type.equalsIgnoreCase("infix")) {
			System.out.println("Please enter infix equation: ");
			String equation = scan.nextLine();
			InfixPostfix convert = new InfixPostfix(equation);
			System.out.println("Your equation in postfix is: " + convert.infixToPostfix());
		} else if (type.equalsIgnoreCase("postfix")) {
			System.out.println("Please enter postfix equation: ");
			String equation = scan.nextLine();
			InfixPostfix convert = new InfixPostfix(equation);
			System.out.println("Your equation in infix is: " + convert.postfixToInfix());
		} else
			System.out.println("Please type infix or postfix. Terminating");
	}

	// Method used for postfixToInfix to see if the current value is an operator
	private boolean isOperator(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/')
			return true;
		else
			return false;
	}

	// Bonus to convert postfix to infix
	public String postfixToInfix() {
		// Generic error handling
		//String stack is used for converting postfix to infix
		MyStack<String> stringStack = new MyStack<>();
		try {
			// Scans through the length of the expression the user typed in
			for (int i = 0; i < equation.length(); i++) {
				// saves each individual char for the expression that the user
				// typed in
				char c = equation.charAt(i);
				// If operator, pop the top 2, save them into values a and b,
				// and push them onto a string stack
				if (isOperator(c)) {
					String b = stringStack.pop();
					String a = stringStack.pop();
					stringStack.push("(" + a + c + b + ")");
				} else
					stringStack.push("" + c);
			}
			return stringStack.pop();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//Bonus to convert infix to postfix
	public String infixToPostfix() {
		//Character stack is used for converting infix to postfix
		MyStack<Character> expressionStack = new MyStack<Character>();
		
		// Error handling for invalid equations with generic catch included too
		try {
			String pf = "";
			for (int index = 0; index < equation.length(); ++index) {
				char i = equation.charAt(index);
				// Push opening parenthesis to stack
				if (i == '(') {
					expressionStack.push('(');
				} else if (i == ')') {
					// Look at top of stack
					Character oper = expressionStack.peek();
					// While top of stack is not ( and not empty, pop the top,
					// add the current charValue to the postfix string
					while (!(oper.equals('(')) && !(expressionStack.isEmpty())) {
						expressionStack.pop();
						pf += oper.charValue();
						if (!expressionStack.isEmpty())
							oper = expressionStack.peek();
					}
					expressionStack.pop();
				}
				// Check for + or - for precedence handling
				else if (i == '+' || i == '-') {
					if (expressionStack.isEmpty()) {
						expressionStack.push(i);
					} else {
						Character oper = expressionStack.peek();
						while (!(expressionStack.isEmpty() || oper.equals(('(')) || oper.equals((')')))) {
							oper = expressionStack.pop();
							pf += oper.charValue();
						}
						expressionStack.push(i);
					}
				}
				// Check for * or / for precedence handling
				else if (i == '*' || i == '/') {
					if (expressionStack.isEmpty()) {
						expressionStack.push(i);
					} else {
						Character oper = expressionStack.peek();
						while (!oper.equals(('(')) && !oper.equals(('+')) && !oper.equals(('-'))
								&& !expressionStack.isEmpty()) {
							oper = expressionStack.pop();
							pf += oper.charValue();
						}
						expressionStack.push(i);
					}
				} else {
					pf += i;
				}
			}

			while (!expressionStack.isEmpty()) {
				Character oper = expressionStack.peek();
				if (!oper.equals(('('))) {
					expressionStack.pop();
					pf += oper.charValue();
				}
			}
			return pf;
		} catch (NullPointerException n) {
			System.out.println("NullPointerException, see stack trace below. Possibly an unbalanced equation.");
			n.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
