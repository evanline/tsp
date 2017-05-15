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

	TwoOpt() {
		long startTime = System.nanoTime();
		GenereerCoordinaten coordinaten = new GenereerCoordinaten();
		ArrayList<Integer[]> list = coordinaten.getLijstCoordinaten();
		Integer[] currentPos = new Integer[]{0, 0};

		for (Integer[] i : list)
		{
			double dist = Math.sqrt((Math.pow((Math.abs(i[0] - currentPos[0]) ), 2) + Math.pow(Math.abs(i[1] - currentPos[1]), 2))); // √((|x - y|)² + (|a - i|)²)
			path.add(i);
			totalDistance += dist;
			currentPos = i;
		}

		long endTime = System.nanoTime();
		timeSpend = (endTime - startTime) / (1 * Math.pow(10, 6));
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
