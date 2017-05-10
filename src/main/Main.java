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
		// TODO Auto-generated method stub
		Scherm scherm = new Scherm();
		scherm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		XMLReaderDOM.run();
//

/*		ArrayList<Order> orders;
		orders = XMLReaderDOM.run();
		assert orders != null : "No orders found!";
*/
		List<Integer> yAs = new ArrayList<Integer>();
		for (Integer i = 0; i < 40; i++){

			yAs.add(i);
		}
		System.out.println(yAs.size());
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Graph.createAndShowGui(yAs);
			}
		});

	}

	
}
