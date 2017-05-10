package main;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanne Klaassen & Ian Hildebrand
 *
 */
public class Main {

	public static void main(String[] args) {
		Scherm scherm = new Scherm();
		scherm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		ArrayList<Order> orders;
		orders = XMLReaderDOM.run();
		assert orders != null : "No orders found!";
		System.out.println(new NearestNeighbor());
		System.out.println(new NearestNeighbor().getTotalDistance());







	}

	
}
