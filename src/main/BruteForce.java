package main;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by: Ian Hildebrand & Sjoerd Dekker(hulp)
 * Date: 15-May-17.
 */
public class BruteForce extends Calculate implements AlgorithmInterface
{
	private ArrayList<Integer[]> path = new ArrayList<>();

	private double totalDistance;
	private double timeSpend;

	BruteForce(ArrayList<Integer[]> coordinates) {
		long startTime = System.nanoTime();

		ArrayList<ArrayList<Integer[]>> returnValue = runBruteForce(coordinates);
		double shortestRouteLength = -1d;
		for (ArrayList<Integer[]> i : returnValue)
		{
			double total = 0d;
			for (int a = 0; a < i.size()-1; a++)
			{
				Integer[] startLoc = i.get(a);
				Integer[] secondLoc = i.get(a+1);
				total += calculateDistance(startLoc, secondLoc);
			}

			if(shortestRouteLength == -1d || total < shortestRouteLength){
				shortestRouteLength = total;
				path = i;
				totalDistance = total;
			}
		}
		totalDistance = calculateTotalDistance(path);
		long endTime = System.nanoTime();
		timeSpend = (endTime - startTime) / (1 * Math.pow(10, 6));
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

	// return all possible location orders from an ArrayList with locations
	private static ArrayList<ArrayList<Integer[]>> runBruteForce(ArrayList<Integer[]> original)
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
}
