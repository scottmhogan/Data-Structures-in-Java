//Scott Hogan
//Data Structures
//5:30-6:45

import java.util.*;
import java.util.TreeMap;

public class MyTreeMap {

	public static void main(String[] args) {

		String goOn = "y";
		String name;
		int vowel = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("Tree Map for Names.");
		TreeMap<String, Integer> treeName = new TreeMap<>();
		while (goOn.equalsIgnoreCase("y")) {
			System.out.println("\nPlease enter a name.");
			name = scan.next();
			name = name.toLowerCase();

			for (int i = 0; i < name.length(); i++) {
				char l = name.charAt(i);

				switch (l) {
				case 'a':
					vowel++;
					break;
				case 'e':
					vowel++;
					break;
				case 'i':
					vowel++;
					break;
				case 'o':
					vowel++;
					break;
				case 'u':
					vowel++;
					break;
				}
				treeName.put(name, vowel);
			}
			vowel = 0;
			System.out.println("Would you like to enter another name? (y/n) \n");
			goOn = scan.next();
		}
		for (String k : treeName.keySet()) {
			System.out.println("Name: " + k + " - Number of Vowels: " + treeName.get(k));
		}
	}
}
