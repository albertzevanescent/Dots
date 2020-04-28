/**
 * Author: Albert Zhou zhouj103
 * Revised: 30/03/2020
 * Description: Module for representing a level.
*/ 

package src;

import java.util.ArrayList;

/**
 * @brief A module for representing a level.
 */
public class LevelT{
    
    private ArrayList<ObjectiveT> objs;
    private final int moves;

    /**
     * @brief Create a level
     * @details Set the sequence of objectives and the number of moves.
     * @param o the sequence of objectives
     * @param m the number of moves
     * @throws IllegalArgumentException if m is less than 5
     */
    public LevelT(ArrayList<ObjectiveT> o, int m){
        if (m < 5)
            throw new IllegalArgumentException("Cannot have number of moves less than 5");

        this.objs = o;
        this.moves = m;
    }

    /**
     * @brief Get objs
     * @details Returns the objs array.
     * @return objs the objectives of the level
     */
    public ArrayList<ObjectiveT> objectives(){
        return this.objs;
    }

    /**
     * @brief Get moves
     * @details Returns the moves value.
     * @return moves the maximum number of moves in the level
     */
    public int moves(){
        return this.moves;
    }

    /**
     * @brief Determine if the level is complete
     * @details A level is complete if the number of moves taken is less than or equal
     *          to the maximum of the level and if all of its objectives are complete.
     * @param m the number of moves taken
     * @return true if the level is complete, false otherwise
     */
    public boolean complete(int m){
        for(int i = 0; i < this.objs.size(); i++)
            if (this.objs.get(i).complete() != true)
                return false;
        return m <= this.moves;
    }

    /**
     * @brief Attempt to complete the objectives in the level
     * @details Attempt each objective.
     * @param c the colour of a path
     * @param x the count of a path
     */
    public void attempt(ColourT c, int x){
        for(int i = 0; i < this.objs.size(); i++)
            this.objs.get(i).attempt(c, x);
    }

}