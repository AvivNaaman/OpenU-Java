/**
 * a Class which uses a 2-d array to represent a monochrome image, 
 * with values of 0-255 (8-bit) in each pixel (cell) in the matrix
 */
public class Matrix
{
    /* Instance Varriables */
    // The array which stores the matrix values
    int[][] _matrix;
    
    /* Constructors */
    /**
     * Creates a new Matrix, and copies it's values by the array parameter.
     * @param array the array to copy the matrix values from.
     */
    public Matrix(int[][] array)
    {
        // store sizes
        int size1 = array.length;
        int size2 = array[0].length;
        // construct new array by sizes
        this._matrix = new int[size1][size2];
        // copy the parameter array into the _matrix array
        // for each row
        for (int i = 0; i < size1; i++)
        {
            // for each cell in row
            for (int j = 0; j < size2; j++)
            {
                // copy value to same cell row/col
                this._matrix[i][j] = array[i][j];
            }
        }
    }
    
    /**
     * Creates a new empty matrix array, with size1 rows and size2 columns.
     * @param size1 the rows number of the matrix
     * @param size2 the colummns number of the matrix
     */
    public Matrix(int size1, int size2)
    {
        // _matrix array = new array by param sizes
        _matrix = new int[size1][size2];
    }
    
    
    /* Methods */
    /**
     * returns a string with all the values in the Matrix.
     * each matrix row is in a row, and each value in a row is seperated from the next by a tab. for example:<br/>
     * 1    2   3<br/>
     * 4    5   6<br/>
     * 7    8   9
     * @return a string representing all the Matrix values.
     */
    public String toString()
    {
        // final result to return
        String result = "";
        for (int i = 0; i < _matrix.length; i++)
        {
            for (int j = 0; j < _matrix[0].length; j++)
            {
                // if not first in line, add tab
                if (j != 0)
                    result += "\t";
                // add value
                result += this._matrix[i][j];
            }
            // line down
            result += "\n";
        } 
        // return final result
        return result;
    }
    
    /**
     * returns a new matrix - with the negative of the current matrix image - means,
     * all the values are turned to their completor to 255 - blacks turned to whites
     * and whites are turned to blacks, grays are turned to their opposites in the color scale.
     * @return a new Matrix object, with the negative image of the current Matrix.
     */
    public Matrix makeNegative()
    {
        int size1 = this._matrix.length, size2 = this._matrix[0].length;
        int[][] newMatrixArr = new int[size1][size2];
        for (int i = 0; i < size1; i++)
        {
            for (int j = 0; j < size2; j++)
            {
                newMatrixArr[i][j] = 255 - this._matrix[i][j];
            }
        }
        return new Matrix(newMatrixArr);
    }
    
    /**
     * returns a new matrix, which each cell in it is averaged to the values of all it's nearby cells - 
     * means, the average of the circle of 8 cells around + the cell value itself.
     * @return the blur Matrix of the current Matrix image - each cell is averaged by the values of the surronding cells of it.
     */
    public Matrix imageFilterAverage()
    {
        int size1 = this._matrix.length, size2 = this._matrix[0].length;
        int[][] newMatrixArr = new int[size1][size2];
        // for each row
        for (int i = 0; i < size1; i++)
        {
            // for each col
            for (int j = 0; j < size2; j++)
            {
                // find average and assign to new matrix array
                newMatrixArr[i][j] = getAverageForCell(i, j);
            }
        }
        return new Matrix(newMatrixArr);
    }
    
    /**
     * returns a new Matrix, which it's image is rotated to the right by 90 degerees.
     * @return Matrix respresents the rotated image of this MAtrix by 90 degrees to the right.
     */
    public Matrix rotateClockwise()
    {
        int oldRowCount = this._matrix.length, oldColCount = this._matrix[0].length;
        int[][] newMatrixArr = new int[oldColCount][oldRowCount];
        
        for (int r = 0; r < oldRowCount; r++)
        {
            for (int c = 0; c < oldColCount; c++)
            {
                int newR = c;
                int newC = oldRowCount - r-1;
                newMatrixArr[newR][newC] = this._matrix[r][c];
            }
        }
        return new Matrix(newMatrixArr);
    }
    
    /**
     * returns a new Matrix, which it's image is rotated to the right by -90 degerees (90 deg to the left)
     * @return Matrix respresents the rotated image of this MAtrix by -90 degrees to the right (90 degrees to the left).
     */
    public Matrix rotateCounterClockwise()
    {
        // rotate in -90 = rotate in 270-360=-90, so let's turn in 3 times in 90 deg:
        return this.rotateClockwise().rotateClockwise().rotateClockwise();
    }
    
    /* Privae methods */
    
    // returns the average of values for each cell (for the method imageFilterAverage).
    private int getAverageForCell(int row, int col)
    {
        int rowsCount = this._matrix.length, colsCount = this._matrix[0].length;
        int cellAroundCounter = 0;
        int cellAroundSum = 0;
        // add cell itself
        cellAroundSum = this._matrix[row][col];
        cellAroundCounter++;
        // if Not First row..
        if (row != 0)
        {
            int[] rowAbove = this._matrix[row - 1];
            cellAroundSum += rowAbove[col];
            cellAroundCounter++;
            //  And not first col
            if (col != 0)
            {
                cellAroundSum += rowAbove[col -1]; // and col before
                cellAroundCounter++;
            }
            // And not Last col
            if (col != colsCount-1)
            {
                cellAroundSum += rowAbove[col + 1]; // and col after
                cellAroundCounter++;
            }
        }
        // if not last row..
        if (row != rowsCount - 1)
        {
            int[] rowUnder = this._matrix[row + 1];
            cellAroundSum += rowUnder[col];
            cellAroundCounter++;
            //  And not first col
            if (col != 0)
            {
                cellAroundSum += rowUnder[col - 1];
                cellAroundCounter++;
            }
            // And not Last col
            if (col != colsCount-1)
            {
                cellAroundSum += rowUnder[col + 1];
                cellAroundCounter++;
            }
        }
        // if not first col
        if (col != 0)
        {
            cellAroundSum += this._matrix[row][col-1];
            cellAroundCounter++;
        }
        // if not last col
        if (col != colsCount - 1)
        {
            cellAroundSum += this._matrix[row][col+1];
            cellAroundCounter++;
        }
        // avg is the sum divided by summed count
        return cellAroundSum / cellAroundCounter;
    }
}
