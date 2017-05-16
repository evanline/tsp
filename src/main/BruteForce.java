package main;

import java.util.ArrayList;
import java.util.Arrays;

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

		returnValue = runBruteForce(new GenereerCoordinaten().getLijstCoordinaten());
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

	private static long count = 0;
	// return all possible location orders from an ArrayList with locations
	static ArrayList<ArrayList<Integer[]>> runBruteForce(ArrayList<Integer[]> original)
	{
		// only if the original array is empty
		if (original.size() == 0)
		{
			// create/add/return an empty result
			ArrayList<ArrayList<Integer[]>> result = new ArrayList<>();
			result.add(new ArrayList<>());
			return result;
		}

		// remove first element
		Integer[] firstElement = original.remove(0);

		// create a return list of ArrayLists
		ArrayList<ArrayList<Integer[]>> returnValue = new ArrayList<>();

		// recursively return the possibilities so far
		ArrayList<ArrayList<Integer[]>> permutations = runBruteForce(original);

		// for each arrayList ...
		for (ArrayList<Integer[]> smallerPermutated : permutations)
		{

			// for every Integer[] ...
			for (int i = 0; i <= smallerPermutated.size(); i++)
			{

				count++;
				// create a new ArrayList from the smaller
				ArrayList<Integer[]> temp = new ArrayList<>(smallerPermutated);

				// add the first element at the index position
				temp.add(i, firstElement);

				// add this possibility to the result
				returnValue.add(temp);
			}
		}

		return returnValue;
	}

	static long getCount()
	{
		return count;
	}
}
