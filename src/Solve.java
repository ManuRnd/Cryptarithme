import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Solve {
	static int nbSolution = 0;
	static int max = 0;

	public static void main(String[] args) {

		int nbMot;
		Set<Character> lettres = new TreeSet<>();
		Set<Character> premiers = new TreeSet<>();
		Map<Character, Integer> values = new HashMap<>();

		Scanner sc = new Scanner(System.in);
		nbMot = sc.nextInt();// nombre de mots total
		sc.nextLine();// changement de ligne

		String[] mots = new String[nbMot];// tableau de mots
		// enregistrement des mots
		for (int i = 0; i < nbMot; i++) {
			mots[i] = sc.nextLine();
			// nombre de caractere max
			if (mots[i].length() > max) {
				max = mots[i].length();
			}
		}
		int[] val = new int[10];

		for (String word : mots) {
			premiers.add(word.charAt(0));
			for (char c : word.toCharArray()) {
				lettres.add(c);
			}
		}
		Character lettre[] = new Character[lettres.size()];
		lettres.toArray(lettre);
		testAll(lettre, values, 0, premiers, mots, val);
		System.out.println(nbSolution);

	} // fin main

	public static boolean solve(String[] mots, Map<Character, Integer> values) {
		int[][] operandes = new int[mots.length][max];
		int add = 0;
		int carry = 0;
		int valide = 0;

		// init du tableau
		for (int i = 0; i < mots.length; i++) {
			for (int y = 0; y < max; y++) {
				operandes[i][y] = 0;
			} // fin for longueur mots
		} // fin for mots

		// enregistrement des mots dans un talbeau de tableau pour l'addition
		for (int i = 0; i < mots.length; i++) {
			for (int y = 0; y < mots[i].length(); y++) {
				operandes[i][y] = values.get(mots[i].charAt(mots[i].length() - 1 - y));
			} // fin for longueur mots
		} // fin for mots
		
		// addition des termes sans le dernier mots
		for (int y = 0; y < max; y++) {
			for (int i = 0; i < mots.length - 1; i++) {
				add += operandes[i][y];
			}
			// fin i
			add+=carry;
			if ( (add % 10) == operandes[mots.length - 1][y]) {
				valide++;
				if(add!=0)carry = add / 10;
				else carry=0;
			} // fin if comparaison addition
			add = 0;

		} // fin y
	
		return valide == max && carry==0;
		
		
	}// fin solve

	public static void testAll(Character[] lettre, Map<Character, Integer> values, int n, Set<Character> premiers,
			String[] mots, int[] val) {
		Character key;
		Integer va;

		if (n == lettre.length) {
			if (solve(mots, values) == true) {
				nbSolution++;
			}
			return;
		}

		if (premiers.contains(lettre[n])) {
			outer: for (int i = 1; i < 10; i++) {
				for (int j = 0; j < n; j++) {
					if (val[j] == i) {
						continue outer;
					}
				}
				values.put(lettre[n], i);
				val[n] = i;
				testAll(lettre, values, n + 1, premiers, mots, val);
			}
		} else {
			outer: for (int i = 0; i < 10; i++) {
				for (int j = 0; j < n; j++) {
					if (val[j] == i) {
						continue outer;
					}
				}
				values.put(lettre[n], i);
				val[n] = i;
				testAll(lettre, values, n + 1, premiers, mots, val);
			}
		}
	}// fin testAll
}// fin class
