package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** JUnit 5 tests for IntegerSet. Covers normal and edge cases. */
public class IntegerSetTest {
	private IntegerSet mk(int... vals) {
	    IntegerSet s = new IntegerSet();
	    for (int v : vals) s.add(v);
	    return s;
	  }

	  @Test
	  @DisplayName("clear() removes all elements")
	  void testClear() {
	    IntegerSet s = mk(1, 2, 3);
	    assertEquals(3, s.length());
	    s.clear();
	    assertTrue(s.isEmpty());
	    assertEquals(0, s.length());
	  }

	  @Test
	  @DisplayName("length() reflects number of unique elements")
	  void testLength() {
	    IntegerSet s = new IntegerSet();
	    s.add(1); s.add(1); s.add(2);
	    assertEquals(2, s.length());
	  }

	  @Test
	  @DisplayName("equals() is order-independent and size-aware")
	  void testEquals() {
	    IntegerSet a = mk(3, 1, 2);
	    IntegerSet b = mk(2, 3, 1);
	    IntegerSet c = mk(1, 2);
	    assertTrue(a.equals(b));
	    assertTrue(b.equals(a));
	    assertFalse(a.equals(c));
	    assertFalse(a.equals("not-a-set"));
	  }

	  @Test
	  @DisplayName("contains() finds existing and missing values")
	  void testContains() {
	    IntegerSet s = mk(5, 7, 9);
	    assertTrue(s.contains(7));
	    assertFalse(s.contains(6));
	  }

	  @Test
	  @DisplayName("largest() returns max and throws on empty")
	  void testLargest() {
	    IntegerSet s = mk(4, -2, 10, 3);
	    assertEquals(10, s.largest());
	    IntegerSet empty = new IntegerSet();
	    assertThrows(IllegalStateException.class, empty::largest);
	  }

	  @Test
	  @DisplayName("smallest() returns min and throws on empty")
	  void testSmallest() {
	    IntegerSet s = mk(4, -2, 10, 3);
	    assertEquals(-2, s.smallest());
	    IntegerSet empty = new IntegerSet();
	    assertThrows(IllegalStateException.class, empty::smallest);
	  }

	  @Test
	  @DisplayName("add() prevents duplicates")
	  void testAdd() {
	    IntegerSet s = new IntegerSet();
	    s.add(1);
	    s.add(1);
	    s.add(2);
	    assertEquals(2, s.length());
	    assertTrue(s.contains(1));
	    assertTrue(s.contains(2));
	  }

	  @Test
	  @DisplayName("remove() deletes present items and ignores missing")
	  void testRemove() {
	    IntegerSet s = mk(1, 2, 3);
	    s.remove(2);
	    assertFalse(s.contains(2));
	    assertEquals(2, s.length());
	    // removing non-existent should be safe
	    s.remove(42);
	    assertEquals(2, s.length());
	  }

	  @Test
	  @DisplayName("union() mutates receiver and keeps uniqueness")
	  void testUnion() {
	    IntegerSet a = mk(1, 2);
	    IntegerSet b = mk(2, 3);
	    a.union(b);                 // must mutate a
	    assertTrue(a.equals(mk(1, 2, 3)));
	    // b unchanged
	    assertTrue(b.equals(mk(2, 3)));
	  }

	  @Test
	  @DisplayName("intersect() keeps only common elements")
	  void testIntersect() {
	    IntegerSet a = mk(1, 2, 3);
	    IntegerSet b = mk(2, 3, 4);
	    a.intersect(b);
	    assertTrue(a.equals(mk(2, 3)));
	    // disjoint intersection -> empty
	    IntegerSet c = mk(10, 11);
	    c.intersect(b);
	    assertTrue(c.isEmpty());
	  }

	  @Test
	  @DisplayName("diff() removes elements found in other (this \\ other)")
	  void testDiff() {
	    IntegerSet a = mk(1, 2, 3, 4);
	    IntegerSet b = mk(2, 4, 6);
	    a.diff(b);
	    assertTrue(a.equals(mk(1, 3)));
	    // diff with self -> empty
	    IntegerSet c = mk(7, 8);
	    c.diff(c);
	    assertTrue(c.isEmpty());
	  }

	  @Test
	  @DisplayName("complement() becomes (other \\ this)")
	  void testComplement() {
	    IntegerSet a = mk(1, 2);
	    IntegerSet b = mk(1, 2, 3, 4);
	    a.complement(b);                 // a = b \ a = {3,4}
	    assertTrue(a.equals(mk(3, 4)));

	    IntegerSet x = mk(5, 6);
	    IntegerSet y = mk(5, 6);
	    x.complement(y);                 // y \ x = {}
	    assertTrue(x.isEmpty());
	  }

	  @Test
	  @DisplayName("isEmpty() true/false")
	  void testIsEmpty() {
	    IntegerSet s = new IntegerSet();
	    assertTrue(s.isEmpty());
	    s.add(1);
	    assertFalse(s.isEmpty());
	  }

	  @Test
	  @DisplayName("toString() uses square brackets and commas; order not required by spec")
	  void testToString() {
	    IntegerSet s = mk(3, 1, 2);
	    String text = s.toString();
	    assertTrue(text.startsWith("[") && text.endsWith("]"));
	    assertTrue(text.contains("1"));
	    assertTrue(text.contains("2"));
	    assertTrue(text.contains("3"));
	    // empty format
	    assertEquals("[]", new IntegerSet().toString());
	  }
	  
	  /**
	   * Tests that unioning a set with itself does not introduce duplicates or errors.
	   * Verifies that the set remains unchanged and the operation is safe for self-reference.
	   */
	  @Test
	  void unionWithSelf_noDupesAndNoCrash() {
	    IntegerSet s = mk(1,2,3);
	    s.union(s);
	    assertTrue(s.equals(mk(1,2,3)));
	  }

	  /**
	   * Tests that intersecting a set with itself leaves the set unchanged.
	   * This ensures that the intersection operation is idempotent for identical operands.
	   */
	  @Test
	  void intersectWithSelf_identity() {
	    IntegerSet s = mk(4,5);
	    s.intersect(s);
	    assertTrue(s.equals(mk(4,5)));
	  }

	  /**
	   * Tests that complementing a set with itself yields an empty set.
	   * The complement of a set with itself should contain no elements.
	   */
	  @Test
	  void complementWithSelf_becomesEmpty() {
	    IntegerSet s = mk(7,8);
	    s.complement(s);
	    assertTrue(s.isEmpty());
	  }

	  /**
	   * Ensures that mutator methods (like union) do not create aliasing.
	   * After performing a union, modifying the second set must not affect the first.
	   */
	  @Test
	  void methodsDoNotAliasOtherSet() {
	    IntegerSet a = mk(1,2);
	    IntegerSet b = mk(2,3);
	    a.union(b);
	    b.add(99); // a should not be impacted
	    assertFalse(a.contains(99));
	  }

	  /**
	   * Tests that union with an empty set leaves the original set unchanged.
	   */
	  @Test
	  void union_withEmpty_isNoop() {
	    IntegerSet a = mk(1,2);
	    IntegerSet e = new IntegerSet();
	    a.union(e);
	    assertTrue(a.equals(mk(1,2)));
	  }

	  /**
	   * Tests that intersecting two disjoint sets results in an empty set.
	   */
	  @Test
	  void intersect_disjoint_becomesEmpty() {
	    IntegerSet a = mk(1,2);
	    IntegerSet b = mk(3,4);
	    a.intersect(b);
	    assertTrue(a.isEmpty());
	  }

	  /**
	   * Tests that diff with an empty set does not alter the original set.
	   */
	  @Test
	  void diff_withEmpty_noChange() {
	    IntegerSet a = mk(1,2);
	    IntegerSet e = new IntegerSet();
	    a.diff(e);
	    assertTrue(a.equals(mk(1,2)));
	  }

	  /**
	   * Tests that complement with an empty other set yields an empty result.
	   */
	  @Test
	  void complement_emptyOther_resultsEmpty() {
	    IntegerSet a = mk(1,2);
	    IntegerSet e = new IntegerSet();
	    a.complement(e);
	    assertTrue(a.isEmpty());
	  }

	  /**
	   * Verifies that equals() satisfies the properties of reflexivity,
	   * symmetry, transitivity, and non-nullity.
	   */
	  @Test
	  void equals_isReflexiveSymmetricTransitive() {
	    IntegerSet a = mk(1,2,3);
	    IntegerSet b = mk(3,2,1);
	    IntegerSet c = mk(2,1,3);
	    assertTrue(a.equals(a));               // reflexive
	    assertTrue(a.equals(b) && b.equals(a)); // symmetric
	    assertTrue(a.equals(b) && b.equals(c) && a.equals(c)); // transitive
	    assertFalse(a.equals(null));            // non-nullity
	  }

	  /**
	   * Ensures that the set correctly handles negative and large integers
	   * while maintaining uniqueness (no duplicates).
	   */
	  @Test
	  void add_handlesNegativesAndLargeValues_noDupes() {
	    IntegerSet s = mk(-10, 0, 1_000_000_000, -10, 0);
	    assertTrue(s.contains(-10));
	    assertTrue(s.contains(0));
	    assertTrue(s.contains(1_000_000_000));
	    assertEquals(3, s.length());
	  }

}
