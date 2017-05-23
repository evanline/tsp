package main;

import java.util.*;

/**
 * Created by: Ian Hildebrand
 * Date: 08-May-17.
 */
class NearestNeighbor extends Calculate implements AlgorithmInterface
{
	private ArrayList<Integer[]> path = new ArrayList<>();
	private double totalDistance;
	private double timeSpend;
	private ArrayList<ArrayList<Integer[]>> ARRAYSEPTION = new ArrayList<>();

	/**
	 * NearestNeighbor looks for the point that is closest to the current position of the robot and adds that to the path.
	 * @param list the list of coördinates it needs to run the algorithm on.
	 */
	NearestNeighbor(ArrayList<Integer[]> list)
	{
		long startTime = System.nanoTime();

		Integer[] startPoint = new Integer[]{0, 0};

		Integer[] nearest = new Integer[]{0,0};
		int stuff = list.size();
		Integer[] currentPos = startPoint;
		while (stuff != path.size())
		{
			double Ndistance = -1;
			ArrayList<Integer[]> ARRAYSEPTIONINTESIFYS = new ArrayList<>();
			for (Integer[] i : list)
			{
				ARRAYSEPTIONINTESIFYS.add(i);
				double dist = calculateDistance(i, currentPos);

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
			ARRAYSEPTION.add(ARRAYSEPTIONINTESIFYS);
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

	/**
	 * getARRAYSEPTION returns all the steps this algothm makes
	 * @return a list with all the steps this algorithm took.
	 */
	ArrayList<ArrayList<Integer[]>> getARRAYSEPTION()
	{
		return ARRAYSEPTION;
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
