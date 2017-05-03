/**
 * TODO: 
 * zorgen dat aniaties aan/uit buttons niet tegelijk aan kunnen en dat als je de een selecteerd, de andere uit gaat
 * als de animaties aan staan, button 'start' 'stop' laten weergeven en als de animatie klaar is, 'reset' laten weergeven
 */
package main;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * @author sanne
 *
 */
public class Scherm extends JFrame implements ActionListener{

	/**
	 * 
	 */
	public Scherm() /*SK*/ { 
			setTitle("Algoritmes voor TSP");
			setSize(800, 600);
			setLayout(new FlowLayout());
			getContentPane().setBackground(Color.gray);
			
			JLabel algoritmeslbl = new JLabel("Algoritmes");
			JLabel aantallbl = new JLabel("Aantal");
			JLabel animatieslbl = new JLabel("Animaties");
						
			Checkbox bruteForceCKBX = new Checkbox("Brute Force");
			Checkbox twoOptCKBX = new Checkbox("2-Opt");
			Checkbox nearestNeighborCKBX = new Checkbox("Nearest Neighbor");
			Checkbox eigenAlgoritmeCKBX = new Checkbox("Eigen Algoritme");
			
			JRadioButton animatiesAANRBTN = new JRadioButton("aan");
			JRadioButton animatiesUITRBTN = new JRadioButton("uit");
			
			//reset/start, berekenen, pauze/doorgaan
			JButton startBTN = new JButton("Start");
			JButton pauzeBTN = new JButton("Pauze");
			JButton resetBTN = new JButton("Reset");
			
			
			TextField aantalTXT = new TextField(2);
			
			JPanel algoritmesPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel aantalPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel animatiesPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel knoppenPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel borderPnl = new JPanel(new BorderLayout());
			
			
			algoritmesPNL.add(algoritmeslbl);
			algoritmesPNL.add(bruteForceCKBX);
			algoritmesPNL.add(twoOptCKBX);
			algoritmesPNL.add(nearestNeighborCKBX);
			algoritmesPNL.add(eigenAlgoritmeCKBX);
			
			
			
			add(aantallbl);
			add(aantalTXT);
			
			add(animatieslbl);
			add(animatiesAANRBTN);
			add(animatiesUITRBTN);
			add(startBTN);
			add(pauzeBTN);
			add(resetBTN);
			
			borderPnl.add(algoritmesPNL, BorderLayout.NORTH);
			borderPnl.add(knoppenPNL, BorderLayout.SOUTH);
//			getContentPane().add(algoritmesPNL);
			setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
