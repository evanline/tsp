package main;

import java.util.ArrayList;
import java.util.Arrays;

import static main.Brutal.bruteForce;

/**
 * Created by: Ian Hildebrand & Sjoerd Dekker(hulp)
 * Date: 15-May-17.
 */
public class BruteForce implements AlgorithmInterface
{
	private ArrayList<Integer[]> path = new ArrayList<>();
	private ArrayList<ArrayList<Integer[]>> returnValue = new ArrayList<>();

	private double totalDistance;
	private double timeSpend;

	BruteForce() {
		long startTime = System.nanoTime();

		returnValue = bruteForce(new GenereerCoordinaten().getLijstCoordinaten());
		double shortestRouteLength = -1d;
		for (ArrayList<Integer[]> i : returnValue)
		{
			double total = 0d;
			for (int a = 0; a < i.size()-1; a++)
			{
				Integer[] startLoc = i.get(a);
				Integer[] secondLoc = i.get(a+1);
				total += Math.sqrt((Math.pow((Math.abs(secondLoc[0] - startLoc[0]) ), 2) + Math.pow(Math.abs(secondLoc[1] - startLoc[1]), 2)));
			}

			if(shortestRouteLength == -1d || total < shortestRouteLength){
				shortestRouteLength = total;
				path = i;
				totalDistance = total;
			}
		}

		long endTime = System.nanoTime();
		timeSpend = (endTime - startTime) / (1 * Math.pow(10, 6));
	}

	public ArrayList<Integer[]> getPath()
	{
		return path;
	}

	public ArrayList<ArrayList<Integer[]>> getReturnValue()
	{
		return returnValue;
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
