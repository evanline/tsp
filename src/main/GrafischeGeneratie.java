package main;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by: Sanne Klaassen
 * Date: 15/05/17.
 */
public class GrafischeGeneratie extends JPanel {
	private static final int PREF_W = 800; //breedte tenzij anders nodig
	private static final int PREF_H = 800; //hoogte tenzij anders nodig
	private static final int BORDER_GAP = 50; //nodig bij schaal berekening ed.
	private static final Color GRAPH_COLOR1 = new Color(11, 180, 7, 255); //kleur lijnen
	private static final Color GRAPH_COLOR2 = new Color(119, 0, 9, 247);
	private static final Color GRAPH_POINT_COLOR = new Color(0, 0, 0, 148); //kleur punten
	private static final Stroke GRAPH_STROKE = new BasicStroke(3f); //vorm lijn(breedte lijn)
	private static final int GRAPH_POINT_WIDTH = 12; //grootte punten
	private ArrayList<Integer[]> coordinaat;
	private Integer xScale = 175;
	private Integer yScale = 175;
	private int stapnummer = 0;
	private ArrayList<ArrayList<Integer[]>> allemogelijkheden = new ArrayList<>();

	GrafischeGeneratie(ArrayList<Integer[]> coordinaat, int stapnummer, ArrayList<ArrayList<Integer[]>> allemogelijkheden) {
		this.coordinaat = coordinaat;
		this.stapnummer = stapnummer;
		this.allemogelijkheden = allemogelijkheden;
		if (stapnummer > coordinaat.size()){
			this.stapnummer = coordinaat.size();
		}
	}

	private Graphics2D g2;

	@Override
	protected void paintComponent(Graphics g) {
		//idk, ziet er noodzakelijk uit.
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setBackground(Color.darkGray);

		//schaal om te vermenigvuldigen, omdat het anders te klein wordt
		Integer x_HATCH_CNT = 5;
		xScale = (getWidth() - 2 * BORDER_GAP) / (x_HATCH_CNT);
		Integer MAX_SCORE = 5;
		yScale = (getHeight() - 2 * BORDER_GAP) / (MAX_SCORE);

		// create x and y axes
		g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);    //y-as

		g2.drawLine(getWidth() - BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);
		g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);  //x-as

		g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

		// create hatch marks for y axis.
		Integer y_HATCH_CNT = 5;
		for (int i = 0; i < y_HATCH_CNT; i++) {
			int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
			int x2 = GRAPH_POINT_WIDTH - (BORDER_GAP / 4);
			int y0 = (getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / y_HATCH_CNT + BORDER_GAP));
			g2.drawLine(BORDER_GAP, y0, x1, y0);

			String i1 = Integer.toString( ((i + 1) * (MAX_SCORE / y_HATCH_CNT)));
			g2.drawString(i1, x2, y0);
		}

		// and for x axis
		for (int i = 0; i < x_HATCH_CNT; i++) {

			int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (x_HATCH_CNT) + BORDER_GAP;
			int y0 = getHeight() - BORDER_GAP;
			int y1 = y0 - GRAPH_POINT_WIDTH;
			int y2 = y0 + (GRAPH_POINT_WIDTH);
			g2.drawLine(x0, y0, x0, y1);

			String i1 = Integer.toString(i + 1);
			g2.drawString(i1, x0, y2);
		}

		List<Point> graphpoints = maakGrafiekpunten(coordinaat);
		List<Point> b = null;
		if (stapnummer < 1) {
			int e = getHeight() - BORDER_GAP;
			b = new ArrayList<>();
			b.add(new Point(BORDER_GAP, e));
		} else if (stapnummer - 1 < allemogelijkheden.size()) {
			b = maakGrafiekpunten(allemogelijkheden.get(stapnummer - 1));
		}

		int x1;
		int y1;
		if (stapnummer < 2) {
			x1 = BORDER_GAP;
			y1 = getHeight() - BORDER_GAP;
		} else if (stapnummer > graphpoints.size()) {
			int a = graphpoints.size() - 1;
			x1 = graphpoints.get(a).x;
			y1 = graphpoints.get(a).y;
		} else {
			x1 = graphpoints.get(stapnummer - 1).x;
			y1 = graphpoints.get(stapnummer - 1).y;
		}


		Stroke oldStroke = g2.getStroke();
		g2.setStroke(GRAPH_STROKE);
		g2.setColor(GRAPH_COLOR2);
		tekenlijn(b, x1, y1);
		g2.setColor(GRAPH_COLOR1);
		tekenlijnGoed(graphpoints);
		g2.setStroke(oldStroke);
		g2.setColor(GRAPH_POINT_COLOR);
		tekenpunten(graphpoints);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(PREF_W, PREF_H);
	}


	private java.util.List<Point> maakGrafiekpunten(ArrayList<Integer[]> a) {
		java.util.List<Point> graphPoints = new ArrayList<>();
		for (Integer[] e : a) {
			int x1 = (e[0] * xScale + BORDER_GAP);
			int y1 = ((5 - e[1]) * yScale + BORDER_GAP);
			graphPoints.add(new Point(x1, y1));
		}
		return graphPoints;
	}
/*
	private void tekenlijnGoed(List<Point> gp) {
		int x1;
		int y1;
		if (stapnummer < 1) {
			x1 = BORDER_GAP;
			y1 = getHeight() - BORDER_GAP;
		} else if (stapnummer >= gp.size()) {
			int a = gp.size() - 1;
			x1 = gp.get(a).x;
			y1 = gp.get(a).y;
		} else {
			x1 = gp.get(stapnummer).x;
			y1 = gp.get(stapnummer).y;
		}
			int x2 = gp.get(stapnummer).x;
			int y2 = gp.get(stapnummer).y;

			g2.drawLine(x1, y1, x2, y2);

	}*/


	private void tekenlijnGoed(List<Point> gp) {
		System.out.println("teken lijn " + stapnummer);
		for (int i = 0; i < stapnummer - 1; i++) {
			int x1 = gp.get(i).x;
			int y1 = gp.get(i).y;
			int x2 = gp.get(i + 1).x;
			int y2 = gp.get(i + 1).y;
			g2.drawLine(x1, y1, x2, y2);
		}
	}

	private void tekenpunten(java.util.List<Point> graphPoints) {
		for (Point graphPoint : graphPoints) {
			int x = graphPoint.x - GRAPH_POINT_WIDTH / 2;
			int y = graphPoint.y - GRAPH_POINT_WIDTH / 2;
			g2.fillOval(x, y, GRAPH_POINT_WIDTH, GRAPH_POINT_WIDTH);
		}
	}

	private void tekenlijn(List<Point> graphPoints, int a, int b) {
		for (Point graphPoint : graphPoints) {
			int x2 = graphPoint.x;
			int y2 = graphPoint.y;

			g2.drawLine(a, b, x2, y2);
		}
	}
}
