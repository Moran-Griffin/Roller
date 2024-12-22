import java.util.ArrayList;
import java.util.Random;
/**
 * Class for simulating dice rolls and experimentally exploring distributions of roll totals.
 *
 * @author Griffin Moran
 * @version 1.0
 *
 */

public class Roller {

  private Random rand;

  /**
   * Create a roller with a specified seed for reproducible results.
   *
   * @param seed Seed for random number generator
   */
  public Roller(long seed) {
    rand = new Random(seed);
  }

  /**
   * Create a roller with a system-assigned seed.
   */
  public Roller() {
    rand = new Random();
  }

  /**
   * Roll the indicated type of dice the indicated number of times and return the sum of all rolls.
   *
   * @param numDice Number of dice to roll
   * @param numSides Number of sides for each die
   * @return The sum of all dice rolls
   * @throws IllegalArgumentException if numDice or numSides are not positive
   */
  public int roll(int numDice, int numSides) throws IllegalArgumentException {
    if (numDice < 1 || numSides < 1) {
      throw new IllegalArgumentException();
    }
    int total = 0;
    for (int i = 0; i < numDice; i++) {
      int val = rand.nextInt(numSides) + 1;
      total += val;
    }
    return total;
  }

  /**
   * This method repeatedly performs multi-dice rolls and keeps a running tally of the number of
   * times that each total is observed.
   *
   * @param numTrials Number of times to roll the indicated set of dice
   * @param numDice The number of dice to roll in each trial
   * @param numSides The number of sides for the dice
   * @return A Multiset of total values indicating the number of times that each total occurred
   * @throws IllegalArgumentException if numTrials, numDice, or numSides are not positive
   */
  public ArrayListMultiset<Integer> multiRoll(int numTrials, int numDice, int numSides)
        throws IllegalArgumentException {
    if (numTrials < 1) {
      throw new IllegalArgumentException();
    }
    ArrayListMultiset<Integer> m = new ArrayListMultiset<>();
    int total = 0;
    for (int i = 0; i < numTrials; i++) {
      total = roll(numDice, numSides);
      m.add(total);
    }
    return m;
  }

  /**
   * Plot an ASCII histogram illustrating the distribution of totals observed over multiple rolls.
   *
   * @param numTrials Number of times to roll the indicated set of dice
   * @param numDice The number of dice to roll in each trial
   * @param numSides The number of sides for the dice
   * @param scale How many results will be represented by each hash mark. For example, if scale is
   *        equal to 10, then each hash mark represents 10 outcomes, rounding down. For example, if
   *        scale is 10 and there are 29 occurrences, then one two hash marks will be displayed.
   * @throws IllegalArgumentException in the case of any non-positive argument
   */
  public void plotRolls(int numTrials, int numDice, int numSides, int scale) 
        throws IllegalArgumentException {
    if (scale < 1) {
      throw new IllegalArgumentException();
    }

    ArrayListMultiset<Integer> rolls = multiRoll(numTrials, numDice, numSides);
    String output = String.format("Rolling %dd%d %d Times\n", numDice, numSides, numTrials);
    ArrayList<Integer> added = new ArrayList<>();

    for (int i = numDice; i <= numDice * numSides; i++) {
      if (!added.contains(i)) {
        int hashes = rolls.getCount(i) / scale;
        double pct = rolls.getCount(i) * 100.0f / numTrials; 
        String data = String.format("%d (%.2f%%)", i, pct);
        output += String.format("%-12s", data) + ":";

        int j = 0;
        while (j < hashes) {
          output += "#";
          j++;
        }
        added.add(i);
        output += "\n";
      }
    }
    System.out.print(output);
  }
}
