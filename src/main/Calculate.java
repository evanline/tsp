package main;

import java.util.ArrayList;

/**
 * Created by: Ian Hildebrand
 * Date: 19-May-17.
 */
class Calculate
{
	/**
	 * CalulateDistance calculates the distance between 2 points.
	 * √((|x - y|)² + (|a - i|)²)
	 * @param a point 1
	 * @param b point 2
	 * @return The distance between point 1 and 2.
	 */
	static double calculateDistance(Integer[] a,Integer[] b)
	{
		return Math.sqrt((Math.pow((Math.abs(b[0] - a[0]) ), 2) + Math.pow(Math.abs(b[1] - a[1]), 2)));
	}

	/**
	 * CalculateTotalDistance calculates the distance from beginning to end in a given path.
	 * @param list The path that needs to be measured.
	 * @return the distance of the entire path.
	 */
	static double calculateTotalDistance(ArrayList<Integer[]> list)
	{
		double x = 0;
		for (int i1 = 0; i1 < list.size()-1; i1++)
		{
			Integer[] i = list.get(i1);
			x += calculateDistance(i, list.get(i1 + 1));
		}
		return x;
	}
}
