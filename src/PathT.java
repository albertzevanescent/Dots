/**
 * Author: Albert Zhou zhouj103
 * Revised: 30/03/2020
 * Description: Module for representing a path.
*/ 

package src;

import java.util.ArrayList;

/**
 * @brief A module for representing a path.
 */
public class PathT{
    
    private ArrayList<LineT> lines;

    /**
     * @brief Create a path
     * @details Set the initial line.
     * @param l the initial line
     */
    public PathT(){
        this.lines = new ArrayList<LineT>();
    }

    /**
     * @brief Add a line
     * @details Add the line to lines
     * @param l the line to add
     */
    public void add(LineT l){
        this.lines.add(l);
    }

    /**
     * @brief Determine if the path is valid
     * @details A path is valid if, for every pair of lines, the end point of the first
     *          is equal to the start point of the second. A path with 0 lines is invalid.
     *          A path with 1 line is valid.
     * @return true if the path is valid, false otherwise
     */
    public Boolean valid(){
        if (this.lines.size() == 0)
            return false;
        else if (this.lines.size() == 1)
            return true;

        for(int i = 0; i < this.lines.size() - 1; i++){
            if (!this.lines.get(i).end().equals(this.lines.get(i + 1).start()))
                return false;
        }

        return true;
    }

    /**
     * @brief Calculate how many points are on the path
     * @details Add up the mag of every line and subtract by the number of lines - 1.
     * @return mag the number of points on the path
     */
    public int mag(){
        int mag = -(this.lines.size() - 1);

        for(int i = 0; i < this.lines.size(); i++)
            mag += this.lines.get(i).mag();

        return mag;
    }

    /**
     * @brief Get the set of points on the path
     * @details Get the points taking the union of set of points of each line
     * @return p the set of points on the path
     */
    public ArrayList<PointT> points(){
        ArrayList<PointT> p = new ArrayList<PointT>();
        ArrayList<PointT> q = null;
        for(int i = 0; i < this.lines.size(); i++){
            q = this.lines.get(i).points();
            for(int j = 0; j < q.size(); j ++)
                if (!p.contains(q.get(j)))
                    p.add(q.get(j));
        }

        return p;
    }

}