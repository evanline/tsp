package main;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by: Ian Hildebrand
 * Date: 15-May-17.
 */
public class EigenAlgoritme implements AlgorithmInterface
{
	private ArrayList<Integer[]> path = new ArrayList<>();
	private double totalDistance;
	private double timeSpend;

	EigenAlgoritme(ArrayList<Integer[]> list) {
		long startTime = System.nanoTime();

		if (list.size() > 9)
		{
			NearestNeighbor nn = new NearestNeighbor(list);
			path = nn.getPath();
			totalDistance = nn.getTotalDistance();
		}
		else
		{
			BruteForce nn = new BruteForce(list);
			path = nn.getPath();
			totalDistance = nn.getTotalDistance();
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
