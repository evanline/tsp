package main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Sanne Klaassen & Ian Hildebrand
 *
 */
public class Main {
	public static void main(String[] args)
	{
		Scherm hoofdscherm = new Scherm();
		hoofdscherm.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		hoofdscherm.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				hoofdscherm.setVisible(false);
				System.out.println("test 1");
				new Paasei().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			}
		});
	/*	if (!hoofdscherm.isVisible()){
			System.out.println("test 2");

		}*/
	}
}
