//Scott Hogan
//Data Structures
//5:30-6:45

import java.util.ArrayList;
import java.util.Scanner;

public class Josephus {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter N for N players");
		int n = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter M for M passes");
		int m = scan.nextInt();
		scan.nextLine();
		hotPotato(n, m);
	}

	public static int hotPotato(int n, int m) {
		int x = 0;
		ArrayList<Integer> players = new ArrayList<Integer>(n);
		if (n < 1) {
			System.out.println("No players to play with");
			return 0;
		} else {
			for (int i = 1; i <= n; i++) {
				players.add(i);
			}
			System.out.println(players);
			while (players.size() > 1) {
				x = (x + m) % players.size();
				System.out.print(players.get(x) + " ");
				players.remove(x);
			}
			System.out.println();
			System.out.println("Survivor = " + players.get(0));
			return players.get(0);
		}
	}
}
