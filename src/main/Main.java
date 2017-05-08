package main;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Sanne Klaassen & Ian Hildebrand
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scherm scherm = new Scherm();
		scherm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		XMLReaderDOM.run();
		GenereerCoordinaten coordinaat = new GenereerCoordinaten();

		ArrayList<Order> orders;
		orders = XMLReaderDOM.run();
		assert orders != null : "No orders found!";


	}

	
}
