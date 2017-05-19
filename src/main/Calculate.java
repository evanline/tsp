package main;

import java.util.ArrayList;

/**
 * Created by: Ian Hildebrand
 * Date: 19-May-17.
 */
class Calculate
{
	static double calculateDistance(Integer[] a,Integer[] b)
	{
		return Math.sqrt((Math.pow((Math.abs(b[0] - a[0]) ), 2) + Math.pow(Math.abs(b[1] - a[1]), 2)));
	}

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
