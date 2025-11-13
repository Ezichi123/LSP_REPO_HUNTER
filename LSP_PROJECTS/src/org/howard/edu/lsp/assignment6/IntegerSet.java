package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A mutable mathematical set of integers backed by an ArrayList.
 * <p>Duplicates are not allowed. All mutators modify {@code this} instance.</p>
 */
public class IntegerSet {
	private List<Integer> set = new ArrayList<>();

	  /** Clears the internal representation of the set. */
	  public void clear() {
	    set.clear();
	  }

	  /** Returns the number of elements in the set. */
	  public int length() {
	    return set.size();
	  }

	  /**
	   * Returns true if the 2 sets are equal, false otherwise.
	   * Two sets are equal if they contain all of the same values in ANY order.
	   * This overrides the equals method from the Object class.
	   */
	  @Override
	  public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof IntegerSet)) return false;
	    IntegerSet other = (IntegerSet) o;
	    if (this.length() != other.length()) return false;
	    // order-independent content equality
	    return this.set.containsAll(other.set) && other.set.containsAll(this.set);
	  }

	  /** Returns true if the set contains the value, otherwise false. */
	  public boolean contains(int value) {
	    return set.contains(value);
	  }

	  /**
	   * Returns the largest item in the set.
	   * @throws IllegalStateException if the set is empty
	   */
	  public int largest()  {
	    if (set.isEmpty()) {
	      throw new IllegalStateException("Set is empty");
	    }
	    return Collections.max(set);
	  }

	  /**
	   * Returns the smallest item in the set.
	   * @throws IllegalStateException if the set is empty
	   */
	  public int smallest()  {
	    if (set.isEmpty()) {
	      throw new IllegalStateException("Set is empty");
	    }
	    return Collections.min(set);
	  }

	  /** Adds an item to the set or does nothing if already present. */
	  public void add(int item) {
	    if (!set.contains(item)) {
	      set.add(item);
	    }
	  }

	  /** Removes an item from the set or does nothing if not there. */
	  public void remove(int item) {
	    // remove(Object) to avoid interpreting the value as an index
	    set.remove(Integer.valueOf(item));
	  }

	  /** Set union: modifies this to contain all unique elements in this or other. */
	  public void union(IntegerSet other) {
	    // Use add(...) to preserve uniqueness
	    for (int v : other.set) {
	      this.add(v);
	    }
	  }

	  /** Set intersection: modifies this to contain only elements in both sets. */
	  public void intersect(IntegerSet other) {
	    set.retainAll(other.set);
	  }

	  /** Set difference (this \ other): modifies this to remove elements found in other. */
	  public void diff(IntegerSet other) {
	    set.removeAll(other.set);
	  }

	  /** Set complement: modifies this to become (other \ this). */
	  public void complement(IntegerSet other) {
	    List<Integer> copy = new ArrayList<>(other.set);
	    copy.removeAll(this.set);
	    this.set.clear();
	    this.set.addAll(copy);
	  }

	  /** Returns true if the set is empty, false otherwise. */
	  public boolean isEmpty() {
	    return set.isEmpty();
	  }

	  /**
	   * Returns a String representation like {@code [1, 2, 3]}.
	   * Order is not semantically required, but we print in ascending order for readability.
	   */
	  @Override
	  public String toString() {
	    List<Integer> snapshot = new ArrayList<>(set);
	    Collections.sort(snapshot);
	    return snapshot.toString();
	  }
}
