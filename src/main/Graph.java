package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Created by: Sanne Klaassen
 * Date: 09/05/17.
 *
 * basis voor code gecopieerd van: (TODO: in verslag)
 * http://stackoverflow.com/questions/8693342/drawing-a-simple-line-graph-in-java
 */
@SuppressWarnings("serial")
public class Graph  extends JPanel{
	private int MAX_SCORE = 1; //hoogste getal op de y-as.
	private static final int PREF_W = 800; //breedte tenzij anders nodig
	private static final int PREF_H = 650; //hoogte tenzij anders nodig //TODO: maak dit statisch? (ik kijk wel als dit in scherm staat wat handig is)
	private static final int BORDER_GAP = 30; //nodig bij schaal berekening ed.
	private static final Color GRAPH_COLOR = new Color(11, 177, 7,180); //kleur lijnen
	private static final Color GRAPH_POINT_COLOR = new Color(65, 7, 9, 180); //kleur punten
	private static final Stroke GRAPH_STROKE = new BasicStroke(3f); //vorm lijn(breedte lijn)
	private static final int GRAPH_POINT_WIDTH = 12; //grootte punten
	private static final int Y_HATCH_CNT = 40; //hoeveelheid streepjes op y-as
	private List<Integer> yAs; //lijst getallen op de y-as

	public Graph(List<Integer> yAs) {
		this.yAs = yAs;
		for (Integer i : yAs){
			i++;
			if ( i > MAX_SCORE){
				MAX_SCORE = i;
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		//idk, ziet er noodzakelijk uit.
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		//schaal om te vermenigvuldigen, omdat het anders te klein wordt
		double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (yAs.size() - 1);
		double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE);

		//maak een lijst punten voor op de grafiek
		List<Point> graphPoints = new ArrayList<>();
		for (int i = 0; i < yAs.size(); i++) {
			int x1 = (int) (i * xScale + BORDER_GAP);
			int y1 = (int) ((MAX_SCORE - yAs.get(i)) * yScale + BORDER_GAP);
			graphPoints.add(new Point(x1, y1));
		}


		// create x and y axes //todo: zorg ervoor dat alle punten erop komen, ook 0.
		g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);	//y-as
		g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);  //x-as

		// create hatch marks for y axis.
		for (int i = 0; i < Y_HATCH_CNT; i++) {
			int x0 = BORDER_GAP;
			int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
			int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
			int y1 = y0;
			g2.drawLine(x0, y0, x1, y1);
		}

		// and for x axis
		for (int i = 0; i < yAs.size() - 1; i++) {
			int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (yAs.size() - 1) + BORDER_GAP;
			int x1 = x0;
			int y0 = getHeight() - BORDER_GAP;
			int y1 = y0 - GRAPH_POINT_WIDTH;
			g2.drawLine(x0, y0, x1, y1);
		}

		//teken de lijnen
		Stroke oldStroke = g2.getStroke();
		g2.setColor(GRAPH_COLOR);
		g2.setStroke(GRAPH_STROKE);
		for (int i = 0; i < graphPoints.size() - 1; i++) {
			int x1 = graphPoints.get(i).x;
			int y1 = graphPoints.get(i).y;
			int x2 = graphPoints.get(i + 1).x;
			int y2 = graphPoints.get(i + 1).y;
			g2.drawLine(x1, y1, x2, y2);
		}

		//teken de stippen
		g2.setStroke(oldStroke);
		g2.setColor(GRAPH_POINT_COLOR);
		for (int i = 0; i < graphPoints.size(); i++) {
			int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
			int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
			int ovalW = GRAPH_POINT_WIDTH;
			int ovalH = GRAPH_POINT_WIDTH;
			g2.fillOval(x, y, ovalW, ovalH);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(PREF_W, PREF_H);
	}

	public static void createAndShowGui(List<Integer> yAs) {


		Graph mainPanel = new Graph(yAs);
		//grafiek staat momenteel in een eigen window. TODO: grafiek in scherm zetten. (bovenstaande functie)
		JFrame frame = new JFrame("DrawGraph");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}
}
