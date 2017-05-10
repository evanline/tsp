
package main;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author sanne
 *
 */
public class Scherm extends JFrame implements ActionListener {

	private JLabel algoritmeslbl;
	private JLabel aantalArtikelenlbl;
	private JLabel aantalSimulatieslbl; //
	private Checkbox bruteForceCKBX;
	private Checkbox twoOptCKBX;
	private Checkbox nearestNeighborCKBX;
	private Checkbox eigenAlgoritmeCKBX;
	private TextField aantaArtikelenlTXT;
	private TextField aantalSimulatiesTXT; //

	private JPanel algoritmesPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel aantalPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel knoppenPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel noordPNL = new JPanel(new BorderLayout());
	private JPanel zuidPNL = new JPanel(new BorderLayout());
	private JPanel totaalPNL = new JPanel(new BorderLayout());

	private JButton startBTN;
	private JButton pauzeBTN;
	private JButton resetBTN;
	private JButton volgendeBTN; //
	private	JButton vorigeBTN; //
	private JButton grafischemodusBTN;

	private JButton simulatiemodusBTN; //
	private boolean grafisch = true;

	private static int aantalArtikelen = 0;


	private int aantalSimulaties;
	private ArrayList<Checkbox> algoritmen = new ArrayList<>();

	enum Algoritmenenum {
		BRUTEFORCE, TWOOPT, NEARESTNEIGHBOR, EIGENALG
	}

	private ArrayList<Algoritmenenum> algoritmenArrayList = new ArrayList<>();

	Scherm() /* SK */ {
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


		algoritmen.add(bruteForceCKBX);
		algoritmen.add(twoOptCKBX);
		algoritmen.add(nearestNeighborCKBX);
		algoritmen.add(eigenAlgoritmeCKBX);
		// maak ckeckboxes en zet ze in een array

		// reset/start, berekenen, pauze/doorgaan
		startBTN = new JButton("Start");
		startBTN.addActionListener(this);
		pauzeBTN = new JButton("Pauze");
		pauzeBTN.addActionListener(this);
		resetBTN = new JButton("Reset");
		resetBTN.addActionListener(this);
		volgendeBTN = new JButton("Start");
		volgendeBTN.addActionListener(this);
		vorigeBTN = new JButton("Vorige");
		vorigeBTN.addActionListener(this);
		// maak knoppen
		aantaArtikelenlTXT = new TextField(2);
		aantalSimulatiesTXT = new TextField(2);
		// maak takstvelden

		grafischemodusBTN = new JButton("Grafische modus");
		grafischemodusBTN.addActionListener(this);
		simulatiemodusBTN = new JButton("Simulatiemodus");
		simulatiemodusBTN.addActionListener(this);
		//

		graphicmodusScherm();
		grafisch = true;
		// simulatiemodusScherm();
		// grafisch = false;
		setVisible(true);
	}

	private void graphicmodusScherm() /* SK */ {
		setTitle("Algoritmes voor TSP (Grafische modus)");
		System.out.println("graphic screen");
		grafisch = true;

		for (Checkbox c:algoritmen) {
			algoritmesPNL.add(c);
		}


		// zet alles van de algoritme regel in een panel
		aantalPNL.add(aantalArtikelenlbl);
		aantalPNL.add(aantaArtikelenlTXT);
		// zet alles van de aantalArtikelen regel in een panel

		// zet alles van de aninmatieregel in een panel
		knoppenPNL.add(volgendeBTN);
		knoppenPNL.add(vorigeBTN);
		knoppenPNL.add(resetBTN);
		// zet alles van de knoppenregel in een panel
		noordPNL.add(simulatiemodusBTN, BorderLayout.NORTH);
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

	private void simulatiemodusScherm() /* SK */ {

		setTitle("Algoritmes voor TSP (Simulatie modus)");
		System.out.println("simulatie scherm");
		grafisch = false;
		for (Checkbox c:algoritmen) {
			algoritmesPNL.add(c);
		}

		aantalPNL.add(aantalArtikelenlbl);
		aantalPNL.add(aantaArtikelenlTXT);
		aantalPNL.add(aantalSimulatieslbl);
		aantalPNL.add(aantalSimulatiesTXT);

		knoppenPNL.add(startBTN);
		knoppenPNL.add(pauzeBTN);
		knoppenPNL.add(resetBTN);

		noordPNL.add(grafischemodusBTN, BorderLayout.NORTH);
		noordPNL.add(algoritmesPNL, BorderLayout.SOUTH);
		zuidPNL.add(aantalPNL, BorderLayout.NORTH);
		zuidPNL.add(knoppenPNL, BorderLayout.SOUTH);
		// combineer de animatieregel en knoppenregel in een panel
		totaalPNL.add(noordPNL, BorderLayout.NORTH);
		totaalPNL.add(zuidPNL, BorderLayout.SOUTH);
		// combineer alle panels in een panel. congrats, you created a mecha
		getContentPane().add(totaalPNL);
	}

	private void cleanup() {
		noordPNL.removeAll();
		algoritmesPNL.removeAll();
		aantalPNL.removeAll();
		knoppenPNL.removeAll();
		noordPNL.removeAll();
		zuidPNL.removeAll();
		totaalPNL.removeAll();
		algoritmenArrayList.clear();
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
		} else {
			if (grafisch) {
				// het grafische scherm is geselecteerd
				if (e.getSource() == volgendeBTN && Objects.equals(volgendeBTN.getText(), "Start")) {
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
					GenereerCoordinaten coordinaat = new GenereerCoordinaten();

					if (bruteForceCKBX.getState()) {
						algoritmenArrayList.add(Algoritmenenum.BRUTEFORCE);
					}
					if (twoOptCKBX.getState()) {
						algoritmenArrayList.add(Algoritmenenum.TWOOPT);
					}
					if (nearestNeighborCKBX.getState()) {
						algoritmenArrayList.add(Algoritmenenum.NEARESTNEIGHBOR);
					}
					if (eigenAlgoritmeCKBX.getState()) {
						algoritmenArrayList.add(Algoritmenenum.EIGENALG);
					}

					for (Checkbox c:algoritmen) {
						c.setEnabled(false);
					}


/*volgende*/	} else if (e.getSource() == volgendeBTN && Objects.equals(volgendeBTN.getText(), "Volgende")) {
					System.out.println("volgende");
/*vorige*/		} else if (e.getSource() == vorigeBTN) {
					System.out.println("vorige");
/*reset*/		} else if (e.getSource() == resetBTN) {
					System.out.println("reset");
					volgendeBTN.setText("Start");
					for (Checkbox c:algoritmen) {
						c.setEnabled(true);
					}
					aantaArtikelenlTXT.setText("");
				} else {
					System.out.println("error: unknown source");
				}

			} else if (!grafisch) {
				// het simulatiescherm is geselecteerd.
				if (e.getSource() == startBTN) {
					System.out.println("start");

					  String aantal = aantaArtikelenlTXT.getText();
					  aantalArtikelen = 0; try { aantalArtikelen = Integer.parseInt(aantal); } catch (NumberFormatException e1) { aantaArtikelenlTXT.setText(""); }
					  System.out.println(aantalArtikelen);
					GenereerCoordinaten coordinaat = new GenereerCoordinaten();
					  String aantalSimul = aantalSimulatiesTXT.getText();
					  aantalSimulaties = 0; try { aantalSimulaties = Integer.parseInt(aantalSimul); } catch (NumberFormatException e1) { aantalSimulatiesTXT.setText("");}
					System.out.println(aantalSimulaties);

					if (bruteForceCKBX.getState()) {
						algoritmenArrayList.add(Algoritmenenum.BRUTEFORCE);
					}
					if (twoOptCKBX.getState()) {
						algoritmenArrayList.add(Algoritmenenum.TWOOPT);
					}
					if (nearestNeighborCKBX.getState()) {
						algoritmenArrayList.add(Algoritmenenum.NEARESTNEIGHBOR);
					}
					if (eigenAlgoritmeCKBX.getState()) {
						algoritmenArrayList.add(Algoritmenenum.EIGENALG);
					}

					  for (Checkbox c:algoritmen) {
						c.setEnabled(false);
					}
				} else if (e.getSource() == pauzeBTN) {
					System.out.println("pauze");
				} else if (e.getSource() == resetBTN) {
					System.out.println("reset");
					for (Checkbox c:algoritmen) {
						c.setEnabled(true);
					}
				} else {
					System.out.println("error: unknown source");
				}
			}
		}
	}

	public ArrayList<Algoritmenenum> getAlgoritmenArrayList() {
		return algoritmenArrayList;
	}

	public int getAantalSimulaties() {
		return aantalSimulaties;
	}
	public static int getAantalArtikelen() {
		return aantalArtikelen;
	}
}
