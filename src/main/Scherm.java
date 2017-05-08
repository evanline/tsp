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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * @author sanne
 *
 */
public class Scherm extends JFrame implements ActionListener {

	JLabel algoritmeslbl;
	JLabel aantalArtikelenlbl;
	JLabel aantalSimulatieslbl; //
	Checkbox bruteForceCKBX;
	Checkbox twoOptCKBX;
	Checkbox nearestNeighborCKBX;
	Checkbox eigenAlgoritmeCKBX;
	TextField aantaArtikelenlTXT;
	TextField aantalSimulatiesTXT; //

	JPanel algoritmesPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel aantalPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel knoppenPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel noordPNL = new JPanel(new BorderLayout());
	JPanel zuidPNL = new JPanel(new BorderLayout());
	JPanel totaalPNL = new JPanel(new BorderLayout());

	JButton startBTN;
	JButton pauzeBTN;
	JButton resetBTN;
	JButton volgendeBTN; //
	JButton vorigeBTN; //
	JButton grafischemodusBTN;

	JButton simulatiemodusBTN; //
	boolean grafisch = true;
	
	boolean bruteForce = false;
	boolean twoOpt = false;
	boolean nearestNeighbor = false;
	boolean eigenAlg = false;

	private int aantalArtikelen;
	private int aantalSimulaties;
	
	ArrayList<Boolean> algoritmen;


	public Scherm() /* SK */ {
		setTitle("Algoritmes voor TSP");
		setSize(800, 600);
		setLayout(new FlowLayout());
		getContentPane().setBackground(Color.gray);
		// standaard noodzakelijke instellingen

		algoritmeslbl = new JLabel("Algoritmes");
		aantalArtikelenlbl = new JLabel("Aantal");
		aantalSimulatieslbl = new JLabel("Aantal Simulaties");
		// maak labels
		bruteForceCKBX = new Checkbox("Brute Force");
		twoOptCKBX = new Checkbox("2-Opt");
		nearestNeighborCKBX = new Checkbox("Nearest Neighbor");
		eigenAlgoritmeCKBX = new Checkbox("Eigen Algoritme");
		// maak ckeckboxes

		// reset/start, berekenen, pauze/doorgaan
		startBTN = new JButton("Start");
		pauzeBTN = new JButton("Pauze");
		resetBTN = new JButton("Reset");
		volgendeBTN = new JButton("Start");
		vorigeBTN = new JButton("Vorige");
		// maak knoppen
		aantaArtikelenlTXT = new TextField(2);
		aantalSimulatiesTXT = new TextField(2);
		// maak takstvelden

		grafischemodusBTN = new JButton("Grafische modus");
		simulatiemodusBTN = new JButton("Simulatiemodus");
		//

		graphicmodusScherm();
		// simulatiemodusScherm();

		setVisible(true);
	}

	public void graphicmodusScherm() /* SK */ {
		setTitle("Algoritmes voor TSP (Grafische modus)");
		System.out.println("graphic screen");
		grafisch = true;

		algoritmesPNL.add(algoritmeslbl);
		algoritmesPNL.add(bruteForceCKBX);    
		algoritmesPNL.add(twoOptCKBX);
		algoritmesPNL.add(nearestNeighborCKBX);
		algoritmesPNL.add(eigenAlgoritmeCKBX);
		// zet alles van de algoritme regel in een panel
		aantalPNL.add(aantalArtikelenlbl);
		aantalPNL.add(aantaArtikelenlTXT);
		// zet alles van de aantalArtikelen regel in een panel

		// zet alles van de aninmatieregel in een panel
		knoppenPNL.add(volgendeBTN);
		volgendeBTN.addActionListener(this);
		knoppenPNL.add(vorigeBTN);
		vorigeBTN.addActionListener(this);
		knoppenPNL.add(resetBTN);
		resetBTN.addActionListener(this);
		// zet alles van de knoppenregel in een panel
		noordPNL.add(simulatiemodusBTN, BorderLayout.NORTH);
		simulatiemodusBTN.addActionListener(this);
		noordPNL.add(algoritmesPNL, BorderLayout.SOUTH);

		// combineer de algoritmeregel en de aantalregel in een panel
		zuidPNL.add(aantalPNL, BorderLayout.NORTH);
		zuidPNL.add(knoppenPNL, BorderLayout.SOUTH);
		// combineer de animatieregel en knoppenregel in een panel
		totaalPNL.add(noordPNL, BorderLayout.NORTH);
		totaalPNL.add(zuidPNL, BorderLayout.SOUTH);
		// combineer alle panels in een panel. congrats, you created a mecha
		getContentPane().add(totaalPNL);
	}

	public void simulatiemodusScherm() /* SK */ {

		setTitle("Algoritmes voor TSP (Simulatie modus)");
		System.out.println("simulatie scherm");
		grafisch = false;

		algoritmesPNL.add(algoritmeslbl);
		algoritmesPNL.add(bruteForceCKBX);
		algoritmesPNL.add(twoOptCKBX);
		algoritmesPNL.add(nearestNeighborCKBX);
		algoritmesPNL.add(eigenAlgoritmeCKBX);

		aantalPNL.add(aantalArtikelenlbl);
		aantalPNL.add(aantaArtikelenlTXT);
		aantalPNL.add(aantalSimulatieslbl);
		aantalPNL.add(aantalSimulatiesTXT);

		knoppenPNL.add(startBTN);
		startBTN.addActionListener(this);
		knoppenPNL.add(pauzeBTN);
		pauzeBTN.addActionListener(this);
		knoppenPNL.add(resetBTN);
		resetBTN.addActionListener(this);

		noordPNL.add(grafischemodusBTN, BorderLayout.NORTH);
		grafischemodusBTN.addActionListener(this);
		noordPNL.add(algoritmesPNL, BorderLayout.SOUTH);
		zuidPNL.add(aantalPNL, BorderLayout.NORTH);
		zuidPNL.add(knoppenPNL, BorderLayout.SOUTH);
		// combineer de animatieregel en knoppenregel in een panel
		totaalPNL.add(noordPNL, BorderLayout.NORTH);
		totaalPNL.add(zuidPNL, BorderLayout.SOUTH);
		// combineer alle panels in een panel. congrats, you created a mecha
		getContentPane().add(totaalPNL);
	}

	public void cleanup() {
		noordPNL.removeAll();
		algoritmesPNL.removeAll();
		aantalPNL.removeAll();
		knoppenPNL.removeAll();
		noordPNL.removeAll();
		zuidPNL.removeAll();
		totaalPNL.removeAll();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println(e.getSource());
		if (e.getSource() == simulatiemodusBTN || e.getSource() == grafischemodusBTN) {

			if (e.getSource() == simulatiemodusBTN) {
				cleanup();
				getContentPane().remove(totaalPNL);
				getContentPane().revalidate();
				getContentPane().repaint();
				simulatiemodusScherm();
			}
			if (e.getSource() == grafischemodusBTN) {
				cleanup();
				getContentPane().remove(totaalPNL);
				getContentPane().revalidate();
				getContentPane().repaint();
				graphicmodusScherm();
			}
		}  else {
			if (grafisch) {
				// het grafische scherm is geselecteerd
				if (e.getSource() == volgendeBTN && volgendeBTN.getText() == "Start") {
					System.out.println("Start");
					volgendeBTN.setText("Volgende");
					
					String aantal = aantaArtikelenlTXT.getText();
					aantalArtikelen = 0;
					try {
						aantalArtikelen = Integer.parseInt(aantal);
					} catch (NumberFormatException e1) {
						aantaArtikelenlTXT.setText("");
					}
					System.out.println(aantalArtikelen);
				
					Boolean bruteForce = bruteForceCKBX.getState();
					///algoritmen.add(bruteForce);
					Boolean TwoOpt = twoOptCKBX.getState();
					//algoritmen.add(TwoOpt);
					Boolean nearestNeighbor = nearestNeighborCKBX.getState();
					//algoritmen.add(nearestNeighbor);
					Boolean eigenAlg = eigenAlgoritmeCKBX.getState();
					//algoritmen.add(eigenAlg);		
					
					

				} else if (e.getSource() == volgendeBTN && volgendeBTN.getText() == "Volgende") {
					System.out.println("volgende");
				} else if (e.getSource() == vorigeBTN) {
					System.out.println("vorige");
				} else if (e.getSource() == resetBTN) {
					System.out.println("reset");
					volgendeBTN.setText("Start");
				} else {
					System.out.println("error: unknown source");
				}

			} else {
				// het simulatiescherm is geselecteerd.
				if (e.getSource() == startBTN) {
			/*		System.out.println("start");

					String aantal = aantaArtikelenlTXT.getText();
					aantalArtikelen = 0;
					try {
						aantalArtikelen = Integer.parseInt(aantal);
					} catch (NumberFormatException e1) {
						aantaArtikelenlTXT.setText("");
					}
					System.out.println(aantalArtikelen);
*/
				} else if (e.getSource() == pauzeBTN) {
					System.out.println("pauze");
				} else if (e.getSource() == resetBTN) {
					System.out.println("reset");
				} else {
					System.out.println("error: unknown source");
				}
			}
		}
	}

}
