/**
 * 
 */
package main;

import javax.swing.*;

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
	}

	
}
