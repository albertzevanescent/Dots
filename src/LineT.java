/**
 * Author: Albert Zhou zhouj103
 * Revised: 31/03/2020
 * Description: Module for representing a line.
*/ 

package src;

import java.util.ArrayList;

/**
 * @brief A module for representing a line.
 */
public class LineT{
    
    private final PointT start;
    private final PointT end;
    private final DirectionT dir;

    /**
     * @brief Create a line
     * @details Set the start point, end point, and calculate the direction.
     * @param p the start point
     * @param q the end point
     * @throws IllegalArgumentException if p and q are not along the same row, the same
     *          column, or if they are equal
     */
    public LineT(PointT p, PointT q){
        if (p.row() == q.row() && p.col() == q.col())
            throw new IllegalArgumentException("Cannot make line out of equal points");
        else if ((p.row() == q.row()) == (p.col() == q.col()))
            throw new IllegalArgumentException("Cannot make diagonal line");

        this.start = p;
        this.end = q;
        this.dir = calcDir(p, q);
    }

    /**
     * @brief Get start
     * @details Returns the start value.
     * @return start the start point
     */
    public PointT start(){
        return this.start;
    }

    /**
     * @brief Get end
     * @details Returns the end value.
     * @return end the end point
     */
    public PointT end(){
        return this.end;
    }

    /**
     * @brief Calculate how many points are on the line
     * @details There is a point for every integer difference between the start
     *          and end points(inclusively).
     * @return mag the number of points on the line
     */
    public int mag(){
        int mag = 1;
        if (this.start.row() == this.end.row())
            mag += abs(this.start.col() - this.end.col());
        else
            mag += abs(this.start.row() - this.end.row());

        return mag;
    }

    /**
     * @brief Get the set of points on the line
     * @details Get the points between start and end by translating start.
     * @return p the set of points on the line
     */
    public ArrayList<PointT> points(){
        ArrayList<PointT> p = new ArrayList<PointT>();
        int dr = 0;
        int dc = 0;
        for(int i = 0; i < this.mag(); i++){
            if (dir == DirectionT.E)
                dc = i;
            else if (dir == DirectionT.W)
                dc = -i;
            else
                dc = 0;

            if (dir == DirectionT.N)
                dr = i;
            else if (dir == DirectionT.S)
                dr = -i;
            else
                dr = 0;

            p.add(new PointT(this.start.row() + dr, this.start.col() + dc));
        }

        return p;
    }

    /**
     * @brief Calculate the absolute value
     * @details If less than 0 multiply by -1
     * @param x the int to make positive
     * @return y the absolute value
     */
    private int abs(int x){
        int y = x;
        if (y < 0)
            return -1 * y;
        return y;
    }

    /**
     * @brief Determine the direction of 2 points
     * @details If the rows increase S. If columns increase E. If columns decrease W.
     *          Otherwise N. Assume row 0 is North.
     * @param p the start point
     * @param q the end point
     * @return d the direction of 2 points
     */
    private DirectionT calcDir(PointT p, PointT q){
        DirectionT d = DirectionT.N;
        if (p.row() > q.row())
            d = DirectionT.S;
        else if (p.col() < q.col())
            d = DirectionT.E;
        else if (p.col() > q.col())
            d = DirectionT.W;
        return d;
    }

}