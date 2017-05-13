package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Sanne Klaassen
 * Date: 13/05/17.
 */
public class Legenda extends JPanel {
	private Checkbox cbx1;
	private Checkbox cbx2;
	private Checkbox cbx3;
	private Checkbox cbx4;
	private static final int PREF_W = 800; //breedte tenzij anders nodig
	private static final int PREF_H = 70; //hoogte tenzij anders nodig
	
	private List<Color> kleuren = new ArrayList<>();


	public Legenda(Checkbox cbx1, Checkbox cbx2, Checkbox cbx3, Checkbox cbx4) {
		this.cbx1 = cbx1;
		this.cbx2 = cbx2;
		this.cbx3 = cbx3;
		this.cbx4 = cbx4;

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		List<String> namen = new ArrayList<>();
		if(cbx1.getState()){
			String naam1 = "Brute Force";
			Color graphcolor1 = Graph.getGraphColor1();
			namen.add(naam1);
			kleuren.add(graphcolor1);
		}
		if(cbx2.getState()){
			String naam2 = "2-Opt";
			Color graphcolor2 = Graph.getGraphColor2();
			namen.add(naam2);
			kleuren.add(graphcolor2);
		}
		if(cbx3.getState()){
			String naam3 = "Nearest Neighbor";
			Color graphcolor3 = Graph.getGraphColor3();
			namen.add("hoi?");
			kleuren.add(graphcolor3);
		}
		if(cbx4.getState()){
			String naam4 = "Eigen Algoritme";
			Color graphcolor4 = Graph.getGraphColor4();
			namen.add(naam4);
			kleuren.add(graphcolor4);
		}

		for (int i = 0; i < namen.size(); i++){
			g.setColor(Color.BLACK);
			g.drawString(namen.get(i), 0, 30); //TODO: de coordinaten kloppen voor geen pixel...

			g.setColor(kleuren.get(i));
			int x = 90;
			if (i > 2){
				x = 0;
			}
			int y = 30;
			if (i%2 == 0){
				y = 60;
			}
			g.fillRect(x,y,20,20);
		}
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(PREF_W, PREF_H);
	}
}
