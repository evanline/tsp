package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by: Sanne Klaassen
 * Date: 08/05/17.
 */

class GenereerCoordinaten {
	private ArrayList<Integer[]> lijstCoordinaten = new ArrayList<>();
	private HashSet<Integer> hashbaar = new HashSet<>();
	private ArrayList<HashSet> hashes = new ArrayList<>();

	GenereerCoordinaten() {
//	aantal = Scherm.getAantalArtikelen();
		int aantal = 3;
		int max = 6;
		int min = 1;
		Boolean dubbelecoordinaat = false;

		while (lijstCoordinaten.size() < aantal) {
			Random rand = new Random();
			Integer x = rand.nextInt(max - min + 1) + min;
			Integer y = rand.nextInt(max - min + 1) + min;
			hashbaar.add(x);
			hashbaar.add(y);

			for (HashSet h : hashes) {
				if (hashbaar == h) {
					dubbelecoordinaat = true;
				}
			}
			if (!dubbelecoordinaat) {
				lijstCoordinaten.add(new Integer[] {x,y});
			}
		}
	}
	ArrayList<Integer[]> getLijstCoordinaten() {
		return lijstCoordinaten;
	}
}
