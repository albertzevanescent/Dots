/**
 * Author: Albert Zhou zhouj103
 * Revised: 30/03/2020
 * Description: Module for representing a board from Dots.
*/ 

package src;

import java.util.ArrayList;

/**
 * @brief A module for representing a board from Dots.
 */
public class BoardT{
    
    private ArrayList<ArrayList<ColourT>> b;
    private final int nRow;
    private final int nCol;

    /**
     * @brief Create a board
     * @details Set the sequence of sequence of colours, the number of rows, and the number
     *          of columns.
     * @param board the sequence of sequence of colours
     * @throws IllegalArgumentException if |board| <= 0, |board[0]| <= 0, there is a row
     *          with columns different from the first, or there is a white colour.
     */
    public BoardT(ArrayList<ArrayList<ColourT>> board){
        if (board.size() <= 0)
            throw new IllegalArgumentException("No rows");

        if (board.get(0).size() <= 0) 
            throw new IllegalArgumentException("No columns");

        for(int i = 1; i < board.size(); i++) {
            if (board.get(i).size() != board.get(0).size())
                throw new IllegalArgumentException("Number of columns in row " + 
                i + " doesn't match row 0");
        }

        for(int i = 0; i < board.size(); i++) {
            for(int j = 0; j < board.get(i).size(); j++)
                if (board.get(i).get(j) == ColourT.W)
                    throw new IllegalArgumentException("White colour at (" + i + "," + j + ")");
        }

        this.b = board;
        this.nRow = board.size();
        this.nCol = board.get(0).size();
    }

    /**
     * @brief Get b
     * @details Returns the board.
     * @return b the board
     */
    public ArrayList<ArrayList<ColourT>> board(){
        return this.b;
    }

    /**
     * @brief Determine if a path is valid on the board
     * @details A path is valid is every point in the path is valid and if every colour on
     *          the board denoted by the points on the path are the same.
     * @param p the path to check
     * @return true if the path is valid on the board, false otherwise
     * @throws IllegalArgumentException if p is an invalid path
     */
    public boolean validPath(PathT p){
        if (!p.valid())
            throw new IllegalArgumentException("The given path is invalid");

        ArrayList<PointT> points = p.points();
        for(int i = 0; i < points.size(); i++)
            if (!this.validPoint(points.get(i)) || 
            this.get(points.get(i)) != this.get(points.get(0)))
                return false;
        return true;
    }

    /**
     * @brief Get the colour of a path
     * @details The colour of a path is the colour of any of its points.
     * @param p the path to get the colour of
     * @return ColourT the colour of the path
     * @throws IllegalArgumentException if p is an invalid path on the board
     */
    public ColourT pathCol(PathT p){
        if (!this.validPath(p))
            throw new IllegalArgumentException("The given path is invalid on the board");

        return this.get(p.points().get(0));
    }

    /**
     * @brief Remove the colours on a path, shift the remaining colours down, and replace
     *          missing colours with random ones.
     * @details Separate the operations into 3 steps: clearOut, shiftDown, and refill
     * @param p the path to remove
     * @throws IllegalArgumentException if p is an invalid path on the board
     */
    public void rmPath(PathT p){
        if (!this.validPath(p))
            throw new IllegalArgumentException("The given path is invalid on the board");

        this.clearOut(p);
        this.shiftDown();
        this.refill();
    }

    /**
     * @brief Set the points on a path to white
     * @details For every point on the path, set the colour to white
     * @param p the path to remove
     */
    private void clearOut(PathT p){
        ArrayList<PointT> points = p.points();
        for(int i = 0; i < points.size(); i++)
            this.set(points.get(i), ColourT.W);
    }

    /**
     * @brief Determine if the board can be shifted down
     * @details The board can be shifted down if there is a non-white colour directly
     *          above a white colour
     * @return true if the board can be shifted down, false otherwise
     */
    private boolean shiftDownAble(){
        PointT p = null;
        PointT q = null;
        for(int i = 0 ; i < this.nRow - 1; i++)
            for(int j = 0; j < this.nCol; j++){
                p = new PointT(i, j);
                q = new PointT(i+1, j);
                if (this.get(p) == ColourT.W && this.get(q) != ColourT.W)
                    return true; 
            }
        return false;
    }

    /**
     * @brief Shift down each row if needed
     * @details Shift down each row if needed
     */
    private void shiftDown(){
        while(this.shiftDownAble())
            for(int i = 0; i < nRow - 1; i++)
                this.shiftDownRow(i);
    }

    /**
     * @brief Shift down a row
     * @details For every column, if there is a non-white colour in the above row
     *          and a white colour in this one swap them
     */
    private void shiftDownRow(int i){
        PointT p = null;
        PointT q = null;
        for(int j = 0; j < nCol; j++){
            p = new PointT(i, j);
            q = new PointT(i+1, j);
            if (this.get(p) == ColourT.W && this.get(q) != ColourT.W){
                this.set(p, this.get(q));
                this.set(q, ColourT.W);
            }
        }
    }

    /**
     * @brief Randomize missing colours
     * @details Replace every white colour with a random one
     */
    private void refill(){
        PointT p = null;
        for(int i = 0; i < this.nRow; i++)
            for(int j = 0; j < this.nCol; j++){
                p = new PointT(i,j);
                if(this.get(p) == ColourT.W)
                    this.set(p, ColourT.randCol());
            }
    }

    /**
     * @brief Set a colour at a point
     * @details Set the colour at the point specified in the board
     * @param p a point on the board
     * @param c the colour to be set
     */
    private void set(PointT p, ColourT c){
        this.b.get(p.row()).set(p.col(), c);
    }

    /**
     * @brief Get the colour at a point
     * @details Get the colour at the point specified in the board
     * @param p a point on the board
     * @param c the colour at the point
     */
    private ColourT get(PointT p){
        return this.b.get(p.row()).get(p.col());
    }

    /**
     * @brief Determine if a given number is a valid row
     * @details Checks if 0 <= j < nRow.
     * @param i the potenital row number
     * @return true if i is a valid row, false otherwise
     */
    private boolean validRow(int i){
        return 0 <= i && i < nRow;
    }

    /**
     * @brief Determine if a given number is a valid column
     * @details Checks if 0 <= j < nCol.
     * @param j the potenital column number
     * @return true if j is a valid column, false otherwise
     */
    private boolean validCol(int j){
        return 0 <= j && j < nCol;
    }

    /**
     * @brief Determine if a given point is a valid point on the board
     * @details Checks if p.row() is a valid row and p.col() is a valid column.
     * @param p a point that may be on the board
     * @return true if p is a valid point on the board, false otherwise
     */
    private boolean validPoint(PointT p){
        return validRow(p.row()) && validCol(p.col());
    }

}