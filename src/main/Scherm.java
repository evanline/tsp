
package main;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/*
 * Created by: Sanne Klaassen
 */
public class Scherm extends JFrame implements ActionListener {

	private JLabel aantalArtikelenlbl;
	private JLabel aantalSimulatieslbl; //
	private JLabel padlengte = new JLabel("Padlengte:");
	private JLabel berekentijd = new JLabel("Berekentijd (miliseconden):");
	private JLabel legenda = new JLabel("Legenda: ");
	private Checkbox bruteForceCKBX;
	private Checkbox twoOptCKBX;
	private Checkbox nearestNeighborCKBX;
	private Checkbox eigenAlgoritmeCKBX;
	private TextField aantaArtikelenlTXT;
	private TextField aantalSimulatiesTXT; //

	private JPanel algoritmesPNL = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel aantalPNL = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel knoppenPNL = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel noordPNL = new JPanel(new BorderLayout());
	private JPanel zuidPNL = new JPanel(new BorderLayout());
	private JPanel totaalPNL = new JPanel(new BorderLayout());
	private JPanel grafiekPNL = new JPanel(new BorderLayout());
	private JPanel padgrafiekPNL = new JPanel(new BorderLayout());
	private JPanel tijdgrafiekPNL = new JPanel(new BorderLayout());
	private JPanel legendaPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel eersteGrafischePanelPNL = new JPanel(new BorderLayout());
	private JPanel tweedeGrafischePanelPNL = new JPanel(new BorderLayout());
	private JPanel derdeGrafischePanelPNL = new JPanel(new BorderLayout());
	private JPanel nogEenPanelWantDatWasWatIkNodigHadInMijnLevenPNL = new JPanel(new BorderLayout());
	private JPanel ditIsEchtDeLaatstePanelPNL = new JPanel(new BorderLayout());
	private JPanel neeEchtPNL = new JPanel(new BorderLayout());

	private JButton startBTN;
	private JButton pauzeBTN;
	private JButton resetBTN;
	private JButton volgendeBTN; //
	private JButton vorigeBTN; //
	private JButton grafischemodusBTN;

	private JButton simulatiemodusBTN; //
	private boolean grafisch = true;

	private static int aantalArtikelen = 0;
	NearestNeighbor nearestNeighbor;
	BruteForce bruteForce;
	TwoOpt twoOpt;
	EigenAlgoritme eigenAlgoritme;

	private int aantalSimulaties;
	private ArrayList<Checkbox> algoritmen = new ArrayList<>();

	enum Algoritmenenum {
		BRUTEFORCE, TWOOPT, NEARESTNEIGHBOR, EIGENALG
	}

	private ArrayList<Algoritmenenum> algoritmenArrayList = new ArrayList<>();

	static ArrayList<ArrayList<Integer[]>> lijst1;



	GrafischeGeneratie grafiesch1;
	GrafischeGeneratie grafiesch2;
	GrafischeGeneratie grafiesch3;
	GrafischeGeneratie grafiesch4;
	int stapnummer = 0;
//	Double totaleRekentijd1;
//	Double totaleRekentijd2;
//	Double totaleRekentijd3;
//	Double totaleRekentijd4;

//	Graph padlengteGraph;
//	Graph berekentijdGraph;

	Scherm() /* SK */ {
		setTitle("Algoritmes voor TSP");
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setLayout(new FlowLayout());
		getContentPane().setBackground(Color.gray);
		// standaard noodzakelijke instellingen

//		JLabel algoritmeslbl = new JLabel("Algoritmes");
		aantalArtikelenlbl = new JLabel("Aantal Artikelen");
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

		for (Checkbox c : algoritmen) {
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

		nogEenPanelWantDatWasWatIkNodigHadInMijnLevenPNL.add(eersteGrafischePanelPNL, BorderLayout.WEST);
		nogEenPanelWantDatWasWatIkNodigHadInMijnLevenPNL.add(tweedeGrafischePanelPNL, BorderLayout.EAST);
		ditIsEchtDeLaatstePanelPNL.add(nogEenPanelWantDatWasWatIkNodigHadInMijnLevenPNL, BorderLayout.WEST);
		ditIsEchtDeLaatstePanelPNL.add(derdeGrafischePanelPNL, BorderLayout.EAST);

		// combineer alle panels in een panel.
		neeEchtPNL.add(ditIsEchtDeLaatstePanelPNL, BorderLayout.SOUTH);
		neeEchtPNL.add(totaalPNL, BorderLayout.NORTH);
		getContentPane().add(neeEchtPNL);
	}

	private void simulatiemodusScherm() /* SK */ {

		setTitle("Algoritmes voor TSP (Simulatie modus)");
		System.out.println("simulatie scherm");
		grafisch = false;
		for (Checkbox c : algoritmen) {
			algoritmesPNL.add(c);
		}

		aantalPNL.add(aantalArtikelenlbl);
		aantalPNL.add(aantaArtikelenlTXT);
		aantalPNL.add(aantalSimulatieslbl);
		aantalPNL.add(aantalSimulatiesTXT);

		knoppenPNL.add(startBTN);
//		knoppenPNL.add(pauzeBTN);
		knoppenPNL.add(resetBTN);

		noordPNL.add(grafischemodusBTN, BorderLayout.NORTH);
		noordPNL.add(algoritmesPNL, BorderLayout.SOUTH);
		zuidPNL.add(aantalPNL, BorderLayout.NORTH);
		zuidPNL.add(knoppenPNL, BorderLayout.SOUTH);
		// combineer de animatieregel en knoppenregel in een panel
		totaalPNL.add(noordPNL, BorderLayout.NORTH);
		totaalPNL.add(zuidPNL, BorderLayout.SOUTH);

		ditIsEchtDeLaatstePanelPNL.add(legendaPNL, BorderLayout.NORTH);
		ditIsEchtDeLaatstePanelPNL.add(grafiekPNL, BorderLayout.SOUTH);

		nogEenPanelWantDatWasWatIkNodigHadInMijnLevenPNL.add(totaalPNL, BorderLayout.NORTH);
		nogEenPanelWantDatWasWatIkNodigHadInMijnLevenPNL.add(ditIsEchtDeLaatstePanelPNL, BorderLayout.SOUTH);

		getContentPane().add(nogEenPanelWantDatWasWatIkNodigHadInMijnLevenPNL);
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
		grafiekPNL.removeAll();
		nogEenPanelWantDatWasWatIkNodigHadInMijnLevenPNL.removeAll();
		ditIsEchtDeLaatstePanelPNL.removeAll();
		legendaPNL.removeAll();
		eersteGrafischePanelPNL.removeAll();
		tweedeGrafischePanelPNL.removeAll();
		derdeGrafischePanelPNL.removeAll();
		neeEchtPNL.removeAll();
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
/*start*/                // het grafische scherm is geselecteerd
				ArrayList<Integer[]> coordinates = new GenereerCoordinaten().getLijstCoordinaten();
				if (e.getSource() == volgendeBTN && Objects.equals(volgendeBTN.getText(), "Start")) {
					System.out.println("Start");
					Boolean geenSelectie = true;

					for (Checkbox c : algoritmen) {
						if (c.getState()) {
							geenSelectie = false;
						}
					}
					if (!geenSelectie) {
						volgendeBTN.setText("Volgende");

						String aantal = aantaArtikelenlTXT.getText();
						aantalArtikelen = 0;
						try {
							aantalArtikelen = Integer.parseInt(aantal);
						} catch (NumberFormatException e1) {
							aantaArtikelenlTXT.setText("");
						}
						System.out.println(aantalArtikelen);
						tekendingenpofzo(coordinates);

						for (Checkbox c : algoritmen) {
							c.setEnabled(false);
						}
					}
/*volgende*/
				} else if (e.getSource() == volgendeBTN && Objects.equals(volgendeBTN.getText(), "Volgende")) {
					System.out.println("volgende");
					stapnummer++;

					tekendingenpofzo(coordinates);
/*vorige*/
				} else if (e.getSource() == vorigeBTN) {
					System.out.println("vorige");
					if (stapnummer != 0) {
						stapnummer--;
						tekendingenpofzo(coordinates);
					}
/*reset*/
				} else if (e.getSource() == resetBTN) {
					System.out.println("reset");
					coordinates = new GenereerCoordinaten().getLijstCoordinaten();
					volgendeBTN.setText("Start");
					for (Checkbox c : algoritmen) {
						c.setEnabled(true);
					}
					aantaArtikelenlTXT.setText("");
					aantalArtikelen = 0;
					//	cleanup();
					eersteGrafischePanelPNL.removeAll();
					tweedeGrafischePanelPNL.removeAll();
					derdeGrafischePanelPNL.removeAll();
					getContentPane().revalidate();
					getContentPane().repaint();

				} else {
					System.out.println("error: unknown source");
				}

			} else if (!grafisch) {
				// het simulatiescherm is geselecteerd.
				if (e.getSource() == startBTN) {
					System.out.println("start");

					Boolean geenAlgoritme = true;
					for (Checkbox c : algoritmen) {
						c.setEnabled(false);
						if (c.getState()) {
							geenAlgoritme = false;
						}
					}
					if (!geenAlgoritme) {
						startBTN.setEnabled(false);
						String aantal = aantaArtikelenlTXT.getText();
						aantalArtikelen = 0;
						try {
							aantalArtikelen = Integer.parseInt(aantal);
						} catch (NumberFormatException e1) {
							aantaArtikelenlTXT.setText("");
						}
						System.out.println("Aantal Artikelen: " + aantalArtikelen);

						String aantalSimul = aantalSimulatiesTXT.getText();
						aantalSimulaties = 0;
						try {
							aantalSimulaties = Integer.parseInt(aantalSimul);
						} catch (NumberFormatException e1) {
							aantalSimulatiesTXT.setText("");
						}
						System.out.println("aantal simulaties: " + aantalSimulaties);

						aantalSimulatiesTXT.setEnabled(false);
						aantaArtikelenlTXT.setEnabled(false);
						grafiekPNL.removeAll();
						legendaPNL.removeAll();

						legendaPNL.add(legenda);
						Legenda legendaOBJ = new Legenda(bruteForceCKBX, twoOptCKBX, nearestNeighborCKBX, eigenAlgoritmeCKBX);
						legendaPNL.add(legendaOBJ);
						maakgrafieken();

//		SwingUtilities.invokeLater(() -> Graph.createAndShowGui(pathlengthes1));


						getContentPane().remove(nogEenPanelWantDatWasWatIkNodigHadInMijnLevenPNL);
						getContentPane().revalidate();
						getContentPane().repaint();
						getContentPane().add(nogEenPanelWantDatWasWatIkNodigHadInMijnLevenPNL);
					}

				} else if (e.getSource() == resetBTN) {
					System.out.println("reset");
					for (Checkbox c : algoritmen) {
						c.setEnabled(true);
					}
					startBTN.setEnabled(true);
					legendaPNL.removeAll();
					aantalSimulaties = 0;
					aantalSimulatiesTXT.setText("");
					aantalArtikelen = 0;
					aantaArtikelenlTXT.setText("");
					aantalSimulatiesTXT.setEnabled(true);
					aantaArtikelenlTXT.setEnabled(true);
					grafiekPNL.removeAll();
					padgrafiekPNL.removeAll();
					tijdgrafiekPNL.removeAll();
					getContentPane().revalidate();
					getContentPane().repaint();
				} else {
					System.out.println("error: unknown source");
				}
			}
		}
	}

	private void tekendingenpofzo(ArrayList<Integer[]> coordinates) {
		System.out.println(stapnummer + ", checkpoint");
		eersteGrafischePanelPNL.removeAll();
		tweedeGrafischePanelPNL.removeAll();
		derdeGrafischePanelPNL.removeAll();
		//maak panels leeg want anders werkt dit maar 1 keer ivm graphics
		if (bruteForceCKBX.getState()) {
			algoritmenArrayList.add(Algoritmenenum.BRUTEFORCE);
			//TODO: dit uit grafisch halen want niemand wil een paar honderd duizend keer op een knop klikken als het geen cookie clicker is.
		}
		if (twoOptCKBX.getState()) {
			System.out.println("nog een checkpoint");
			algoritmenArrayList.add(Algoritmenenum.TWOOPT);
			if (stapnummer == 0) {
				twoOpt = new TwoOpt(new ArrayList<>(coordinates));
			}//als je hier komt omdat je op start geklikt hebt, maak dan een object van het algoritme aan
			int alg = 2; //want dit is het tweede algoritme uit de lijst
			grafiesch2 = new GrafischeGeneratie(twoOpt.getPath(), stapnummer, alg);
			JLabel l = new JLabel("2-Opt");
			eersteGrafischePanelPNL.add(l, BorderLayout.NORTH);
			eersteGrafischePanelPNL.add(grafiesch2, BorderLayout.SOUTH);
		}
		if (nearestNeighborCKBX.getState()) {
			algoritmenArrayList.add(Algoritmenenum.NEARESTNEIGHBOR);
			if (stapnummer == 0) {
				nearestNeighbor = new NearestNeighbor(new ArrayList<>(coordinates)); //TODO hier
			}
			lijst1 = nearestNeighbor.getARRAYSEPTION();
			int alg = 3;
			grafiesch3 = new GrafischeGeneratie(nearestNeighbor.getPath(), stapnummer, alg);
			JLabel l = new JLabel("Nearest Neighbor");
			tweedeGrafischePanelPNL.add(l, BorderLayout.NORTH);
			tweedeGrafischePanelPNL.add(grafiesch3, BorderLayout.SOUTH);
		}
		if (eigenAlgoritmeCKBX.getState()) {
			algoritmenArrayList.add(Algoritmenenum.EIGENALG);
			if (stapnummer == 0) {
				eigenAlgoritme = new EigenAlgoritme(new ArrayList<>(coordinates)); //TODO hier
			}
			JLabel l = new JLabel("Eigen Algoritme");
			//			grafiesch4 = new GrafischeGeneratie(eigenAlgoritme.getPath(), stapnummer, eigenAlgoritme.getARRAYSEPTION());
			derdeGrafischePanelPNL.add(l, BorderLayout.NORTH);
			derdeGrafischePanelPNL.add(grafiesch4, BorderLayout.SOUTH);
		}

		eersteGrafischePanelPNL.revalidate();
		eersteGrafischePanelPNL.repaint();
		tweedeGrafischePanelPNL.revalidate();
		tweedeGrafischePanelPNL.repaint();
		derdeGrafischePanelPNL.revalidate();
		derdeGrafischePanelPNL.repaint();
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	private void maakgrafieken() {
		List<Double> pathlengthes1 = new ArrayList<>();
		List<Double> pathlengthes2 = new ArrayList<>();
		List<Double> pathlengthes3 = new ArrayList<>();
		List<Double> pathlengthes4 = new ArrayList<>();
		List<Double> tijden1 = new ArrayList<>();
		List<Double> tijden2 = new ArrayList<>();
		List<Double> tijden3 = new ArrayList<>();
		List<Double> tijden4 = new ArrayList<>();
		ArrayList<ArrayList<Integer[]>> coordinateslist = new ArrayList<>();
		for (int i = 0; i < aantalSimulaties; i++) {
			ArrayList<Integer[]> coordinates = new GenereerCoordinaten().getLijstCoordinaten();
			coordinateslist.add(coordinates);
		}
		if (bruteForceCKBX.getState()) {
			algoritmenArrayList.add(Algoritmenenum.BRUTEFORCE);
			for (int i = 0; i < aantalSimulaties + 1; i++) {
				if (!(i == 0)) {
					BruteForce j = new BruteForce(new ArrayList<>(coordinateslist.get(i - 1))); //TODO hier

					pathlengthes1.add(j.getTotalDistance());
					tijden1.add(j.getRunTime());
					//		totaleRekentijd1 = totaleRekentijd1 + (j.getRunTime());
				} else {
					pathlengthes1.add(0.0);
					tijden1.add(0.0);
				}
			}
		}
		if (twoOptCKBX.getState()) {
			algoritmenArrayList.add(Algoritmenenum.TWOOPT);
			for (int i = 0; i < aantalSimulaties + 1; i++) {
				if (!(i == 0)) {
					TwoOpt j = new TwoOpt(new ArrayList<>(coordinateslist.get(i - 1)));//TODO hier

					pathlengthes2.add(j.getTotalDistance());
					tijden2.add(j.getRunTime());
					//		totaleRekentijd2 = totaleRekentijd2 + (j.getRunTime());
				} else {
					pathlengthes2.add(0.0);
					tijden2.add(0.0);
				}
			}
		}
		if (nearestNeighborCKBX.getState()) {
			//	algoritmenArrayList.add(Algoritmenenum.NEARESTNEIGHBOR);
			for (int i = 0; i < aantalSimulaties + 1; i++) {
				if (!(i == 0)) {
					NearestNeighbor j = new NearestNeighbor(new ArrayList<>(coordinateslist.get(i - 1)));//TODO hier

					pathlengthes3.add(j.getTotalDistance());
					tijden3.add(j.getRunTime());
//									totaleRekentijd3 = totaleRekentijd3 + (j.getRunTime()); //TODO
				} else {
					pathlengthes3.add(0.0);
					tijden3.add(0.0);
				}
			}
		}
		if (eigenAlgoritmeCKBX.getState()) {
			algoritmenArrayList.add(Algoritmenenum.EIGENALG);
			for (int i = 0; i < aantalSimulaties + 1; i++) {
				if (!(i == 0)) {
					EigenAlgoritme j = new EigenAlgoritme(new ArrayList<>(coordinateslist.get(i - 1)));//TODO hier

					pathlengthes4.add(j.getTotalDistance());
					tijden4.add(j.getRunTime());
					//		totaleRekentijd4 = totaleRekentijd4 + (j.getRunTime());
				} else {
					pathlengthes4.add(0.0);
					tijden4.add(0.0);
				}
			}
		}

		Graph padlengteGraph = new Graph(pathlengthes1, pathlengthes2, pathlengthes3, pathlengthes4);
		padgrafiekPNL.add(padlengte);
		padgrafiekPNL.add(padlengteGraph, BorderLayout.EAST);
		grafiekPNL.add(padgrafiekPNL, BorderLayout.WEST);
		Graph berekentijdGraph = new Graph(tijden1, tijden2, tijden3, tijden4);
		tijdgrafiekPNL.add(berekentijd);
		tijdgrafiekPNL.add(berekentijdGraph, BorderLayout.EAST);
		grafiekPNL.add(tijdgrafiekPNL, BorderLayout.EAST);
	}


	public ArrayList<Algoritmenenum> getAlgoritmenArrayList() {
		return algoritmenArrayList;
	}

	public int getAantalSimulaties() {
		return aantalSimulaties;
	}

	static int getAantalArtikelen() {
		return aantalArtikelen;
	}

}
