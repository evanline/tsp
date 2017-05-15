package main;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Sanne Klaassen & Ian Hildebrand
 *
 */
public class Main {
	public static void main(String[] args) {
		Scherm scherm = new Scherm();
		scherm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		ArrayList<Order> orderList;
		XMLReaderDOM orders1 = new XMLReaderDOM("src/main/bestelling.xml");
		XMLReaderDOM orders2 = new XMLReaderDOM();
		orderList = orders1.getOrderList();

		BruteForce b = new BruteForce();
		ArrayList<Integer[]> a = b.getPath();

		for (Integer[] i : a)
		{
			System.out.println(Arrays.toString(i));
		}

		System.out.println(b.getRunTime());
		System.out.println(b.getTotalDistance());
		System.out.println(Brutal.getCount());
	}
}
