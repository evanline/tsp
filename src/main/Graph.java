package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Sanne Klaassen
 * Date: 09/05/17.
 * <p>
 * basis voor code gecopieerd van: (TODO: in verslag)
 * http://stackoverflow.com/questions/8693342/drawing-a-simple-line-graph-in-java
 */
public class Graph extends JPanel {
	private static Graphics2D g2;
	private double MAX_SCORE = 1; //hoogste getal op de y-as.
	private static final int PREF_W = 800; //breedte tenzij anders nodig
	private static final int PREF_H = 650; //hoogte tenzij anders nodig
	private static final int BORDER_GAP = 30; //nodig bij schaal berekening ed.
	private static final Color GRAPH_COLOR1 = new Color(11, 177, 7, 180); //kleur lijnen
	private static final Color GRAPH_COLOR2 = new Color(130, 79, 0, 180);
	private static final Color GRAPH_COLOR3 = new Color(0, 118, 158, 180);
	private static final Color GRAPH_COLOR4 = new Color(79, 42, 80, 180);
	private static final Color GRAPH_POINT_COLOR = new Color(0, 0, 0, 52); //kleur punten
	private static final Stroke GRAPH_STROKE = new BasicStroke(3f); //vorm lijn(breedte lijn)
	private static final int GRAPH_POINT_WIDTH = 12; //grootte punten
	private Double Y_HATCH_CNT = 40.00; //hoeveelheid streepjes op y-as
	private List<Double> algoritme1;
	private List<Double> algoritme2;
	private List<Double> algoritme3;
	private List<Double> algoritme4;//lijst getallen op de y-as
	private double xScale;
	private double yScale;
	private int MAX_SIZE = 1;
	public static Color getGraphColor1() {
		return GRAPH_COLOR1;
	}

	public static Color getGraphColor2() {
		return GRAPH_COLOR2;
	}

	public static Color getGraphColor3() {
		return GRAPH_COLOR3;
	}

	public static Color getGraphColor4() {
		return GRAPH_COLOR4;
	}

	Graph(List<Double> algoritme1, List<Double> algoritme2, List<Double> algoritme3, List<Double> algoritme4) {
		this.algoritme1 = algoritme1;
		int size1 = 1;
		if (algoritme1.isEmpty()) {
			algoritme1.add(0.0);
		} else {
			size1 = algoritme1.size();
		}
		for (double i : algoritme1) {
			if (i > MAX_SCORE) {
				MAX_SCORE = i;
			}
		}
		this.algoritme2 = algoritme2;
		int size2 = 1;
		if (algoritme2.isEmpty()) {
			algoritme2.add(0.0);
		} else {
			size2 = algoritme2.size();
		}
		for (double i : algoritme2) {
			if (i > MAX_SCORE) {
				MAX_SCORE = i;
			}
		}
		this.algoritme3 = algoritme3;
		int size3 = 1;
		if (algoritme3.isEmpty()) {
			algoritme3.add(0.0);
		} else {
			size3 = algoritme3.size();
		}
		for (double i : algoritme3) {
			if (i > MAX_SCORE) {
				MAX_SCORE = i;
			}
		}
		this.algoritme4 = algoritme4;
		int size4 = 1;
		if (algoritme4.isEmpty()) {
			algoritme4.add(0.0);
		} else {
			size4 = algoritme4.size();
		}
		for (double i : algoritme4) {
			if (i > MAX_SCORE) {
				MAX_SCORE = i;
			}
		}

		if (MAX_SCORE > 6) {
			Y_HATCH_CNT = (MAX_SCORE / 2) + 1;
		} else {
			Y_HATCH_CNT = 5.0;
		}
		MAX_SIZE = Math.max(size1, size2);
		MAX_SIZE = Math.max(MAX_SIZE, size3);
		MAX_SIZE = Math.max(MAX_SIZE, size4);
	}



	@Override
	protected void paintComponent(Graphics g) {
		//idk, ziet er noodzakelijk uit.
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setBackground(Color.darkGray);

		//schaal om te vermenigvuldigen, omdat het anders te klein wordt
		xScale = ((double) getWidth() - 2 * BORDER_GAP) / (MAX_SIZE - 1);
		yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE);

		//maak een lijst punten voor op de grafiek
		List<Point> graphPoints1 = maakGrafiekpunten(algoritme1);
		List<Point> graphPoints2 = maakGrafiekpunten(algoritme2);
		List<Point> graphPoints3 = maakGrafiekpunten(algoritme3);
		List<Point> graphPoints4 = maakGrafiekpunten(algoritme4);

		// create x and y axes
		g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);    //y-as
		g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);  //x-as

		// create hatch marks for y axis.
		for (int i = 0; i < Y_HATCH_CNT; i++) {
			if (Y_HATCH_CNT > 100) {
				i = i + 9;
			}
			int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
			int x2 = GRAPH_POINT_WIDTH - (BORDER_GAP / 4);
			int y0 = (int) (getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP));
			g2.drawLine(BORDER_GAP, y0, x1, y0);

			String i1 = Integer.toString((int) ((i + 1) * (MAX_SCORE / Y_HATCH_CNT)));
			g2.drawString(i1, x2, y0);
		}

		// and for x axis
		for (int i = 0; i < algoritme3.size() - 1; i++) {
			if (MAX_SIZE > 10 && MAX_SIZE < 100) {
				i++;
			} else if (MAX_SIZE > 100) {
				i = i + 9;
			}

			int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (MAX_SIZE - 1) + BORDER_GAP;
			int y0 = getHeight() - BORDER_GAP;
			int y1 = y0 - GRAPH_POINT_WIDTH;
			int y2 = y0 + (GRAPH_POINT_WIDTH);
			g2.drawLine(x0, y0, x0, y1);

			String i1 = Integer.toString(i + 1);
			g2.drawString(i1, x0, y2);
		}
		int b = getHeight() - (BORDER_GAP / 4);
		int a = (getWidth() - BORDER_GAP * 2) / (9 + BORDER_GAP);
		g2.drawString("    Simulatie:", a, b);

		//teken de lijnen
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(GRAPH_STROKE);
		g2.setColor(GRAPH_COLOR1);
		tekenlijn(graphPoints1);
		g2.setColor(GRAPH_COLOR2);
		tekenlijn(graphPoints2);
		g2.setColor(GRAPH_COLOR3);
		tekenlijn(graphPoints3);
		g2.setColor(GRAPH_COLOR4);
		tekenlijn(graphPoints4);

		//teken de stippen
		g2.setStroke(oldStroke);
		g2.setColor(GRAPH_POINT_COLOR);
		tekenpunten(graphPoints1);
		tekenpunten(graphPoints2);
		tekenpunten(graphPoints3);
		tekenpunten(graphPoints4);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(PREF_W, PREF_H);
	}


	private List<Point> maakGrafiekpunten(List<Double> a) {
		List<Point> graphPoints = new ArrayList<>();
		for (int i = 0; i < a.size(); i++) {
			if (!(i == 0)) {
				int x1 = (int) (i * xScale + BORDER_GAP);
				int y1 = (int) ((MAX_SCORE - a.get(i)) * yScale + BORDER_GAP);
				graphPoints.add(new Point(x1, y1));
			}
		}
		return graphPoints;
	}

	 static void tekenlijn(List<Point> graphPoints) {
		for (int i = 0; i < graphPoints.size() - 1; i++) {
			int x1 = graphPoints.get(i).x;
			int y1 = graphPoints.get(i).y;
			int x2 = graphPoints.get(i + 1).x;
			int y2 = graphPoints.get(i + 1).y;
			g2.drawLine(x1, y1, x2, y2);
		}
	}

	private void tekenpunten(List<Point> graphPoints) {
		for (Point graphPoint : graphPoints) {
			int x = graphPoint.x - GRAPH_POINT_WIDTH / 2;
			int y = graphPoint.y - GRAPH_POINT_WIDTH / 2;
			g2.fillOval(x, y, GRAPH_POINT_WIDTH, GRAPH_POINT_WIDTH);
		}
	}

/*	public static void createAndShowGui(List<Integer> yAs) {


		Graph mainPanel = new Graph(yAs);
		//grafiek staat momenteel in een eigen window.
		JFrame frame = new JFrame("DrawGraph");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().add(mainPanel);
//		frame.pack();
//		frame.setLocationByPlatform(true);
//		frame.setVisible(true);
	}*/
}
