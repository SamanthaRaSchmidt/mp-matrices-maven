package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Sam Schmidt
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
@SuppressWarnings("unchecked")
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The height of the matrix starts at 1, not 0.
   */
  int rows;

  /**
   * The width of the matrix starts at 1, not 0.
   */
  int cols;

  /**
   * A matrix made from a two dimensional array of generic types.
   */
  T[][] matrix;

  /**
   * The default value to fill the matrice.
   */
  T defaultVal;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) throws NegativeArraySizeException{
    if (width < 0) {
      throw new NegativeArraySizeException("Width cannot be negative");
    } else if(height < 0) {
      throw new NegativeArraySizeException("Height cannot be negative");
    } //endif

    T[][] tempMatrix;
    tempMatrix = (T[][]) new Object[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        tempMatrix[i][j] = def;
      } // end for
    } // endfor

    this.matrix = tempMatrix;
    this.rows = height;
    this.cols = width;
    this.defaultVal = def;
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) throws IndexOutOfBoundsException {
    if (row > rows) {
      throw new IndexOutOfBoundsException();
    } else if (col > cols) {
      throw new IndexOutOfBoundsException();
    } //endif
    return matrix[row][col];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) throws IndexOutOfBoundsException {
    if (row > rows) {
      throw new IndexOutOfBoundsException();
    } else if (col > cols) {
      throw new IndexOutOfBoundsException();
    } //endif

    matrix[row][col] = val;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.rows;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.cols;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) throws IndexOutOfBoundsException {
    if (row < 0 || row > rows) {
      throw new IndexOutOfBoundsException();
    } //endif
    
    T[][] newMatrix;
    newMatrix = (T[][]) new Object[rows + 1][cols];

    for (int i = 0; i < rows + 1; i++) {
      for(int j = 0; j < cols; j++) {
        if (i < row) {
          newMatrix[i][j] = matrix[i][j];
        } else if (i > row){
          newMatrix[i][j] = matrix[i - 1][j];
        } else {
          newMatrix[i][j] = defaultVal;
        } //endif
      } //endfor
    } //endfor

    this.rows = rows + 1;

    this.matrix = newMatrix;
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException, IndexOutOfBoundsException {
    if (row < 0 || row > rows) {
      throw new IndexOutOfBoundsException();
    } else if (vals.length != cols) {
      throw new ArraySizeException();
    } //endif
    
    T[][] newMatrix;
    newMatrix = (T[][]) new Object[rows + 1][cols];

    for (int i = 0; i < rows + 1; i++) {
      for(int j = 0; j < cols; j++) {
        if (i < row) {
          newMatrix[i][j] = matrix[i][j];
        } else if (i > row){
          newMatrix[i][j] = matrix[i - 1][j];
        } else {
          newMatrix[i][j] = vals[j];
        } //endif
      } //endfor
    } //endfor

    this.rows = rows + 1;

    this.matrix = newMatrix;
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) throws IndexOutOfBoundsException {
    if (col < 0 || col > cols) {
      throw new IndexOutOfBoundsException();
    } //endif
    
    T[][] newMatrix;
    newMatrix = (T[][]) new Object[rows][cols + 1];

    for (int i = 0; i < rows; i++) {
      for(int j = 0; j < cols + 1; j++) {
        if (j < col) {
          newMatrix[i][j] = matrix[i][j];
        } else if (j > col){
          newMatrix[i][j] = matrix[i][j - 1];
        } else {
          newMatrix[i][j] = defaultVal;
        } //endif
      } //endfor
    } //endfor

    this.cols = cols + 1;

    this.matrix = newMatrix;
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException, IndexOutOfBoundsException {
    if (col < 0 || col > cols) {
      throw new IndexOutOfBoundsException();
    } else if (vals.length != rows) {
      throw new ArraySizeException();
    } //endif
    
    T[][] newMatrix;
    newMatrix = (T[][]) new Object[rows][cols + 1];

    for (int i = 0; i < rows; i++) {
      for(int j = 0; j < cols + 1; j++) {
        if (j < col) {
          newMatrix[i][j] = matrix[i][j];
        } else if (j > col){
          newMatrix[i][j] = matrix[i][j - 1];
        } else {
          newMatrix[i][j] = vals[i];
        } //endif
      } //endfor
    } //endfor

    this.cols = cols + 1;

    this.matrix = newMatrix;
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) throws IndexOutOfBoundsException {
    if (row >= rows || row < 0) {
      throw new IndexOutOfBoundsException();
    } //endif

    T[][] newMatrix;
    newMatrix = (T[][]) new Object[rows - 1][cols];

    for (int i = 0; i < rows; i++) {
      for(int j = 0; j < cols; j++) {
        if (i < row) {
          newMatrix[i][j] = matrix[i][j];
        } else if (i > row){
          newMatrix[i - 1][j] = matrix[i][j];
        } //endif
      } //endfor
    } //endfor

    this.rows = rows - 1;

    this.matrix = newMatrix;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) throws IndexOutOfBoundsException {
    if (col >= cols || col < 0) {
      throw new IndexOutOfBoundsException();
    } //endif

    T[][] newMatrix;
    newMatrix = (T[][]) new Object[rows][cols - 1];

    for (int i = 0; i < rows; i++) {
      for(int j = 0; j < cols; j++) {
        if (j < col) {
          newMatrix[i][j] = matrix[i][j];
        } else if (j > col){
          newMatrix[i][j - 1] = matrix[i][j];
        } //endif
      } //endfor
    } //endfor

    this.cols = cols - 1;

    this.matrix = newMatrix;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) throws IndexOutOfBoundsException {
        if (startRow > this.height() || startRow < 0 || endRow > this.height() 
        || endRow < 0 || startCol > this.width() || startCol < 0 
        || endCol > this.width() || endCol < 0) {
          throw new IndexOutOfBoundsException();
        } //endif

        for (int i = startRow; i < endRow; i++) {
          for (int j = startCol; j < endCol; j++) {
            set(i, j, val);
          } //endfor
        } //endfor
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) throws IndexOutOfBoundsException {

        int i = startRow;
        int j = startCol;

        while(i < endRow && j < endCol) {
          set(i, j, val);
          i = i + deltaRow;
          j = j + deltaCol;
        } //while
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  @SuppressWarnings("rawtypes")
  public Matrix clone() {
    T[][] clone;
    T[][] newMatrix;
    newMatrix = (T[][]) new Object[rows][cols - 1];

    
    return this;        // STUB
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  @SuppressWarnings("rawtypes")
  public boolean equals(Object other) {
    
    if(((Matrix) other).width() != this.width()) {
      return false;
    } else if (((Matrix) other).height() != this.height()) {
      return false;
    } else {
      for (int i = 0; i < height(); i++) {
        for (int j = 0; j < width(); j++) {
          if (this.get(i, j) != ((Matrix) other).get(i, j)) {
            return false;
          } //endif
        } // end for
      } // endfor
    } //endif

    return true;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
