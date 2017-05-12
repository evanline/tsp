package main;

import java.util.*;

/**
 * Created by: Ian Hildebrand
 * Date: 08-May-17.
 */
class NearestNeighbor implements AlgorithmInterface
{
	private ArrayList<Integer[]> path = new ArrayList<>();
	private double totalDistance;
	private double timeSpend;

	NearestNeighbor()
	{
		long startTime = System.nanoTime();
		GenereerCoordinaten coordinaten = new GenereerCoordinaten();
		ArrayList<Integer[]> list = coordinaten.getLijstCoordinaten();
		Integer[] startPoint = new Integer[]{0, 0};

		Integer[] nearest = new Integer[]{0,0};
		int stuff = list.size();
		Integer[] currentPos = startPoint;
		while (stuff != path.size())
		{
			double Ndistance = -1;

			for (Integer[] i : list)
			{
				double dist = Math.sqrt((Math.pow((Math.abs(i[0] - currentPos[0]) ), 2) + Math.pow(Math.abs(i[1] - currentPos[1]), 2)));
				// √((|x - y|)² + (|a - i|)²)
				if (dist < Ndistance || Ndistance == -1)
				{
					Ndistance = dist;
					nearest = i;
				}
			}
			path.add(nearest);
			totalDistance += Ndistance;
			list.remove(nearest);
			currentPos = nearest;
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
