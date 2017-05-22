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

	GenereerCoordinaten()
	{
		int aantal = Scherm.getAantalArtikelen();
		if (aantal <= 0) aantal = 9;
		int max = 5;
		if (aantal > Math.pow(max, 2)) max = (int) Math.sqrt(aantal)+1;

		Boolean dubbelecoordinaat = false;

		while (lijstCoordinaten.size() < aantal) {
			HashSet<Integer> hashbaar = new HashSet<>();

			Random rand = new Random();
			Integer x = rand.nextInt(max);
			Integer y = rand.nextInt(max);
			hashbaar.add(x);
			hashbaar.add(y);


			ArrayList<HashSet> hashes = new ArrayList<>();
			for (HashSet h : hashes) {
				if (hashbaar == h) {
					dubbelecoordinaat = true;
				}
			}
			if (!dubbelecoordinaat) {
				lijstCoordinaten.add(new Integer[] {x,y});
				hashes.add(hashbaar);
			}
		}
	}

	ArrayList<Integer[]> getLijstCoordinaten() {
		return lijstCoordinaten;
	}
}