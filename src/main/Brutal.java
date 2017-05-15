package main;

import java.util.ArrayList;

/**
 * Created by: Ian Hildebrand
 * Date: 15-May-17.
 */
class Brutal
{
	private static long count = 0;
	// return all possible location orders from an ArrayList with locations
	static ArrayList<ArrayList<Integer[]>> bruteForce(ArrayList<Integer[]> original)
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
		ArrayList<ArrayList<Integer[]>> permutations = bruteForce(original);

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
