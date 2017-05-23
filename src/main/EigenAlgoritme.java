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
	private ArrayList<ArrayList<Integer[]>> ARRAYSEPTION = new ArrayList<>();

	/**
	 * constructor, selects what algorithm to use according to the list size.
	 * @param list the list of coördinates it needs to run the algorithm on.
	 */
	EigenAlgoritme(ArrayList<Integer[]> list) {
		long startTime = System.nanoTime();

		if (list.size() > 9)
		{
			NearestNeighbor nn = new NearestNeighbor(list);
			path = nn.getPath();
			totalDistance = nn.getTotalDistance();
			ARRAYSEPTION =nn.getARRAYSEPTION();
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

	/**
	 * returns the path that is calculated.
	 * @return the path that is calculated.
	 */
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

	public ArrayList<ArrayList<Integer[]>> getARRAYSEPTION() {
		return ARRAYSEPTION;
	}
}
