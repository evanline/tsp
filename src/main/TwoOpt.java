package main;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by: Sanne Klaassen
 * Date: 18/05/17.
 */
public class TwoOpt implements AlgorithmInterface {
	private ArrayList<Integer[]> path = new ArrayList<>();
	private double totalDistance;
	private double timeSpend;

	TwoOpt(ArrayList<Integer[]> list) {
		long startTime = System.nanoTime();

		for (Integer[] a : list)
		{
			for (Integer[] b : list)
			{
				if (a == b) continue;
				Line2D line1 = new Line2D.Float(a[0], a[1], b[0], b[1]);

				for (Integer[] c : list)
				{
					if (c == a || c == b) continue;
					for (Integer[] d : list)
					{
						if (d == c || d == b || d == a) continue;
						Line2D line2 = new Line2D.Float(c[0], c[1], d[0], d[1]);
						if (line2.intersectsLine(line1))
						{
							//todo: this is wrong, it needs to select edges according to a path not make them!
						}
					}
				}
			}
		}

		long endTime = System.nanoTime();
		timeSpend = (endTime - startTime) / (1 * Math.pow(10, 6));
	}

	private double calculateDistance(Integer[] a,Integer[] b)
	{
		return Math.sqrt((Math.pow((Math.abs(b[0] - a[0]) ), 2) + Math.pow(Math.abs(b[1] - a[1]), 2)));
	}

	ArrayList<Integer[]> getPath()
	{
		return path;
	}

	@Override
	public double getTotalDistance() {
		return totalDistance;
	}

	@Override
	public double getRunTime() {
		return timeSpend;
	}

	@Override
	public String toString()
	{
		StringBuilder pathyeey = new StringBuilder("path:");
		for (Integer[] i : path)
		{
			pathyeey.append(Arrays.toString(i));
		}
		return String.valueOf(pathyeey);
	}
}
