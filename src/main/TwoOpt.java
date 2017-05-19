package main;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by: Sanne Klaassen
 * Date: 18/05/17.
 */
public class TwoOpt implements AlgorithmInterface {
	private ArrayList<Integer[]> path = new ArrayList<>();
	private double totalDistance;
	private double previousTotalDistance;
	private double timeSpend;

	TwoOpt(ArrayList<Integer[]> list) {
		long startTime = System.nanoTime();
		previousTotalDistance = -1d;
		totalDistance = -1d;

		list = new NearestNeighbor(list).getPath();

		while (this.previousTotalDistance >= this.totalDistance || previousTotalDistance == -1d)
		{
			run2Opt(list);
		}

		long endTime = System.nanoTime();
		timeSpend = (endTime - startTime) / (1 * Math.pow(10, 6));
	}

	private double calculateDistance(Integer[] a,Integer[] b)
	{
		return Math.sqrt((Math.pow((Math.abs(b[0] - a[0]) ), 2) + Math.pow(Math.abs(b[1] - a[1]), 2)));
	}

	private void run2Opt(ArrayList<Integer[]> list)
	{
		for (Integer[] a : list)
		{
			int indexa = list.indexOf(a);
			if ((list.size()-1) < (indexa+1))
			{
				continue;
			}

			Integer[] b = list.get(indexa+1);

			for(Integer[] c : list)
			{
				int indexc = list.indexOf(c);
				if ((list.size()-1) < (indexc+1)) continue;
				Integer[] d = list.get(indexc+1);
				if (c == a || c == b || d == a || d == b) continue;

				Line2D line1 = new Line2D.Float(a[0], a[1], b[0], b[1]);
				Line2D line2 = new Line2D.Float(c[0], c[1], d[0], d[1]);

				if (line2.intersectsLine(line1))
				{
					double path1 = calculateDistance(a, b);
					double path2 = calculateDistance(c, d);
					double path3 = calculateDistance(a, c);
					double path4 = calculateDistance(b, d);

					double route1 = path1 + path2;
					double route2 = path3 + path4;

					if (route1 > route2)
					{
						Collections.swap(list, indexa+1, indexc);
					}
				}
			}
		}
		calculateTotalDistance(list);
		if((previousTotalDistance > totalDistance) || previousTotalDistance == -1d)
		{
			this.path = list;
		}
	}

	private void calculateTotalDistance(ArrayList<Integer[]> list)
	{
		previousTotalDistance = totalDistance;
		for(Integer[] i : list)
		{
			int index = list.indexOf(i);
			if ((list.size()-1) < (index+1))
			{
				continue;
			}
			totalDistance += calculateDistance(i, list.get(index+1));
		}
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
