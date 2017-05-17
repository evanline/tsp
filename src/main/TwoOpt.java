package main;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by: Ian Hildebrand
 * Date: 12-May-17.
 */
public class TwoOpt implements AlgorithmInterface
{
	private ArrayList<Integer[]> path = new ArrayList<>();
	private double totalDistance;
	private double timeSpend;

	TwoOpt(ArrayList<Integer[]> list) {
		long startTime = System.nanoTime();
		Integer[] currentPos = new Integer[]{0, 0};

		for (int i = 0; i < list.size(); i++)
		{
			i = ((i + 3) > list.size()) ? list.size() : (i + 3);

			Integer[] x = list.get(i - 3);
			Integer[] y = list.get(i - 2);
			Integer[] u = list.get(i - 1);
			Integer[] v = list.get(i);

			double route1 = calculateDistance(x, y);
			double route2 = calculateDistance(u, v);

			double costR1 = route1 + route2;

			double route3 = calculateDistance(x, u);
			double route4 = calculateDistance(y, v);

			double costR2 = route3 + route4;


		}

		long endTime = System.nanoTime();
		timeSpend = (endTime - startTime) / (1 * Math.pow(10, 6));
	}

	private double calculateDistance(Integer[] a,Integer[] b)
	{
		return Math.sqrt((Math.pow((Math.abs(b[0] - a[0]) ), 2) + Math.pow(Math.abs(b[1] - a[1]), 2)));
	}

	public ArrayList<Integer[]> getPath()
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
