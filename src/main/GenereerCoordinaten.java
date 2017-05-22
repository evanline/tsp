package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * Created by: Sanne Klaassen & Ian Hildebrand
 * Date: 08/05/17.
 */

class GenereerCoordinaten {
	private ArrayList<Integer[]> lijstCoordinaten = new ArrayList<>();

	GenereerCoordinaten()
	{
		ArrayList<Integer[]> hashes = new ArrayList<>();
		int aantal = Scherm.getAantalArtikelen();
		if (aantal <= 0) aantal = 9;
		int max = 6;
		if (aantal > Math.pow(max, 2)) max = (int) Math.sqrt(aantal)+1;
		Boolean dubbelecoordinaat;

		while (lijstCoordinaten.size() < aantal) {
			dubbelecoordinaat = false;
			Random rand = new Random();
			Integer x = rand.nextInt(max);
			Integer y = rand.nextInt(max);
			Integer[] hashbaar = {x, y};

			for (Integer[] h : hashes)
			{
				if (Objects.equals(hashbaar[0], h[0]) && Objects.equals(hashbaar[1], h[1]))
				{
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