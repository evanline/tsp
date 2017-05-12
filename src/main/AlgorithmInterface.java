package main;
/**
 * Created by: Ian Hildebrand
 * Date: 10-May-17.
 */
public interface AlgorithmInterface
{
	/**
	 * get the total distance of the path calculated.
	 * @return total distance in squares.
	 */
	double getTotalDistance();

	/**
	 * get the time it took to run the algorithm
	 * @return time in nanoseconds
	 */
	double getRunTime();
}
