package main;

import java.util.*;

/**
 * Created by: Ian Hildebrand
 * Date: 08-May-17.
 */
class NearestNeighbor
{
	NearestNeighbor()
	{
		GenereerCoordinaten coordinaten = new GenereerCoordinaten(10);
		ArrayList<Integer[]> list = coordinaten.getLijstCoordinaten();
		Integer[] startPoint = new Integer[]{1, 1};

		Integer[] nearest = new Integer[]{1,1};
		int stuff = list.size();

		ArrayList<Integer[]> path = new ArrayList<>();
		Integer[] currentPos = startPoint;
		while (stuff != path.size())
		{
			double Ndistance = -1;

			for (Integer[] i : list)
			{
				double dist = Math.sqrt((Math.pow((Math.abs(i[0] - currentPos[0]) ), 2) + Math.pow(Math.abs(i[1] - currentPos[1]), 2)));
				if (dist < Ndistance || Ndistance == -1)
				{
					Ndistance = dist;
					nearest = i;
				}
			}
			path.add(nearest);
			list.remove(nearest);
			currentPos = nearest;
		}

			for (Integer[] i : path)
			{
				System.out.println(Arrays.toString(i));
			}

	}
}
