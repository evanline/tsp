package main;

import java.util.ArrayList;

/**
 * Created by: Ian Hildebrand
 * Date: 12-May-17.
 */
public class BruteForce implements AlgorithmInterface
{
	private ArrayList<Integer[]> path = new ArrayList<>();
	private double totalDistance;
	private double timeSpend;

	BruteForce()
	{
		long startTime = System.nanoTime();
		GenereerCoordinaten coordinaten = new GenereerCoordinaten();
		ArrayList<Integer[]> list = coordinaten.getLijstCoordinaten();
		long maxOptions = factorial(list.size());

		while (path.size() < maxOptions)
		{

		}


		long endTime = System.nanoTime();
		timeSpend = (endTime - startTime) / (1 * Math.pow(10, 6));
	}

	public static long factorial(int N)
	{
		long multi = 1;
		for (int i = 1; i <= N; i++) {
			multi = multi * i;
		}
		return multi;
	}

	@Override
	public double getTotalDistance() {
		return totalDistance;
	}

	@Override
	public double getRunTime() {
		return timeSpend;
	}
}
