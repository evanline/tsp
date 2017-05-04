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

import javax.swing.ButtonGroup;
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

	JLabel algoritmeslbl;
	JLabel aantallbl;
	JLabel animatieslbl;
	
	Checkbox bruteForceCKBX;
	Checkbox twoOptCKBX;
	Checkbox nearestNeighborCKBX;
	Checkbox eigenAlgoritmeCKBX; 
	
	JRadioButton animatiesAANRBTN;
	JRadioButton animatiesUITRBTN;
	ButtonGroup animatieBTNGRP;
	JButton startBTN;
	JButton pauzeBTN;
	JButton resetBTN;
	TextField aantalTXT;
	
	
	
	public Scherm() /*SK*/ { 
			setTitle("Algoritmes voor TSP");
			setSize(800, 600);
			setLayout(new FlowLayout());
			getContentPane().setBackground(Color.gray);
			//standaard noodzakelijke instellingen
			
			algoritmeslbl = new JLabel("Algoritmes");
			aantallbl = new JLabel("Aantal");
			animatieslbl = new JLabel("Animaties");
			//maak labels			
			bruteForceCKBX = new Checkbox("Brute Force");
			twoOptCKBX = new Checkbox("2-Opt");
			nearestNeighborCKBX = new Checkbox("Nearest Neighbor");
			eigenAlgoritmeCKBX = new Checkbox("Eigen Algoritme");
			//maak ckeckboxes
			animatiesAANRBTN = new JRadioButton("aan");
			animatiesUITRBTN = new JRadioButton("uit");
			//maak radiobuttons
			animatieBTNGRP = new ButtonGroup();
			animatieBTNGRP.add(animatiesAANRBTN);
			animatieBTNGRP.add(animatiesUITRBTN);
			//maak een buttongroup en zet de animatieaan/uit buttons erin
			//reset/start, berekenen, pauze/doorgaan
			startBTN = new JButton("Start");
			pauzeBTN = new JButton("Pauze");
			resetBTN = new JButton("Reset");
			//maak knoppen
			aantalTXT = new TextField(2);
			//maak takstvelden
			
			JPanel algoritmesPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel aantalPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel animatiesPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel knoppenPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel settingPNL = new JPanel(new BorderLayout());
			JPanel startPNL = new JPanel(new BorderLayout());
			JPanel totaalPNL = new JPanel(new BorderLayout());
			//maak jpanels
			
			algoritmesPNL.add(algoritmeslbl);
			algoritmesPNL.add(bruteForceCKBX);
			algoritmesPNL.add(twoOptCKBX);
			algoritmesPNL.add(nearestNeighborCKBX);
			algoritmesPNL.add(eigenAlgoritmeCKBX);
			//zet alles van de algoritme regel in een panel
			aantalPNL.add(aantallbl);
			aantalPNL.add(aantalTXT);
			//zet alles van de aantal regel in een panel
			animatiesPNL.add(animatieslbl);
			animatiesPNL.add(animatiesAANRBTN);
			animatiesPNL.add(animatiesUITRBTN);
			//zet alles van de aninmatieregel in een panel
			knoppenPNL.add(startBTN);
			startBTN.addActionListener(this);
			knoppenPNL.add(pauzeBTN);
			startBTN.addActionListener(this);
			knoppenPNL.add(resetBTN);
			startBTN.addActionListener(this);
			//zet alles van de knoppenregel in een panel
			settingPNL.add(algoritmesPNL, BorderLayout.NORTH);
			settingPNL.add(aantalPNL, BorderLayout.SOUTH);
			//combineer de algoritmeregel en de aantalregel in een panel
			startPNL.add(animatiesPNL, BorderLayout.NORTH);
			startPNL.add(knoppenPNL, BorderLayout.SOUTH);
			//combineer de animatieregel en knoppenregel in een panel
			totaalPNL.add(settingPNL, BorderLayout.NORTH);
			totaalPNL.add(startPNL, BorderLayout.SOUTH);
			//combineer alle panels in een panel. congrats, you created a mecha
			getContentPane().add(totaalPNL);
			setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		if(e.getSource() == startBTN){
//			
//		}
	}

}
