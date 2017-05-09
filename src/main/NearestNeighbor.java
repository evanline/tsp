package main;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by: Ian Hildebrand
 * Date: 08-May-17.
 */
class NearestNeighbor
{
	private GenereerCoordinaten coordinaten;
	private ArrayList<Integer[]> list;
	private final Boolean DEV = true;
	private final Integer[] startPoint = new Integer[]{1, 1};
	private Integer[] currentPos = startPoint;
	private ArrayList<Integer[]> path = new ArrayList<>();

	NearestNeighbor()
	{
		coordinaten = new GenereerCoordinaten();
		list = coordinaten.getLijstCoordinaten();
		if (DEV) System.out.println("starting from:" + Arrays.toString(startPoint));

		double Ndistance = -1;
		Integer[] nearest = new Integer[]{1,1};

		while (list.size() != path.size())
		{
			for (Integer[] i : list)
			{
				double dist = Math.sqrt((Math.pow((Math.abs(i[0] - currentPos[0]) ), 2) + Math.pow(Math.abs(i[1] - currentPos[1]), 2)));
				if (path.contains(i) || i == currentPos) continue; //TODO: WORK DAMMIT
				if (dist < Ndistance || Ndistance == -1)
				{
					Ndistance = dist;
					nearest = i;
				}
			}
			path.add(nearest);
			currentPos = nearest;
		}

		if (DEV)
		{
			for (Integer[] i : list)
			{
				System.out.println(Arrays.toString(i));

			}
		}

		System.out.println("---------------------------------------------");

		if (DEV)
		{
			for (Integer[] i : path)
			{
				System.out.println(Arrays.toString(i));
			}
		}
	}

}
