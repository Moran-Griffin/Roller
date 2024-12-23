import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * ArrayListMultiset collection type for Strings.
 *
 * @author CS 240 Instructors
 * @version V3, 1/2024
 *
 */
public class ArrayListMultiset<E> extends AbstractMultiset<E> {

  private ArrayList<E> elements;

  /**
   * Create an empty ArrayListMultiset.
   */
  public ArrayListMultiset() {
    elements = new ArrayList<E>();
  }

  /**
   * Add an item.
   *
   * @param item The item to add
   * @return Always returns true.
   */
  public boolean add(E item) {
    return elements.add(item);
  }

  /**
   * Add some number of occurrences for an item.
   *
   * @param item Item to add
   * @param occurances The number of occurrences of the item
   * @return The count of the item before the operation.
   */
  public int add(E item, int occurances) {
    int num = getCount(item);
    for (int i = 0; i < occurances; i++) {
      elements.add(item);
    }
    return num;
  }
  
  /**
   * Return true if the provided item is contained in the Multiset.
   *
   * @param item The item to check
   * @return true if the item is in the Multiset, false otherwise
   */
  @Override
  public boolean contains(Object item) {
    return super.contains(item);
  }

  /**
   * Return a count of the number of occurrences of the provided item in the Multiset.
   *
   * @param item The item to count
   * @return The number of occurrences
   */
  public int getCount(Object item) {
    int total = 0;

    for (E element : elements) {
      if (element.equals(item)) {
        total++;
      }
    }

    return total;
  }


  /**
   * Return true if the provided object is equal to this Multiset. Two Multisets are considered to
   * be equal if they contain the same elements with the same counts.
   *
   * @param other The object to check for equality
   * @return true if the object is equal to this Multiset
   */
  @Override
  public boolean equals(Object other) {

    if (!(other instanceof ArrayListMultiset) || ((ArrayListMultiset) other).size() != size()) {
      return false;
    }

    for (E element : elements) {
      if (this.getCount(element) != ((ArrayListMultiset) other).getCount(element)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Return an iterator for this Multiset. Repeated elements will be returned the appropriate number
   * of times by the iterator.
   *
   * @return The iterator
   */
  @Override
  public Iterator<E> iterator() {
    return elements.iterator();
  }

  /**
   * Reduce the count of the provided element by one. Remove the element if the count reaches 0.
   *
   * @param item The element to remove
   * @return true if the element was contained in the Multiset
   */
  @Override
  public boolean remove(Object item) {
    return elements.remove(item);
  }
  
  @Override
  public int size() {
    return elements.size();
  }

  /**
   * Return a String representation of this Multiset. A string representation of each element will
   * be included along with the count for that element. For example, the Multiset [a, a, b] could be
   * represented by the String "[a x 2, b x 1]". The order of the elements is not specified.
   *
   * @return A string representation of the Multiset
   */
  @Override
  public String toString() {
    HashSet<E> added = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    sb.append("[");

    for (E element : elements) {

      if (!added.contains(element)) {

        if (added.size() > 0) {
          sb.append(", ");
        }

        sb.append(element.toString() + " x " + this.getCount(element));
      }
      added.add(element);
    }
    sb.append("]");
    return sb.toString();
  }
}
