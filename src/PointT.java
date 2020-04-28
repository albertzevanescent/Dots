/**
 * Author: Albert Zhou zhouj103
 * Revised: 31/03/2020
 * Description: Module for representing a point.
*/ 

package src;

/**
 * @brief A module for representing a point.
 */
public class PointT{
    
    private final int r;
    private final int c;

    /**
     * @brief Create a point
     * @details Sets the row and column values.
     * @param row the row value
     * @param col the column value
     */
    public PointT(int row, int col){
        this.r = row;
        this.c = col;
    }

    /**
     * @brief Get r
     * @details Returns the r value.
     * @return r the row value
     */
    public int row(){
        return this.r;
    }

    /**
     * @brief Get c
     * @details Returns the c value.
     * @return c the column value
     */
    public int col(){
        return this.c;
    }

    /**
     * @brief Determine if a PointT is equal to this
     * @details Determine if the rows are equal and the columns are equal.
     * @oaram q the PointT to compare to
     * @return true if q is equal to this, false otherwise
     */
    public boolean equals(PointT q){
        return this.r == q.row() && this.c == q.col();
    }

}