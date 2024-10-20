package edu.grinnell.csc207.util;

import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * A variety of tests for the MatrixV0 class.
 *
 * @author Sam Schmidt
 */
public class TestsByStudent {
  MatrixV0<Integer> tester = new MatrixV0<Integer>(3, 4);
  /**
   * Tests the height and width of a straightforward matrix.
   */
  @Test
  public void simpleHeightAndWidth() {
    assertEquals(3, tester.width(), "A simple width.");
    assertEquals(4, tester.height(), "A simple height.");
  } // simpleHeightAndWidth()

  /**
   * Tests the height and width of a straightforward matrix.
   */
  @Test
  public void simpleClone() {
    @SuppressWarnings("unchecked")
    Matrix<Integer> cloner = tester.clone();
    assertEquals(3, cloner.width(), "A simple width.");
    assertEquals(4, cloner.height(), "A simple height.");
  } // simpleHeightAndWidth()

  /**
   * Tests that a specific spot can be set and then can later get that value;
   */
  @Test
  public void setAndGet() {
    tester.set(2, 2, 2);
    assertEquals(2, tester.get(2, 2), "Sets and gets an integer val.");
  } //setAndGet()

  /**
   * Tests that insertRows with defaults work. 
   * @throws ArraySizeException 
   */
  @Test
  public void setAndDeleteRows() throws ArraySizeException {
    Integer i1 = Integer.valueOf(1);
    Integer i2 = Integer.valueOf(2);
    MatrixV0<Integer> newTester = new MatrixV0<Integer>(2, 2);
    tester.insertRow(0);
    tester.insertRow(1, new Integer[] {i1,i2});
    tester.insertRow(2);
    assertMatrixEquals(new Integer[][] {{0,0},
                                        {i1,i2},
                                        {0,0}}, newTester,"Rows updated");
    tester.deleteRow(1);
    assertMatrixEquals(new Integer[][] {{0,0},
                                        {0,0}}, newTester,"Rows deleted");
  } //setAndDeleteRows()

  /**
   * Sets and removes cols.
   * @throws ArraySizeException 
   */
  @Test
  public void setsCols() throws ArraySizeException {
    MatrixV0<String> colTester = new MatrixV0<String>(2, 2);
    colTester.insertCol(0);
    colTester.insertCol(1, new String[] {"h", "i"});
    colTester.insertCol(2, new String[] {"i", "t"});
    assertMatrixEquals(new String[][] {{"0","h", "i"},
                                        {"0","i", "t"},}, colTester,"Cols updated");
    colTester.deleteCol(0);
    assertMatrixEquals(new String[][] {{"h", "i"},
                                        {"i", "t"},}, colTester,"Col deleted");
    
  } //setsCols()

  /**
   * Tests that line and region work.
   */
  @Test
  public void lineAndRegion() {
    MatrixV0<String> lineAndRegionTester = new MatrixV0<String>(4, 4);
    lineAndRegionTester.fillLine(1, 1, 1, 1, 2, 3, " ");
    assertMatrixEquals(new String[][] {{"0"," ", "0", "0"},
                                        {"0","0", " ", "0"},
                                        {"0","0", "0", "0"},
                                        {"0","0", "0", "0"},}, lineAndRegionTester,"Line filled.");
    lineAndRegionTester.fillRegion(2, 0, 4, 2, "A");
    assertMatrixEquals(new String[][] {{"0"," ", "0", "0"},
                                        {"0","0", " ", "0"},
                                        {"A","A", "0", "0"},
                                        {"A","A", "0", "0"},}, lineAndRegionTester,"Line filled.");
  } // lineAndRegion()
}
