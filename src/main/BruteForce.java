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

	/**
	 * BruteForce itterates through all possible paths and gives back the most optimal one.
	 * @param list the list of coördinates it needs to run the algorithm on.
	 */
	BruteForce(ArrayList<Integer[]> list) {
		long startTime = System.nanoTime();

		ArrayList<ArrayList<Integer[]>> Value = runBruteForce(list);
		double shortestRouteLength = -1d;
		for (ArrayList<Integer[]> i : Value)
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

	/**
	 * returns the path that is calculated.
	 * @return the path that is calculated.
	 */
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

	/**
	 * return all possible location orders from an ArrayList with locations
	 * @param originalList the original list
	 * @return all the paths
	 */
	private static ArrayList<ArrayList<Integer[]>> runBruteForce(ArrayList<Integer[]> originalList)
	{
		if (originalList.size() == 0)
		{
			ArrayList<ArrayList<Integer[]>> result = new ArrayList<>();
			result.add(new ArrayList<>());
			return result;
		}
		Integer[] firstInteger = originalList.remove(0);
		ArrayList<ArrayList<Integer[]>> Value = new ArrayList<>();
		ArrayList<ArrayList<Integer[]>> thingy = runBruteForce(originalList);
		for (ArrayList<Integer[]> smallerThingy : thingy)
		{
			for (int i = 0; i <= smallerThingy.size(); i++)
			{
				ArrayList<Integer[]> templist = new ArrayList<>(smallerThingy);
				templist.add(i, firstInteger);
				Value.add(templist);
			}
		}
		return Value;
	}
}
