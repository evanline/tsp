package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by: Sanne Klaassen
 * Date: 08/05/17.
 */

class GenereerCoordinaten {
	private int aantal;
	private ArrayList<Integer[]> lijstCoordinaten = new ArrayList<>();
	private HashSet<Integer> hashbaar = new HashSet<>();
	private ArrayList<HashSet> hashes = new ArrayList<>();

	public GenereerCoordinaten() {
//	aantal = Scherm.getAantalArtikelen();
		aantal = 3;
		Boolean dubbelecoordinaat = false;

		while (lijstCoordinaten.size() < aantal) {
			Random rand = new Random();
			Integer x = rand.nextInt(6);
			x++;
			Integer y = rand.nextInt(6);
			y++;
			hashbaar.add(x);
			hashbaar.add(y);

			for (HashSet h : hashes) {
				if (hashbaar == h) {
					dubbelecoordinaat = true;
				}
			}
			if (dubbelecoordinaat == false) {
				lijstCoordinaten.add(new Integer[] {x,y});
				System.out.print(x + ", ");
				System.out.print(y);
				System.out.println("");
			}
		}
	}
	public ArrayList<Integer[]> getLijstCoordinaten() {
		return lijstCoordinaten;
	}
}

