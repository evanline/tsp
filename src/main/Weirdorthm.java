package main;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by: Ian Hildebrand
 * Date: 12-May-17.
 */
/*
public class Weirdorthm implements AlgorithmInterface {
	private ArrayList<Integer[]> path = new ArrayList<>();
	private double totalDistance = -1;
	private double newTotalDistance;
	private double timeSpend;

	Weirdorthm(ArrayList<Integer[]> list) {
		long startTime = System.nanoTime();
		boolean lol = true;
		path = list;

		while (lol)
		{
			ArrayList<Integer[]> tempList = runTwoOpt(new ArrayList<>(path));
			if (newTotalDistance < totalDistance || totalDistance == -1)
			{
				totalDistance =  newTotalDistance;
				path = tempList;
			}
			else
			{
				lol = false;
			}
		}

		long endTime = System.nanoTime();
		timeSpend = (endTime - startTime) / (1 * Math.pow(10, 6));
	}

	private double calculateDistance(Integer[] a,Integer[] b)
	{
		return Math.sqrt((Math.pow((Math.abs(b[0] - a[0]) ), 2) + Math.pow(Math.abs(b[1] - a[1]), 2)));
	}

	private ArrayList<Integer[]> runTwoOpt (ArrayList<Integer[]> list)
	{
		newTotalDistance = 0;
		ArrayList<Integer[]> tpath = new ArrayList<>();
		for (int i = -1; i < list.size();)
		{
			if ((i+4) > list.size())
			{
				for(int b = 1; (b+i) < list.size(); b++)
				{
					tpath.add(list.get(i+b));
				}
				break;
			}
			else
			{
				i += 4;
			}

			Integer[] x = list.get(i - 3);
			Integer[] y = list.get(i - 2);
			Integer[] u = list.get(i - 1);
			Integer[] v = list.get(i);

			double route1 = calculateDistance(x, y);
			double route2 = calculateDistance(u, v);

			double costR1 = route1 + route2;

			double route3 = calculateDistance(x, u);
			double route4 = calculateDistance(y, v);

			double costR2 = route3 + route4;

			if(costR1 > costR2)
			{
				tpath.add(x);
				tpath.add(u);
				tpath.add(y);
				tpath.add(v);
				newTotalDistance += costR1;
			}
			else
			{
				tpath.add(x);
				tpath.add(y);
				tpath.add(u);
				tpath.add(v);
				newTotalDistance += costR2;
			}
		}
		return tpath;
	}

	public ArrayList<Integer[]> getList()
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

}*/
