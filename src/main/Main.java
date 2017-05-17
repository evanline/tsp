package main;

import javax.swing.*;

/**
 * @author Sanne Klaassen & Ian Hildebrand
 *
 */
public class Main {
	public static void main(String[] args)
	{
		Scherm scherm = new Scherm();
		scherm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		System.out.println(new TwoOpt(new GenereerCoordinaten().getLijstCoordinaten()));
	}
}
